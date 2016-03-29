package com.lefu.weixin.token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.lefu.weixin.controller.TokenController;
import com.lefu.weixin.entity.AccessToken;
import com.lefu.weixin.entity.Message;
import com.lefu.weixin.http.HttpCon;
import com.lefu.weixin.util.Path;
import com.lefu.weixin.util.R;
import com.lefu.weixin.util.SignUtil;
import com.lefu.weixin.widget.SpringContextHolder;

import net.sf.json.JSONObject;

/**
 * 微信公众号主类
 */
@SuppressWarnings("all")
public class WeiXinToken extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Logger log = LogManager.getLogger(this.getClass());
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    private TokenController tokenController;

    public WeiXinToken() {
        try {
            JAXBContext context = JAXBContext.newInstance(Message.class);
            marshaller = context.createMarshaller();
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        tokenController = SpringContextHolder.getBean("tokenController", TokenController.class);
        Assert.notNull(tokenController, "tokenController must not be null");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戮
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        // String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            // out.print(echostr);
            try {
                responseMsg(request, response);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        out.close();
        out = null;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 自动回复内容
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws JAXBException
     */
    public void responseMsg(ServletRequest request, ServletResponse response) throws IOException, JAXBException {
        PrintWriter out = response.getWriter();
        String      postStr = null;
        try {
            postStr = this.readStreamParameter(request.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(postStr);
        //  System.out.println(postStr);
        if (null != postStr && !postStr.isEmpty()) {
            Message msg = (Message) unmarshaller.unmarshal(new StringReader(postStr));
            String fromUsername = msg.getFromUserName();
            String toUsername = msg.getToUserName();
            String msgType = msg.getMsgType();
            log.info(msg);
            if (msg.getEvent() != null && !"".equals(msg.getEvent())) {
                if (R.event.subscribe.equalsIgnoreCase(msg.getEvent())) {
                    Message result = tokenController.subscribe(fromUsername, toUsername);
                    marshaller.marshal(result, out);
                    log.info(result);
                }
            } else if (R.type.text.equalsIgnoreCase(msgType)) {
                String keyword = msg.getContent();
                log.info("input keyword is : " + keyword);
                if (null != keyword && !keyword.equals("")) {
                    Message result = tokenController.autoAnswer(keyword, fromUsername, toUsername);
                    result.setContent(getAccessToken());
                    // marshaller.marshal(result, out);
                    log.info(result);
                }
            }
        } else {
            out.print("");
        }
    }

    /**
     * 从输入流读取post参数
     *
     * @param in
     * @return
     */
    public String readStreamParameter(ServletInputStream in) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 返回Token
     *
     * @return
     */
    public String getAccessToken() {
        ServletContext application = this.getServletContext();
        AccessToken token = (AccessToken) application.getAttribute("token");
        if (null == token) {
            // 如果Token为空
            token = queryToken();
        } else if (System.currentTimeMillis() / 1000 - token.getCreateTime().longValue() / 1000 > token.getExpiresIn().intValue() - 600) {
            // 如果超时
            token = queryToken();
        }
        return token.getAccessToken();
    }

    /**
     * 查询Token
     *
     * @return
     */
    private AccessToken queryToken() {
        AccessToken token = new AccessToken();
        String json = HttpCon.httpRequest(Path.getAccessTokenUrl(), "GET", null);
        JSONObject object = JSONObject.fromObject(json);
        token.setAccessToken(object.optString("access_token"));
        token.setExpiresIn(object.optInt("expires_in"));
        token.setCreateTime(System.currentTimeMillis());
        this.getServletContext().setAttribute("token", token);
        return token;
    }
}
