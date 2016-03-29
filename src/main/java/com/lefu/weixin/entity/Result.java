package com.lefu.weixin.entity;

import java.util.List;

@SuppressWarnings("all")
public class Result<E> implements java.io.Serializable {
	/**
	 * 成功对象
	 */
	public static final Result SUCCEED = new Result("success");
	/**
	 * 失败对象
	 */
	public static final Result FAILED = new Result("failed");
	private E content;
	private String status = "success";
	private String message;

	public Result() {
	}

	public Result(String status) {
		this.status = status;
	}

	public E getContent() {
		return content;
	}

	public void setContent(E content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
