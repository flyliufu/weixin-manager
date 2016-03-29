package com.lefu.weixin.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * HTTP连接类. 用于HTTP的连接，提供了普通http传输和文件http传输函数。
 * 
 * @author fkxpjj
 *
 */
public class HttpCon {

	private static Log log = LogFactory.getLog(HttpCon.class);

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				log.info(outputStr);
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("https request error:{}", e);
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		//String accsessToken = SignUtil.getAccessToken("wx72757b8078f3ce64", "27c5fdbfa3d4663deb88abdafd1de322");
		//System.out.println(accsessToken);
		String str = "{ \"button\": [{ \"type\": \"click\", \"name\": \"今日歌曲\", \"key\": \"V1001\" }, { \"name\": \"菜单\", \"sub_button\": [{ \"type\": \"view\", \"name\": \"搜索\", \"url\": \"http://www.soso.com/\" }, { \"type\": \"view\", \"name\": \"视频\", \"url\": \"http://v.qq.com/\" }, { \"type\": \"click\", \"name\": \"赞一下我们\", \"key\": \"V1001\" }]}]}";
		String result = HttpCon.httpRequest("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=9ZIjkwSNn4oNGHqjqxxutonWZVh5J3FwSzyNoaImJqj--boSjkP_XwQInUvKFY0UuBlqtr5MS3NZV55AefAe1QWhjf_2qnsVoWAj_OA6RQMj3bfRtwX8vGlehesvlC4FADBgABARUS", "POST", str);
		System.out.println(result);
	}
}
