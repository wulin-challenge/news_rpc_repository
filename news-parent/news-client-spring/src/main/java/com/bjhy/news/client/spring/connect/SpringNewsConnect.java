package com.bjhy.news.client.spring.connect;

import com.bjhy.cache.toolkit.extension.ExtensionLoader;
import com.bjhy.cache.toolkit.util.NativeHostUtil;
import com.bjhy.news.common.connect.NewsConnect;

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
	public String zookeeperId() {
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
	public String clientIp() {
		return NativeHostUtil.getHostAddress();
	}

	@Override
	public Integer clientPort() {
		return getSpringLoadNewsConnect().getClientPort();
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
	public String rocketmqPublishEnvironment() {
		return getSpringLoadNewsConnect().getRocketmqPublishEnvironment();
	}

	@Override
	public String rocketmqPublishVersion() {
		return getSpringLoadNewsConnect().getRocketmqPublishVersion();
	}
}
