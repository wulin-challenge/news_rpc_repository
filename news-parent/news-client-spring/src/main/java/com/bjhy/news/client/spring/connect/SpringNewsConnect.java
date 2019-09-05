package com.bjhy.news.client.spring.connect;

import org.apache.commons.lang3.StringUtils;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.RocketmqNewsType;

import cn.wulin.brace.utils.NativeHostUtil;
import cn.wulin.ioc.extension.ExtensionLoader;

/**
 * 消息rpc的连接接口 的spring实现
 * @author wubo
 *
 */
public class SpringNewsConnect implements NewsConnect{
	
	private SpringLoadNewsConnect getSpringLoadNewsConnect(){
		
//		ExtensionLoader.setInstance(SpringLoadNewsConnect.class
		SpringLoadNewsConnect instance = ExtensionLoader.getInstance(SpringLoadNewsConnect.class);	
		return instance;
//		return ApplicationContextHelper.getApplicationContext().getBean(SpringLoadNewsConnect.class);
	}

	@Override
	public String zookeeperIp() {
		String[] split = getSpringLoadNewsConnect().getZookeeperAddress().split(":");
		return split[0];
	}

	@Override
	public Integer zookeeperPort() {
		String[] split = getSpringLoadNewsConnect().getZookeeperAddress().split(":");
		return Integer.valueOf(split[1]);
	}
	
	@Override
	public String rocketmqAddress() {
		return getSpringLoadNewsConnect().getRocketmqAddress();
	}
	
	

	@Override
	public String clientId() {
		return  getSpringLoadNewsConnect().getClientId();
	}

	@Override
	public String clientName() {
		return  getSpringLoadNewsConnect().getClientName();
	}

	@Override
	public String clientIp() {
		String clientIp = getSpringLoadNewsConnect().getClientIp();
		if(StringUtils.isBlank(clientIp) ){
			clientIp = NativeHostUtil.getHostAddress();
		}
		return clientIp;
	}

	@Override
	public Integer clientPort() {
		return getSpringLoadNewsConnect().getClientPort();
	}
	
	@Override
	public Integer clientTelnetPort() {
		return getSpringLoadNewsConnect().getClientTelnetPort();
	}

	@Override
	public String clientTopic() {
		return getSpringLoadNewsConnect().getClientTopic();
	}

	@Override
	public String clientTag() {
		return getSpringLoadNewsConnect().getClientTag();
	}
	
	@Override
	public Integer retries() {
		return getSpringLoadNewsConnect().getClientRetries();
	}

	@Override
	public String cluster() {
		return getSpringLoadNewsConnect().getClientCluster();
	}

	@Override
	public String loadbalance() {
		return getSpringLoadNewsConnect().getClientLoadbalance();
	}

	@Override
	public String rocketmqPublishEnvironment() {
		return getSpringLoadNewsConnect().getRocketmqPublishEnvironment();
	}

	@Override
	public String rocketmqPublishVersion() {
		return getSpringLoadNewsConnect().getRocketmqPublishVersion();
	}

	@Override
	public RocketmqNewsType rocketmqNewsType() {
		String code = getSpringLoadNewsConnect().getRocketmqNewsType();
		return RocketmqNewsType.getRocketmqNewsTypeByCode(code);
	}

	@Override
	public boolean rocketmqIsUniqueGroup() {
		return getSpringLoadNewsConnect().getRocketmqIsUniqueGroup();
	}

	@Override
	public Integer rocketmqOrderQueue() {
		return getSpringLoadNewsConnect().getRocketmqOrderQueue();
	}

	@Override
	public Integer payload() {
		return getSpringLoadNewsConnect().getPayload();
	}
}
