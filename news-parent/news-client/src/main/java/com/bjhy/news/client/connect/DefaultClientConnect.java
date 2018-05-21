package com.bjhy.news.client.connect;

import com.bjhy.cache.toolkit.util.NativeHostUtil;
import com.bjhy.cache.toolkit.util.YamlUtil;
import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.util.NewsConstants;

/**
 * 默认的客户端连接
 * 消息rpc的连接接口 默认的客户端连接
 * @author wubo
 */
public class DefaultClientConnect implements NewsConnect{
	
	@Override
	public String zookeeperId() {
		String[] split = YamlUtil.getValue("news.zookeeper-address", "127.0.0.1", String.class).split(":");
		return split[0];
	}

	@Override
	public Integer zookeeperPort() {
		String[] split = YamlUtil.getValue("news.zookeeper-address", "2181", String.class).split(":");
		if(split.length==2){
			return Integer.valueOf(split[1]);
		}
		return Integer.valueOf(split[0]);
	}

	@Override
	public String rocketmqIp() {
		String[] split = YamlUtil.getValue("news.rocketmq-address", "127.0.0.1", String.class).split(":");
		return split[0];
	}

	@Override
	public Integer rocketmqPort() {
		String[] split = YamlUtil.getValue("news.rocketmq-address", "2181", String.class).split(":");
		if(split.length==2){
			return Integer.valueOf(split[1]);
		}
		return Integer.valueOf(split[0]);
	}

	@Override
	public String clientIp() {
		return NativeHostUtil.getHostAddress();
	}
	
	@Override
	public Integer clientPort() {
		return YamlUtil.getValue("news.client-port", 5588,Integer.class);
	}

	@Override
	public String clientTopic() {
		return YamlUtil.getValue("news.client-topic",String.class);
	}

	@Override
	public String clientTag() {
		return YamlUtil.getValue("news.client-tag",String.class);
	}

	@Override
	public String rocketmqPublishEnvironment() {
		return YamlUtil.getValue("news.rocketmq-publish-environment",NewsConstants.ROCKETMQ_PUBLISH_ENVIRONMENT_PRODUCT,String.class);
	}

	@Override
	public String rocketmqPublishVersion() {
		return YamlUtil.getValue("news.rocketmq-publish-version",null,String.class);
	}
	
}