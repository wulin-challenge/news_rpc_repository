package com.bjhy.news.common.connect;

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
	public String zookeeperId() {
		return getNewsConnect().zookeeperId();
	}

	@Override
	public Integer zookeeperPort() {
		return getNewsConnect().zookeeperPort();
	}

	@Override
	public String rocketmqIp() {
		return getNewsConnect().rocketmqIp();
	}

	@Override
	public Integer rocketmqPort() {
		return getNewsConnect().rocketmqPort();
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
	public String clientTopic() {
		return getNewsConnect().clientTopic();
	}

	@Override
	public String clientTag() {
		return getNewsConnect().clientTag();
	}

	@Override
	public String rocketmqPublishEnvironment() {
		return getNewsConnect().rocketmqPublishEnvironment();
	}

	@Override
	public String rocketmqPublishVersion() {
		return getNewsConnect().rocketmqPublishVersion();
	}
}