package com.bjhy.news.common.zookeeper;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.util.NewsConstants;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.UrlUtils;

/**
 * 通过zk找到注册的服务
 * @author wubo
 *
 */
public class DiscoveryZkService {
	private static DiscoveryZkService discoveryZkService;
	
	private DiscoveryZkService(){}
	
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	public void serviceDiscovery(String clientTopic,String clientTag,Class<?> interfaceService){
		String serviceName = interfaceService.getName();
		String publishTagRootPath = getPublishTagRootPath(clientTopic, clientTag,serviceName);
		
		ZookeeperConfig zookeeperConfig = ZookeeperConfig.getInstance();
		List<String> childrenFullPathList = zookeeperConfig.getChildrenFullPathList(publishTagRootPath);
		
		System.out.println();
		System.out.println();
		
//		zookeeperConfig.getNodeData(publishTagRootPath)
		
		
	}
	
	/**
	 * 通过topic,tag,服务接口,版本号 得到唯一发现的服务信息(多个Ip之间采用随机访问)
	 * @param clientTopic
	 * @param clientTag
	 * @param interfaceService
	 * @param syncVersion
	 * @return
	 */
	public DiscoveryServiceInfo getDiscoveryServiceInfoOnlyOne(String clientTopic,String clientTag,Class<?> interfaceService,String syncVersion){
		DiscoveryServiceInfo discoveryServiceInfo = getDiscoveryServiceInfo(clientTopic, clientTag, interfaceService);
		List<DiscoveryServiceDetailInfo> discoveryServiceDetailInfoList = discoveryServiceInfo.getDiscoveryServiceDetailInfoList();
		
		if(discoveryServiceDetailInfoList == null || discoveryServiceDetailInfoList.isEmpty()){
			return null;
		}
		
		int size = discoveryServiceDetailInfoList.size();
		if(size ==1){
			return discoveryServiceInfo;
		}
		
		if(size>1){
			DiscoveryServiceDetailInfo discoveryServiceDetailInfo = discoveryServiceDetailInfoList.get(ThreadLocalRandom.current().nextInt(size));
			List<DiscoveryServiceDetailInfo> onlyOneList = new ArrayList<DiscoveryServiceDetailInfo>();
			onlyOneList.add(discoveryServiceDetailInfo);
			discoveryServiceInfo.setDiscoveryServiceDetailInfoList(onlyOneList);
			return discoveryServiceInfo;
		}
		return null;
	}
	
	/**
	 * 通过topic,tag,服务接口得到发现的服务信息
	 * @param clientTopic
	 * @param clientTag
	 * @param interfaceService
	 * @return
	 */
	public DiscoveryServiceInfo getDiscoveryServiceInfo(String clientTopic,String clientTag,Class<?> interfaceService){
		DiscoveryServiceInfo discoveryServiceInfo = new DiscoveryServiceInfo();
		
		String serviceName = interfaceService.getName();
		String publishTagRootPath = getPublishTagRootPath(clientTopic, clientTag,serviceName);
		
		ZookeeperConfig zookeeperConfig = ZookeeperConfig.getInstance();
		List<String> childrenFullPathList = zookeeperConfig.getChildrenFullPathList(publishTagRootPath);
		
		discoveryServiceInfo.setClientTopic(clientTopic);
		discoveryServiceInfo.setClientTag(clientTag);
		discoveryServiceInfo.setServiceClass(interfaceService);
		
		for (String childrenFullPath : childrenFullPathList) {
			String urlAddress = new String(zookeeperConfig.getNodeData(childrenFullPath),Charset.defaultCharset());
			URL url = UrlUtils.parseURL(urlAddress, null);
			
			DiscoveryServiceDetailInfo detailInfo = new DiscoveryServiceDetailInfo();
			detailInfo.setServiceIp(url.getHost());
			detailInfo.setServicePort(url.getPort());
			detailInfo.setTimeout(url.getParameter(NewsConstants.SYNC_TIMEOUT_KEY, 60000));
			detailInfo.setVersion(url.getParameter(NewsConstants.SYNC_VERSION_KEY, ""));
			discoveryServiceInfo.getDiscoveryServiceDetailInfoList().add(detailInfo);
		}
		return discoveryServiceInfo;
	}
	
	/**
	 * 得到Tag级的根
	 * @return
	 */
	private String getPublishTagRootPath(String clientTopic,String clientTag,String serviceName){
		StringBuffer registerPath = new StringBuffer(NewsConstants.ZK_ROOT_NODE);
		registerPath.append("/"+clientTopic);
		registerPath.append("/"+clientTag);
		registerPath.append("/"+serviceName);
		return registerPath.toString();
	}
	
	public static DiscoveryZkService getInstance(){
		if(discoveryZkService == null){
			discoveryZkService = new DiscoveryZkService();
		}
		return discoveryZkService;
	} 
	
	
}
