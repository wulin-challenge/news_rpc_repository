package com.bjhy.news.common.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.bjhy.news.common.api.InvokerStrategyCluster;
import com.bjhy.news.common.cache.TimedOverdueCache;
import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.domain.TopicTag;
import com.bjhy.news.common.proxy.RemoteAsyncProxy;
import com.bjhy.news.common.proxy.RemoteProxy;
import com.bjhy.news.common.zookeeper.DiscoveryZkService;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.UrlUtils;

/**
 * 消息rpc客户端工具类
 * @author wubo
 */
public class NewsUtil {
	/**
	 * 同步发送消息,使用默认主题和默认tag标签进行消息的发送
	 * @param interfaceClass 接口class
	 * @throws IOException 
	 */
	public static <T> T syncSend(Class<T> interfaceClass) throws IOException{
		return syncSend(new TopicTag(NewsConstants.DEFAULT_TOPIC, NewsConstants.DEFAULT_TAG), interfaceClass);
	}
	
	/**
	 * 同步发送消息
	 * @param topicTags 客户端标识和 客户端应用标识 (主题和标记/标签)
	 * @param interfaceClass 接口class
	 * @throws IOException 
	 */
	public static <T> T syncSend(TopicTag topicTag,Class<T> interfaceClass) throws IOException{
		return syncSend(topicTag, interfaceClass,null);
	}
	
	
	/**
	 * 同步发送消息
	 * @param topicTags 客户端标识和 客户端应用标识 (主题和标记/标签)
	 * @param interfaceClass 接口class
	 * @param syncVersion 同步rpc的服务版本
	 * @throws IOException 
	 */
	public static <T> T syncSend(TopicTag topicTag,Class<T> interfaceClass,String syncVersion) throws IOException{
		URL url = NewsRpcUtil.getSyncSendUrl(topicTag, interfaceClass, syncVersion);
		RemoteProxy adaptiveExtension = InterfaceExtensionLoader.getExtensionLoader(RemoteProxy.class).getAdaptiveExtension();
		url = url.addParameter("invokeType", "netty.remote.proxy");
		return adaptiveExtension.remoteInvoke(url, null,interfaceClass);
	}
	
	/**
	 * 异步发送消息
	 * @param topicTags 客户端标识和 客户端应用标识 (主题和标记/标签)
	 * @param interfaceClass 接口class
	 */
	public static RemoteAsyncProxy asyncSend(TopicTag topicTag,Class<?> interfaceClass){
		return asyncSend(topicTag, interfaceClass,1000*60);
	}
	
	/**
	 * 异步发送消息
	 * @param topicTags 客户端标识和 客户端应用标识 (主题和标记/标签)
	 * @param interfaceClass 接口class
	 */
	public static RemoteAsyncProxy asyncSend(TopicTag topicTag,Class<?> interfaceClass,Integer timeout){
		timeout = (timeout == null || timeout<=0)?((topicTag.getTimeout() == null || topicTag.getTimeout()<=0)?60000:topicTag.getTimeout()):timeout;
		
		DiscoveryServiceInfo discoveryServiceInfo = new DiscoveryServiceInfo();
		discoveryServiceInfo.setClientTopic(topicTag.getTopic());
		discoveryServiceInfo.setClientTag(topicTag.getTag());
		discoveryServiceInfo.setServiceClass(interfaceClass);
		
		DiscoveryServiceDetailInfo detailInfo = new DiscoveryServiceDetailInfo();
		detailInfo.setTimeout(timeout);
		discoveryServiceInfo.getDiscoveryServiceDetailInfoList().add(detailInfo);
		
		RemoteProxy adaptiveExtension = InterfaceExtensionLoader.getExtensionLoader(RemoteProxy.class).getAdaptiveExtension();
		URL emptyUrl = UrlUtils.getEmptyUrl("", "");
		emptyUrl = emptyUrl.addParameter("invokeType", "rocketmq.remote.proxy");
		return adaptiveExtension.remoteInvoke(emptyUrl, discoveryServiceInfo,RemoteAsyncProxy.class);
	}
}
