package com.bjhy.news.common.heartbeat.telnet;


/**
 * telnet 心跳响应
 * @author wulin
 *
 */
public class TelnetHeartbeatInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 请求code
	 * 0: telnet发起的请求,1: telnet处理后返回的响应
	 */
	private int requestCode;
	
	/**
	 * 真正的值
	 */
	private int result;
	
	
	public TelnetHeartbeatInfo() {}
	public TelnetHeartbeatInfo(int requestCode, int result) {
		super();
		this.requestCode = requestCode;
		this.result = result;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
