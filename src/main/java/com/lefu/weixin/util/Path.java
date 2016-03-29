package com.lefu.weixin.util;

public class Path {
	
	/**
	 * AccessToken url
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static String getAccessTokenUrl() {
		return "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + R.app.appid + "&secret=" + R.app.secret;
	}
}
