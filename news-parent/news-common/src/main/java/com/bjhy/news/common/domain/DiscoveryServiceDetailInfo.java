package com.bjhy.news.common.domain;

/**
 * 服务发现详细
 * @author wubo
 */
public class DiscoveryServiceDetailInfo {
	/**
	 * 同步调用时的版本
	 */
	private String version;
	
	/**
	 * 同步调用时的超时时间
	 */
	private Integer timeout;
	
	/**
	 * 服务Ip
	 */
	private String serviceIp;
	
	/**
	 * 服务端口
	 */
	private Integer servicePort;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

	public Integer getServicePort() {
		return servicePort;
	}

	public void setServicePort(Integer servicePort) {
		this.servicePort = servicePort;
	}
}
