package com.lefu.weixin.controller.impl;

import org.springframework.stereotype.Controller;

import com.lefu.weixin.controller.TokenController;
import com.lefu.weixin.entity.Message;
import com.lefu.weixin.util.R;

@Controller(value = "tokenController")
public class TokenControllerImpl implements TokenController {

	public Message subscribe(String fromUser, String toUser) {
		Message result = new Message();
		result.setToUserName(fromUser);
		result.setFromUserName(toUser);
		result.setCreateTime(R.util.getSysTime() + "");
		result.setMsgType(R.type.text);
		result.setContent("你好，很荣幸您能关注我们公众账号。\n我们不定期向你推荐各种安卓开发相关文章，共同进步");
		return result;
	}

	public Message autoAnswer(String key, String fromUser, String toUser) {
		Message result = new Message();
		result.setToUserName(fromUser);
		result.setFromUserName(toUser);
		result.setCreateTime(R.util.getSysTime() + "");
		result.setMsgType(R.type.text);
		return result;
	}
}
