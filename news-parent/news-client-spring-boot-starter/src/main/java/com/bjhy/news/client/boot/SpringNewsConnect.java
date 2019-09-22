package com.bjhy.news.client.boot;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.RocketmqNewsType;
import com.bjhy.news.common.util.NewsConstants;

import cn.wulin.brace.utils.NativeHostUtil;
import cn.wulin.ioc.extension.ExtensionLoader;

/**
 * 消息rpc的连接接口 的spring实现
 * @author wulin
 *
 */
public class SpringNewsConnect implements NewsConnect{
	private NewsBootConfigurer newsBootConfigurer;
	private Environment environment;
	private String providerConsumer;
	
	public SpringNewsConnect() {
		super();
		newsBootConfigurer = ExtensionLoader.getInstance(NewsBootConfigurer.class);
		environment = newsBootConfigurer.getEnvironment();
	}

	@Override
	public String zookeeperIp() {
		String zookeeperAddress =  environment.getProperty("news.zookeeper-address", String.class,"127.0.0.1:2181");
		String[] split =zookeeperAddress.split(":");
		return split[0];
	}

	@Override
	public Integer zookeeperPort() {
		String zookeeperAddress =  environment.getProperty("news.zookeeper-address", String.class,"127.0.0.1:2181");
		String[] split =zookeeperAddress.split(":");
		return Integer.valueOf(split[1]);
	}
	
	@Override
	public String rocketmqAddress() {
		return environment.getProperty("news.rocketmq-address", String.class,"127.0.0.1:9876");
	}
	
	@Override
	public String clientId() {
		return environment.getProperty("news.client-id", String.class,environment.getProperty("appId", String.class));
	}
	
	@Override
	public String clientName() {
		return environment.getProperty("news.client-name", String.class,environment.getProperty("appName", String.class));
	}

	@Override
	public String clientIp() {
		String clientIp =environment.getProperty("news.client-ip", String.class);
		if(StringUtils.isBlank(clientIp) ){
			clientIp = NativeHostUtil.getHostAddress();
		}
		return clientIp;
	}

	@Override
	public Integer clientPort() {
		return environment.getProperty("news.client-port", Integer.class,5588);
	}
	
	@Override
	public Integer clientTelnetPort() {
		return environment.getProperty("news.client-telnet-port", Integer.class,clientPort()+1);
	}

	@Override
	public String clientTopic() {
		return environment.getProperty("news.client-topic", String.class,NewsConstants.DEFAULT_TOPIC);
	}

	@Override
	public String clientTag() {
		return environment.getProperty("news.client-tag", String.class,NewsConstants.DEFAULT_TAG);
	}
	
	@Override
	public Integer retries() {
		return environment.getProperty("news.client-retries", Integer.class,2);
	}

	@Override
	public String cluster() {
		return environment.getProperty("news.client-cluster", String.class,"failover");
	}

	@Override
	public String loadbalance() {
		return environment.getProperty("news.client-loadbalance", String.class,"random");
	}

	@Override
	public String rocketmqPublishEnvironment() {
		return environment.getProperty("news.rocketmq-publish-environment", String.class,NewsConstants.ROCKETMQ_PUBLISH_ENVIRONMENT_PRODUCT);
	}

	@Override
	public String rocketmqPublishVersion() {
		return  environment.getProperty("news.rocketmq-publish-version", String.class);
	}

	@Override
	public RocketmqNewsType rocketmqNewsType() {
		String code = environment.getProperty("news.rocketmq-news-type", String.class, "general");
		return RocketmqNewsType.getRocketmqNewsTypeByCode(code);
	}

	@Override
	public boolean rocketmqIsUniqueGroup() {
		return environment.getProperty("news.rocketmq-is-unique-group", Boolean.class, false);
	}

	@Override
	public Integer rocketmqOrderQueue() {
		return environment.getProperty("news.rocketmq-order-queue", Integer.class, 0);
	}

	@Override
	public Integer payload() {
		return environment.getProperty("news.payload", Integer.class, 65536);
	}

	@Override
	public String interfaceGroup() {
		return environment.getProperty("news.interface-group", String.class, NewsConstants.DEFAULT_INTERFACE_GROUP);
	}

	@Override
	public String providerConsumer() {
		if(StringUtils.isBlank(this.providerConsumer)) {
			this.providerConsumer = environment.getProperty("news.provider-consumer", String.class);
		}
		return providerConsumer;
	}

	@Override
	public void setProviderConsumer(String providerConsumer) {
		this.providerConsumer = providerConsumer;
	}
}
