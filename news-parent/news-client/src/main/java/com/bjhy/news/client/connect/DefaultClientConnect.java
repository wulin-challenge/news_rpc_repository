package com.bjhy.news.client.connect;
import org.apache.commons.lang3.StringUtils;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.RocketmqNewsType;
import com.bjhy.news.common.util.NewsConstants;

import cn.wulin.brace.utils.NativeHostUtil;
import cn.wulin.brace.utils.YamlUtil;

/**
 * 默认的客户端连接
 * 消息rpc的连接接口 默认的客户端连接
 * @author wubo
 */
public class DefaultClientConnect implements NewsConnect{
	
	@Override
	public String zookeeperIp() {
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
	public String rocketmqAddress() {
		return YamlUtil.getValue("news.rocketmq-address", "localhost:9876", String.class);
	}
	
	@Override
	public String clientId() {
		return YamlUtil.getValue("news.client-id", String.class);
	}

	@Override
	public String clientName() {
		return YamlUtil.getValue("news.client-name", String.class);
	}

	@Override
	public String clientIp() {
		String clientIp = YamlUtil.getValue("news.client-ip", String.class);
		if(StringUtils.isBlank(clientIp)){
			clientIp = NativeHostUtil.getHostAddress();
		}
		return clientIp;
	}
	
	@Override
	public Integer clientPort() {
		return YamlUtil.getValue("news.client-port", 5588,Integer.class);
	}
	
	@Override
	public Integer clientTelnetPort() {
		return YamlUtil.getValue("news.client-telnet-port", clientPort()+1,Integer.class);
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
	public Integer retries() {
		return YamlUtil.getValue("news.client-retries",2,Integer.class);
	}

	@Override
	public String cluster() {
		return YamlUtil.getValue("news.client-cluster","failover",String.class);
	}

	@Override
	public String loadbalance() {
		return YamlUtil.getValue("news.client-loadbalance","random",String.class);
	}

	@Override
	public String rocketmqPublishEnvironment() {
		return YamlUtil.getValue("news.rocketmq-publish-environment",NewsConstants.ROCKETMQ_PUBLISH_ENVIRONMENT_PRODUCT,String.class);
	}

	@Override
	public String rocketmqPublishVersion() {
		return YamlUtil.getValue("news.rocketmq-publish-version",null,String.class);
	}

	@Override
	public RocketmqNewsType rocketmqNewsType() {
		String code = YamlUtil.getValue("news.rocketmq-publish-version","general",String.class);
		return RocketmqNewsType.getRocketmqNewsTypeByCode(code);
	}
	
	@Override
	public boolean rocketmqIsUniqueGroup() {
		return YamlUtil.getValue("news.rocketmq-is-unique-group",false,boolean.class);
	}

	@Override
	public Integer rocketmqOrderQueue() {
		return YamlUtil.getValue("news.rocketmq-order-queue",0,Integer.class);
	}

	@Override
	public Integer payload() {
		return YamlUtil.getValue("news.payload",65536,Integer.class);
	}
}
