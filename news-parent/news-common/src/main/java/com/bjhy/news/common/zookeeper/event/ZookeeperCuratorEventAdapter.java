package com.bjhy.news.common.zookeeper.event;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;

import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.proxy.RemoveRpcClient;
import com.bjhy.news.common.zookeeper.RegistryZkService;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.UrlUtils;

public class ZookeeperCuratorEventAdapter implements ZookeeperCuratorEvent{
	@Override
	public void nodeAddedEvent(CuratorFramework curatorFramework,TreeCacheEvent treeCacheEvent) {
	}

	@Override
	public void nodeUpdatedEvent(CuratorFramework curatorFramework,TreeCacheEvent treeCacheEvent) {
	}

	@Override
	public void nodeRemovedEvent(CuratorFramework curatorFramework,TreeCacheEvent treeCacheEvent) {
		//清除过期的netty客户端
		String urlStr = new String(treeCacheEvent.getData().getData(),Charset.defaultCharset());
		cleanExpireNettyClient(UrlUtils.parseURL(urlStr, null));
	}
	
	@Override
	public void connectionReconnectedEvent(CuratorFramework curatorFramework,TreeCacheEvent treeCacheEvent) {
		//失败重连
		List<PublishServiceInfo> publishServiceInfoList = RegistryZkService.getInstance().getCachePublishServiceInfo();
		if(publishServiceInfoList != null && publishServiceInfoList.size()>0){
			RegistryZkService.getInstance().registerZkService(publishServiceInfoList);
		}
	}
	
	/**
	 * 清除过期的netty客户端
	 */
	private void cleanExpireNettyClient(URL url){
		RemoveRpcClient removeRpcClient = InterfaceExtensionLoader.getExtensionLoader(RemoveRpcClient.class).getAdaptiveExtension();
		removeRpcClient.cleanRpcClient(url);
	}
}
