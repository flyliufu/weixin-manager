package com.lefu.weixin.util;

/***
 * �쳣��
 * 
 * @author LiuFu
 * 
 *         2015��7��15��
 */
public class WSException extends Exception {

	private static final long serialVersionUID = 4690056790033912475L;
	private String code = "";
	private String msg;
	private String content;

	public WSException() {
		super();
	}

	public WSException(String msg) {
		this.msg = msg;
	}

	public WSException(String code, String msg, String content) {
		this.code = code;
		this.msg = msg;
		this.content = content;
	}

	public WSException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}

	public String getCode() {
		return code;
	}

	public String getContent() {
		return content;
	}
}
