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
	
	/**
	 * 服务所属的pid进程,用于处理同一台服务器上启动多个相同服务的情况
	 */
	private Integer pid;
	
	/**
	 * 调用失败次数,用于处理当同一个服务调用失败次数 大于等于 maxFailNumber时,直接从缓存列表中剔除
	 */
	private Integer failNumber=0;

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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getFailNumber() {
		return failNumber;
	}

	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}
}
