package com.bjhy.news.rpc.api.netty.domain;

/**
 * netty的rpc类型
 * @author wulin
 *
 */
public enum NettyRpcType {
	
	USER_SERVICE("user_service","用户的服务"),MOCK_SERVICE("mock_service","mock服务"),TELNET_SERVICE("telnet_service","telnet服务");
	
	private String code;
	private String name;
	
	private NettyRpcType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
}
