package com.bjhy.news.common.domain;
/**
 * 客户端标识和 客户端应用标识 (主题和标记/标签)
 * @author wubo
 */
public class TopicTag{
	/**
	 * 客户端标识(主题)
	 */
	private String topic;
	
	/**
	 * 客户端应用标识(标记/标签)
	 */
	private String tag;
	
	/**
	 * 同步调用的
	 * 重试次数,当不想要重试,请使用赋值为0
	 * 当cluster使用 failover策略才生效
	 */
	private Integer retries;
	
	/**
	 * 同步调用的集群策略
	 */
	private String cluster;
	
	/**
	 * 同步调用的负载均衡策略
	 */
	private String loadbalance;
	
	/**
	 * 同步调用的版本号
	 */
	private String syncVersion;
	
	/**
	 * 超时时间
	 */
	private Integer timeout;
	
	public TopicTag(){}
	
	public TopicTag(String topic, String tag) {
		this(topic,tag,null);
	}
	public TopicTag(String topic, String tag,Integer timeout) {
		this(topic,tag,timeout,null);
	}
	
	public TopicTag(String topic, String tag,Integer timeout,String syncVersion) {
		super();
		this.topic = topic;
		this.tag = tag;
		this.timeout = timeout;
		this.syncVersion = syncVersion;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getRetries() {
		return retries;
	}

	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getLoadbalance() {
		return loadbalance;
	}

	public void setLoadbalance(String loadbalance) {
		this.loadbalance = loadbalance;
	}

	public String getSyncVersion() {
		return syncVersion;
	}

	public void setSyncVersion(String syncVersion) {
		this.syncVersion = syncVersion;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
}