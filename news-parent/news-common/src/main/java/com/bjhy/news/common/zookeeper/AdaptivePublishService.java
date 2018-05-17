package com.bjhy.news.common.zookeeper;

import java.util.List;

import com.bjhy.news.common.domain.PublishServiceInfo;

import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

@Adaptive
public class AdaptivePublishService implements PublishService{
	
	private static volatile String DEFAULT_PUBLISH_SERVICE;

	public static void setDefaultAdaptivePublishService(String publishService) {
		DEFAULT_PUBLISH_SERVICE = publishService;
	}

	/**
	 * 得到真正的消息rpc连接
	 * @return
	 */
	private PublishService getPublishService() {
		PublishService publishService;
		InterfaceExtensionLoader<PublishService> loader = InterfaceExtensionLoader.getExtensionLoader(PublishService.class);
		String name = DEFAULT_PUBLISH_SERVICE; // copy reference
		if (name != null && name.length() > 0) {
			publishService = loader.getExtension(name);
		} else {
			publishService = loader.getDefaultExtension();
		}
		return publishService;
	}
	
	@Override
	public List<PublishServiceInfo> getPublishServiceInfo() {
		return getPublishService().getPublishServiceInfo();
	}
	
}
