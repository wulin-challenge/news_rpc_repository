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
	
	@Value("${news.client-port:5588}")
	private Integer clientPort;
	
	@Value("${news.client-topic}")
	private String clientTopic;
	
	@Value("${news.client-tag}")
	private String clientTag;
	
	@Value("${news.rocketmq-publish-environment:"+NewsConstants.ROCKETMQ_PUBLISH_ENVIRONMENT_PRODUCT+"}")
	private String rocketmqPublishEnvironment;
	
	@Value("${news.rocketmq-publish-version:null}")
	private String rocketmqPublishVersion;
	
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
}
