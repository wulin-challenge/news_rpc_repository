package com.bjhy.news.common.connect;

import com.bjhy.news.common.domain.RocketmqNewsType;

import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

@Adaptive
public class AdaptiveNewsConnect implements NewsConnect {

	private static volatile String DEFAULT_NEWS_CONNECT;

	public static void setDefaultAdaptiveNewsConnect(String newsConnect) {
		DEFAULT_NEWS_CONNECT = newsConnect;
	}

	/**
	 * 得到真正的消息rpc连接
	 * @return
	 */
	private NewsConnect getNewsConnect() {
		NewsConnect newsConnect;
		InterfaceExtensionLoader<NewsConnect> loader = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class);
		String name = DEFAULT_NEWS_CONNECT; // copy reference
		if (name != null && name.length() > 0) {
			newsConnect = loader.getExtension(name);
		} else {
			newsConnect = loader.getDefaultExtension();
		}
		return newsConnect;
	}

	@Override
	public String zookeeperIp() {
		return getNewsConnect().zookeeperIp();
	}

	@Override
	public Integer zookeeperPort() {
		return getNewsConnect().zookeeperPort();
	}

	@Override
	public String rocketmqAddress() {
		return getNewsConnect().rocketmqAddress();
	}
	
	@Override
	public String clientId() {
		return getNewsConnect().clientId();
	}

	@Override
	public String clientName() {
		return getNewsConnect().clientName();
	}

	@Override
	public String clientIp() {
		return getNewsConnect().clientIp();
	}

	@Override
	public Integer clientPort() {
		return getNewsConnect().clientPort();
	}
	
	@Override
	public Integer clientTelnetPort() {
		return getNewsConnect().clientTelnetPort();
	}

	@Override
	public String clientTopic() {
		return getNewsConnect().clientTopic();
	}

	@Override
	public String clientTag() {
		return getNewsConnect().clientTag();
	}
	
	@Override
	public Integer retries() {
		return getNewsConnect().retries();
	}

	@Override
	public String cluster() {
		return getNewsConnect().cluster();
	}

	@Override
	public String loadbalance() {
		return getNewsConnect().loadbalance();
	}

	@Override
	public String rocketmqPublishEnvironment() {
		return getNewsConnect().rocketmqPublishEnvironment();
	}

	@Override
	public String rocketmqPublishVersion() {
		return getNewsConnect().rocketmqPublishVersion();
	}

	@Override
	public RocketmqNewsType rocketmqNewsType() {
		return getNewsConnect().rocketmqNewsType();
	}

	@Override
	public boolean rocketmqIsUniqueGroup() {
		return getNewsConnect().rocketmqIsUniqueGroup();
	}

	@Override
	public Integer rocketmqOrderQueue() {
		return getNewsConnect().rocketmqOrderQueue();
	}

	@Override
	public Integer payload() {
		return getNewsConnect().payload();
	}
	
}
