package com.bjhy.news.client.spring.connect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bjhy.news.common.util.NewsConstants;

/**
 * spring如何加载消息配置连接
 * @author wubo
 */
@Component
public class SpringLoadNewsConnect {
	
	@Value("${news.zookeeper-address:127.0.0.1:2181}")
	private String zookeeperAddress;
	
	@Value("${news.rocketmq-address:127.0.0.1:9876}")
	private String rocketmqAddress;
	
	@Value("${news.client-id:}")
	private String clientId;
	
	@Value("${news.client-name:}")
	private String clientName;
	
	@Value("${news.client-ip:}")
	private String clientIp;
	
	@Value("${news.client-port:5588}")
	private Integer clientPort;
	
	@Value("${news.client-telnet-port:-1}")
	private Integer clientTelnetPort;
	
	@Value("${news.client-topic}")
	private String clientTopic;
	
	@Value("${news.client-tag}")
	private String clientTag;
	
	@Value("${news.client-retries:2}")
	private Integer clientRetries;
	
	@Value("${news.client-cluster:failover}")
	private String clientCluster;
	
	@Value("${news.client-loadbalance:random}")
	private String clientLoadbalance;
	
	@Value("${news.rocketmq-publish-environment:"+NewsConstants.ROCKETMQ_PUBLISH_ENVIRONMENT_PRODUCT+"}")
	private String rocketmqPublishEnvironment;
	
	@Value("${news.rocketmq-publish-version:null}")
	private String rocketmqPublishVersion;
	
	@Value("${news.rocketmq-news-type:general}")
	private String rocketmqNewsType;
	
	@Value("${news.rocketmq-is-unique-group:false}")
	private boolean rocketmqIsUniqueGroup;
	
	@Value("${news.rocketmq-order-queue:0}")
	private Integer rocketmqOrderQueue;
	
	@Value("${news.payload:65536}")
	private Integer payload;
	
	public String getZookeeperAddress() {
		return zookeeperAddress;
	}
	public void setZookeeperAddress(String zookeeperAddress) {
		this.zookeeperAddress = zookeeperAddress;
	}
	public String getRocketmqAddress() {
		return rocketmqAddress;
	}
	public void setRocketmqAddress(String rocketmqAddress) {
		this.rocketmqAddress = rocketmqAddress;
	}
	public Integer getClientPort() {
		return clientPort;
	}
	public void setClientPort(Integer clientPort) {
		this.clientPort = clientPort;
	}
	public Integer getClientTelnetPort() {
		return clientTelnetPort == -1?(getClientPort()+1):clientTelnetPort;
	}
	public void setClientTelnetPort(Integer clientTelnetPort) {
		this.clientTelnetPort = clientTelnetPort;
	}
	public String getClientTopic() {
		return clientTopic;
	}
	public void setClientTopic(String clientTopic) {
		this.clientTopic = clientTopic;
	}
	public String getClientTag() {
		return clientTag;
	}
	public void setClientTag(String clientTag) {
		this.clientTag = clientTag;
	}
	public String getRocketmqPublishEnvironment() {
		return rocketmqPublishEnvironment;
	}
	public void setRocketmqPublishEnvironment(String rocketmqPublishEnvironment) {
		this.rocketmqPublishEnvironment = rocketmqPublishEnvironment;
	}
	public String getRocketmqPublishVersion() {
		return rocketmqPublishVersion;
	}
	public void setRocketmqPublishVersion(String rocketmqPublishVersion) {
		this.rocketmqPublishVersion = rocketmqPublishVersion;
	}
	public String getRocketmqNewsType() {
		return rocketmqNewsType;
	}
	public void setRocketmqNewsType(String rocketmqNewsType) {
		this.rocketmqNewsType = rocketmqNewsType;
	}
	public boolean getRocketmqIsUniqueGroup() {
		return rocketmqIsUniqueGroup;
	}
	public void setRocketmqIsUniqueGroup(boolean rocketmqIsUniqueGroup) {
		this.rocketmqIsUniqueGroup = rocketmqIsUniqueGroup;
	}
	public Integer getRocketmqOrderQueue() {
		return rocketmqOrderQueue;
	}
	public void setRocketmqOrderQueue(Integer rocketmqOrderQueue) {
		this.rocketmqOrderQueue = rocketmqOrderQueue;
	}
	public Integer getClientRetries() {
		return clientRetries;
	}
	public void setClientRetries(Integer clientRetries) {
		this.clientRetries = clientRetries;
	}
	public String getClientCluster() {
		return clientCluster;
	}
	public void setClientCluster(String clientCluster) {
		this.clientCluster = clientCluster;
	}
	public String getClientLoadbalance() {
		return clientLoadbalance;
	}
	public void setClientLoadbalance(String clientLoadbalance) {
		this.clientLoadbalance = clientLoadbalance;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Integer getPayload() {
		return payload;
	}
	public void setPayload(Integer payload) {
		this.payload = payload;
	}
}
