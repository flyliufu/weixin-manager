package com.lefu.weixin.util;

public class R {

	public final static class app {
		public static final String appid = "wx3b23bef3f8332835";
		public static final String secret = "85527999f9fa9c0566935574bab614a1";

		// public static final String appid = "wx72757b8078f3ce64";
		// public static final String secret =
		// "27c5fdbfa3d4663deb88abdafd1de322";
	}

	/**
	 * 消息类型
	 * 
	 * @author liufu
	 *
	 */
	public final static class type {
		public static final String text = "text";
	}

	/**
	 * 事件类型
	 * 
	 * @author liufu
	 *
	 */
	public final static class event {
		public static final String subscribe = "subscribe";
	}

	/**
	 * 系统工具类
	 * 
	 * @author liufu
	 *
	 */
	public final static class util {

		public static long getSysTime() {
			return System.currentTimeMillis();
		}
	}
}
