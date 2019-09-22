package com.bjhy.news.common.util;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.domain.TopicTag;
import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * 通用news-rpc工具类,该工具类是提供给框架内部使用的
 * @author wubo
 *
 */
public class NewsRpcUtil {
	private static final String ZK_ROOT_NODE = "/news";
	
	private static int PID = -1;
	/**
	 * 得到连接配置信息
	 */
	private static NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	/**
	 * 得到订阅缓存key
	 * @param url
	 * @return
	 */
	public static String getSubscribeCacheKey(String clientTopic,String clientTag,Class<?> interfaceService,String syncVersion){
		clientTopic = StringUtils.isBlank(clientTopic)?"":clientTopic.trim();
		clientTag = StringUtils.isBlank(clientTag)?"":clientTag.trim();
		String interfaceServiceKey = interfaceService == null?"":interfaceService.getName();
		syncVersion = StringUtils.isBlank(syncVersion)?"":syncVersion.trim();
		
		StringBuilder key = new StringBuilder(clientTopic);
		key.append("_"+clientTag);
		key.append("_"+interfaceServiceKey);
		key.append("_"+syncVersion);
		return key.toString();
	}
	
	/**
	 * 得到订阅缓存key
	 * @param discoveryServiceInfo
	 * @return
	 */
	public static String getSubscribeCacheKey(DiscoveryServiceInfo discoveryServiceInfo){
		if(discoveryServiceInfo == null || discoveryServiceInfo.getDiscoveryServiceDetailInfoList().size()!=1){
			throw new NewsRpcException("getDetailInfoServiceKey 的"+DiscoveryServiceInfo.class+"不能空 或者"+DiscoveryServiceDetailInfo.class+"的个数只能唯一");
		}
		DiscoveryServiceDetailInfo detailInfo = discoveryServiceInfo.getDiscoveryServiceDetailInfoList().get(0);
		return getSubscribeCacheKey(discoveryServiceInfo.getClientTopic(), discoveryServiceInfo.getClientTag(), discoveryServiceInfo.getServiceClass(), detailInfo.getVersion());
	}
	
	/**
	 * 得到订阅缓存key
	 * @param discoveryServiceInfo
	 * @param detailInfo
	 * @return
	 */
	public static String getSubscribeCacheKey(DiscoveryServiceInfo discoveryServiceInfo,DiscoveryServiceDetailInfo detailInfo){
		DiscoveryServiceInfo dsi = new DiscoveryServiceInfo();
		dsi.setClientTag(discoveryServiceInfo.getClientTag());
		dsi.setClientTopic(discoveryServiceInfo.getClientTopic());
		dsi.setServiceClass(discoveryServiceInfo.getServiceClass());
		dsi.getDiscoveryServiceDetailInfoList().add(detailInfo);
		return getSubscribeCacheKey(dsi);
	}
	
	/**
	 * 得到详细服务的key
	 * @param discoveryServiceInfo
	 * @return
	 */
	public static String getDetailInfoServiceKey(DiscoveryServiceInfo discoveryServiceInfo,DiscoveryServiceDetailInfo detailInfo){
		if(discoveryServiceInfo == null || detailInfo == null){
			throw new NewsRpcException("getDetailInfoServiceKey 的"+DiscoveryServiceInfo.class+"不能空 或者"+DiscoveryServiceDetailInfo.class+"不能为空!");
		}
		
		discoveryServiceInfo.getDiscoveryServiceDetailInfoList().clear();
		discoveryServiceInfo.getDiscoveryServiceDetailInfoList().add(detailInfo);
		return getDetailInfoServiceKey(discoveryServiceInfo);
	}
	
	/**
	 * 得到详细服务的key
	 * @param discoveryServiceInfo
	 * @return
	 */
	public static String getDetailInfoServiceKey(DiscoveryServiceInfo discoveryServiceInfo){
		if(discoveryServiceInfo == null || discoveryServiceInfo.getDiscoveryServiceDetailInfoList().size()!=1){
			throw new NewsRpcException("getDetailInfoServiceKey 的"+DiscoveryServiceInfo.class+"不能空 或者"+DiscoveryServiceDetailInfo.class+"的个数只能唯一");
		}
		DiscoveryServiceDetailInfo detailInfo = discoveryServiceInfo.getDiscoveryServiceDetailInfoList().get(0);
		
		String clientToptic = discoveryServiceInfo.getClientTopic();
		String clientTag = discoveryServiceInfo.getClientTag();
		String interfaceServiceKey = discoveryServiceInfo.getServiceClass().getName();
		String version = detailInfo.getVersion();
		String serviceIp = detailInfo.getServiceIp();
		Integer servicePort = detailInfo.getServicePort();
		Integer pid2 = detailInfo.getPid();
		StringBuilder key = new StringBuilder(clientToptic);	
		key.append("_"+clientTag);
		key.append("_"+interfaceServiceKey);
		key.append("_"+version);
		key.append("_"+serviceIp);
		key.append("_"+servicePort);
		key.append("_"+pid2);
		return key.toString();
	}

	/**
	 * 得到同步发送消息的url
	 * @param topicTags 客户端标识和 客户端应用标识 (主题和标记/标签)
	 * @param interfaceClass 接口class
	 * @param syncVersion 同步rpc的服务版本
	 * @throws IOException 
	 */
	public static URL getSyncSendUrl(TopicTag topicTag,Class<?> interfaceClass,String syncVersion){
		int retries = topicTag.getRetries() == null?newsConnect.retries():topicTag.getRetries();
		String cluster = topicTag.getCluster() == null?newsConnect.cluster():topicTag.getCluster();
		String loadbalance = topicTag.getLoadbalance() == null?newsConnect.loadbalance():topicTag.getLoadbalance();
		
		Map<String,String> params = new HashMap<String,String>();
		params.put(NewsConstants.CLIENT_TOPIC_KEY, topicTag.getTopic());
		params.put(NewsConstants.CLIENT_TAG_KEY, topicTag.getTag());
		params.put(NewsConstants.RETRIES_KEY, new Integer(retries).toString());
		params.put(NewsConstants.CLUSTER_KEY, cluster);
		params.put(NewsConstants.LOADBALANCE_KEY, loadbalance);
		params.put(NewsConstants.SERVICE_INTERFACE_KEY, interfaceClass.getName());
		params.put(NewsConstants.SYNC_TIMEOUT_KEY, Integer.toString(topicTag.getTimeout()==null?0:topicTag.getTimeout()));
		params.put(NewsConstants.SYNC_VERSION_KEY, syncVersion==null?(topicTag.getSyncVersion()==null?"":topicTag.getSyncVersion()):syncVersion);
		
		URL url = new URL("syncSend","0.0.0.0",0,params);
		return url;
	}
	
	/**
	 * 得到进程的pid
	 * @return
	 */
	public static int getPid() {
        if (PID < 0) {
            try {
                RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
                String name = runtime.getName(); // format: "pid@hostname"
                PID = Integer.parseInt(name.substring(0, name.indexOf('@')));
            } catch (Throwable e) {
                PID = 0;
            }
        }
        return PID;
    }
	
	public static SocketAddress string2SocketAddress(final String addr) {
        String[] s = addr.split(":");
        InetSocketAddress isa = new InetSocketAddress(s[0], Integer.parseInt(s[1]));
        return isa;
	}

	/**
	 * 得到zk的根节点,该根节点有{@Link #ZK_ROOT_NODE}/interfaceGroup 构成
	 * @return
	 */
	public static String getZkRootNode() {
		return ZK_ROOT_NODE+"/"+newsConnect.interfaceGroup();
	}
}
