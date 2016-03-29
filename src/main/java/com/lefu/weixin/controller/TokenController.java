package com.lefu.weixin.controller;

import com.lefu.weixin.entity.Message;

public interface TokenController {

	/**
	 * 订阅事件
	 * 
	 * @param fromUser
	 * @param toUser
	 * @return
	 */
	Message subscribe(String fromUser, String toUser);

	/**
	 * 用户在输入框输入文本自动回复
	 * 
	 * @param key 用户输入key
	 * @param fromUser
	 * @param toUser
	 * @return
	 */
	Message autoAnswer(String key, String fromUser, String toUser);

}
