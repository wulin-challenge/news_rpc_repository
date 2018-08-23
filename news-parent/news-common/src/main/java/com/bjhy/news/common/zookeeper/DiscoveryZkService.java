package com.bjhy.news.common.zookeeper;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;

import com.bjhy.news.common.cache.TimedOverdueCache;
import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;
import com.bjhy.news.common.notify.AbstractNotifyListener;
import com.bjhy.news.common.notify.NotifyListener;
import com.bjhy.news.common.util.NewsConstants;
import com.bjhy.news.common.util.NewsRpcUtil;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.UrlUtils;

/**
 * 通过zk找到注册的服务
 * @author wubo
 *
 */
public class DiscoveryZkService extends AbstractNotifyListener{
	/**
	 * 最大失败数,默认为5次
	 */
	private static Integer maxFailNumber = 5;
	
	/**
	 * 通过topic,tag优先从缓存中获取服务列表
	 * @param clientTopic 客户端主题
	 * @param clientTag 客户端标签
	 * @param interfaceService 服务接口
	 * @param syncVersion 同步调用的版本号
	 * @param willBeDeleted 将要被剔除的服务
	 * @return
	 */
	public DiscoveryServiceInfo subscribeService(String clientTopic,String clientTag,Class<?> interfaceService,String syncVersion,DiscoveryServiceInfo willBeDeleted){
		String subscribeCacheKey = NewsRpcUtil.getSubscribeCacheKey(clientTopic, clientTag, interfaceService, syncVersion);
		//找将要被删除的缓存,并将其剔除,同时返回 拷贝的 缓存服务列表(但不包括 willBeDeleted(将要被剔除的缓存))
		DiscoveryServiceInfo copyCacheServiceInfo = willBeDeletedCache(subscribeCacheKey, willBeDeleted);
		if(copyCacheServiceInfo == null || copyCacheServiceInfo.getDiscoveryServiceDetailInfoList().size()==0){
			copyCacheServiceInfo = getDiscoveryServiceInfo(clientTopic, clientTag, interfaceService, syncVersion);
			TimedOverdueCache.put(subscribeCacheKey, copyCacheServiceInfo);
			copyCacheServiceInfo = TimedOverdueCache.get(subscribeCacheKey, DiscoveryServiceInfo.class);
		}
		return copyCacheServiceInfo;
	}
	
	/**
	 * 通过topic,tag,服务接口得到发现的服务信息(所有在注册中心注册的服务接口信息)
	 * @param clientTopic 客户端主题
	 * @param clientTag 客户端标签
	 * @param interfaceService 服务接口
	 * @param syncVersion 同步调用的版本号
	 * @return
	 */
	public DiscoveryServiceInfo getDiscoveryServiceInfo(String clientTopic,String clientTag,Class<?> interfaceService,String syncVersion){
		//处理版本号
		if(StringUtils.isNotBlank(syncVersion)){
			syncVersion = syncVersion.trim();
		}else{
			syncVersion = "";
		}
		
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
			//这是服务提供者注册到注册中心的版本号
			String providerSyncVersion = url.getParameter(NewsConstants.SYNC_VERSION_KEY, "").trim();
			//当版本号为空时表示加载所有版本的服务
			if(StringUtils.isBlank(syncVersion)){
				buildDetailInfo(discoveryServiceInfo, url, providerSyncVersion);
				
			//只加载指定版本的服务
			}else if(syncVersion.equalsIgnoreCase(providerSyncVersion)){
				buildDetailInfo(discoveryServiceInfo, url, providerSyncVersion);
			}
		}
		return discoveryServiceInfo;
	}
	
	public static DiscoveryZkService getInstance(){
		NotifyListener notifyListener = InterfaceExtensionLoader.getExtensionLoader(NotifyListener.class).getExtension("discovery_zk_service");
		return (DiscoveryZkService) notifyListener;
	}
	

	/**
	 * 通过topic,tag,服务接口,版本号 得到唯一发现的服务信息(多个Ip之间采用随机访问,简单的负载均衡实现)
	 * @param clientTopic
	 * @param clientTag
	 * @param interfaceService
	 * @param syncVersion
	 * @return
	 */
	public DiscoveryServiceInfo getDiscoveryServiceInfoOnlyOne(String clientTopic,String clientTag,Class<?> interfaceService,String syncVersion){
		DiscoveryServiceInfo discoveryServiceInfo = getDiscoveryServiceInfo(clientTopic, clientTag, interfaceService,syncVersion);
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

	@Override
	protected void doNotify(URL url) throws NewsRpcException {
		String serviceInterfaceString = url.getParameter(NewsConstants.SERVICE_INTERFACE_KEY);
		if(serviceInterfaceString != null){
			try {
				Class<?> serviceInterface = Class.forName(serviceInterfaceString);
				//通过通知监听的url生产 服务提供者列表
				DiscoveryServiceInfo notifyServiceInfo = getDiscoveryServiceInfoByNotifyUrl(url, serviceInterface);
				String subscribeCacheKey = NewsRpcUtil.getSubscribeCacheKey(notifyServiceInfo);
				TimedOverdueCache.remove(subscribeCacheKey);
//				//处理通知事件
//				dealWithNotifyEvent(url, notifyServiceInfo, subscribeCacheKey);
			} catch (ClassNotFoundException e) {
				//TODO吃掉该 异常
			}
		}
	}

	/**
	 * 处理通知事件
	 * @param url
	 * @param notifyServiceInfo
	 * @param subscribeCacheKey
	 */
	//TODO 该方法有问题,之所以还保留着,是为了以后可能修改这一块时提供一些思路
	private void dealWithNotifyEvent(URL url, DiscoveryServiceInfo notifyServiceInfo, String subscribeCacheKey) {
		DiscoveryServiceInfo cacheServiceInfo = TimedOverdueCache.get(subscribeCacheKey, DiscoveryServiceInfo.class);
		//事件类型
		String category = url.getParameter(NewsConstants.CATEGORY_KEY);
		if(NewsConstants.ZOOKEEPER_NODE_UPDATED_EVENT.equals(category)){
			if(cacheServiceInfo == null || cacheServiceInfo.getDiscoveryServiceDetailInfoList().size()==0){
				TimedOverdueCache.put(subscribeCacheKey, notifyServiceInfo);
			}else{
				List<DiscoveryServiceDetailInfo> cacheDetailInfoList = deleteCacheServiceByNotifyService(cacheServiceInfo, notifyServiceInfo);
				cacheDetailInfoList.add(notifyServiceInfo.getDiscoveryServiceDetailInfoList().get(0));
			}
		}else if(NewsConstants.ZOOKEEPER_NODE_REMOVED_EVENT.equals(category)){
			deleteCacheServiceByNotifyService(cacheServiceInfo, notifyServiceInfo);
		}
	}

	@Override
	protected Set<String> getCategory() {
		Set<String> category = new HashSet<String>();
		category.add(NewsConstants.ZOOKEEPER_NODE_REMOVED_EVENT);
		category.add(NewsConstants.ZOOKEEPER_NODE_UPDATED_EVENT);
		category.add(NewsConstants.ZOOKEEPER_NODE_ADDED_EVENT);
		category.add(NewsConstants.ZOOKEEPER_RECONNECTED_EVENT);
		return category;
	} 
	
	/**
	 * 通过通知监听的url生产 服务提供者列表
	 * @param url
	 * @return
	 */
	private DiscoveryServiceInfo getDiscoveryServiceInfoByNotifyUrl(URL url,Class<?> serviceInterface){
		DiscoveryServiceInfo discoveryServiceInfo = new DiscoveryServiceInfo();
		discoveryServiceInfo.setClientTopic(url.getParameter(NewsConstants.CLIENT_TOPIC_KEY));
		discoveryServiceInfo.setClientTag(url.getParameter(NewsConstants.CLIENT_TAG_KEY));
		discoveryServiceInfo.setServiceClass(serviceInterface);
		
		DiscoveryServiceDetailInfo detailInfo = new DiscoveryServiceDetailInfo();
		detailInfo.setPid(url.getParameter(NewsConstants.PID,-1));
		detailInfo.setServiceIp(url.getHost());
		detailInfo.setServicePort(url.getPort());
		detailInfo.setTimeout(url.getParameter(NewsConstants.SYNC_TIMEOUT_KEY,NewsConstants.DEFUALT_SYNC_TIMEOUT));
		detailInfo.setVersion(url.getParameter(NewsConstants.SYNC_VERSION_KEY));
		
		discoveryServiceInfo.getDiscoveryServiceDetailInfoList().add(detailInfo);
		return discoveryServiceInfo;
	}

	/**
	 * 构建发现服务详细信息
	 * @param discoveryServiceInfo 服务发现信息
	 * @param url 从注册中心获取的注册信息
	 * @param providerSyncVersion 提供者的服务版本
	 */
	private void buildDetailInfo(DiscoveryServiceInfo discoveryServiceInfo, URL url, String providerSyncVersion) {
		DiscoveryServiceDetailInfo detailInfo = new DiscoveryServiceDetailInfo();
		detailInfo.setServiceIp(url.getHost());
		detailInfo.setServicePort(url.getPort());
		detailInfo.setTimeout(url.getParameter(NewsConstants.SYNC_TIMEOUT_KEY, NewsConstants.DEFUALT_SYNC_TIMEOUT));
		detailInfo.setVersion(providerSyncVersion);
		detailInfo.setPid(url.getParameter(NewsConstants.PID,-1));
		discoveryServiceInfo.getDiscoveryServiceDetailInfoList().add(detailInfo);
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
	
	/**
	 * 通过通知服务列表删除缓存服务列表,并返回缓存详细信息列表
	 * @param cacheServiceInfo 缓存服务列表
	 * @param notifyServiceInfo 通知服务列表
	 * @return
	 */
	private List<DiscoveryServiceDetailInfo> deleteCacheServiceByNotifyService(DiscoveryServiceInfo cacheServiceInfo,DiscoveryServiceInfo notifyServiceInfo) {
		//要被删除的服务列表
		Set<DiscoveryServiceDetailInfo> deletedDetailInfoSet = new HashSet<>();
		//得到通知详细服务的key
		String notifydetailInfoServiceKey = NewsRpcUtil.getDetailInfoServiceKey(notifyServiceInfo);
		List<DiscoveryServiceDetailInfo> cacheDetailInfoList = cacheServiceInfo.getDiscoveryServiceDetailInfoList();
		for (DiscoveryServiceDetailInfo cacheDetailInfo : cacheDetailInfoList) {
			//得到缓存详细服务的key
			String cacheDetailInfoServiceKey = NewsRpcUtil.getDetailInfoServiceKey(cacheServiceInfo, cacheDetailInfo);
			if(cacheDetailInfoServiceKey.equals(notifydetailInfoServiceKey)){
				deletedDetailInfoSet.add(cacheDetailInfo);
			}
		}
		//从缓存中删除要被删除的服务列表
		for (DiscoveryServiceDetailInfo deletedDetailInfo : deletedDetailInfoSet) {
			cacheDetailInfoList.remove(deletedDetailInfo);
		}
		return cacheDetailInfoList;
	}
	
	/**
	 * 找将要被删除的缓存,并将其剔除,同时返回 拷贝的 缓存服务列表(但不包括 willBeDeleted(将要被剔除的缓存))
	 * @param subscribeCacheKey
	 * @param willBeDeleted 将要被剔除的缓存
	 */
	private DiscoveryServiceInfo willBeDeletedCache(String subscribeCacheKey,DiscoveryServiceInfo willBeDeleted){
		//记录要被剔除的缓存列表
		Set<DiscoveryServiceDetailInfo> willBeDeletedCacheSet = new HashSet<>();
		//从缓存中获取满足条件的缓存
		DiscoveryServiceInfo cacheServiceInfo = TimedOverdueCache.get(subscribeCacheKey, DiscoveryServiceInfo.class);
		if(cacheServiceInfo != null && cacheServiceInfo.getDiscoveryServiceDetailInfoList().size()>0){
			//得到缓存的服务列表
			List<DiscoveryServiceDetailInfo> cacheServiceDetailInfoList = cacheServiceInfo.getDiscoveryServiceDetailInfoList();
			if(willBeDeleted != null && willBeDeleted.getDiscoveryServiceDetailInfoList().size()>0){
				//得到将要被剔除的服务列表
				List<DiscoveryServiceDetailInfo> willBeDeletedDetailInfoList = willBeDeleted.getDiscoveryServiceDetailInfoList();
				for (DiscoveryServiceDetailInfo willBeDeletedDetailInfo : willBeDeletedDetailInfoList) {
					//得到将要被剔除服务的key
					String willBeDeletedDetailInfoServiceKey = NewsRpcUtil.getDetailInfoServiceKey(willBeDeleted, willBeDeletedDetailInfo);
					for (DiscoveryServiceDetailInfo cacheServiceDetailInfo : cacheServiceDetailInfoList) {
						//得到缓存服务的key
						String cacheDetailInfoServiceKey = NewsRpcUtil.getDetailInfoServiceKey(cacheServiceInfo, cacheServiceDetailInfo);
						//若缓存中的服务与将要被剔除服务的key相同,则失败数加一
						if(cacheDetailInfoServiceKey.equals(willBeDeletedDetailInfoServiceKey)){
							cacheServiceDetailInfo.setFailNumber(cacheServiceDetailInfo.getFailNumber()+1);
							//若当前缓存的失败数大于等于最大失败数,则将其在缓存中剔除
							if(cacheServiceDetailInfo.getFailNumber() >= maxFailNumber){
								willBeDeletedCacheSet.add(cacheServiceDetailInfo);
							}
						}
					}
				}
			}
			
			//剔除被记录要被剔除的缓存服务
			for (DiscoveryServiceDetailInfo willBeDeletedCache : willBeDeletedCacheSet) {
				cacheServiceDetailInfoList.remove(willBeDeletedCache);
			}
		}else{
			return null;
		}
		//拷贝 DiscoveryServiceInfo
		return copyCacheDiscoveryServiceInfo(cacheServiceInfo, willBeDeleted);
	}

	/**
	 * 拷贝 DiscoveryServiceInfo
	 * @param cacheServiceInfo
	 * @param willBeDeleted
	 * @return
	 */
	private DiscoveryServiceInfo copyCacheDiscoveryServiceInfo(DiscoveryServiceInfo cacheServiceInfo,DiscoveryServiceInfo willBeDeleted) {
		//拷贝缓存服务
		DiscoveryServiceInfo copyCacheServiceInfo = new DiscoveryServiceInfo();
		copyCacheServiceInfo.setClientTopic(cacheServiceInfo.getClientTopic());
		copyCacheServiceInfo.setClientTag(cacheServiceInfo.getClientTag());
		copyCacheServiceInfo.setServiceClass(cacheServiceInfo.getServiceClass());
		//拷贝缓存服务详细信息列表
		List<DiscoveryServiceDetailInfo> cacheDetailInfoList = cacheServiceInfo.getDiscoveryServiceDetailInfoList();
		if(cacheDetailInfoList.size()>0 && willBeDeleted != null && willBeDeleted.getDiscoveryServiceDetailInfoList().size()>0){
			for (DiscoveryServiceDetailInfo willBeDeletedDetailInfo : willBeDeleted.getDiscoveryServiceDetailInfoList()) {
				//得到将要被剔除服务的key
				String willBeDeletedDetailInfoServiceKey = NewsRpcUtil.getDetailInfoServiceKey(willBeDeleted, willBeDeletedDetailInfo);
				for (DiscoveryServiceDetailInfo  cacheDetailInfo : cacheDetailInfoList) {
					String cacheDetailInfoServiceKey = NewsRpcUtil.getDetailInfoServiceKey(cacheServiceInfo, cacheDetailInfo);
					List<DiscoveryServiceDetailInfo> copyDetailInfoList = copyCacheServiceInfo.getDiscoveryServiceDetailInfoList();
					//若缓存服务可以与将要被剔除的服务可以不等且拷贝缓存服务列表中不包含该缓存服务详细信息,则进行拷贝
					if(!cacheDetailInfoServiceKey.equals(willBeDeletedDetailInfoServiceKey) && !copyDetailInfoList.contains(cacheDetailInfo) ){
						copyCacheServiceInfo.getDiscoveryServiceDetailInfoList().add(cacheDetailInfo);
					}
				}
			}
		}else{
			copyCacheServiceInfo.getDiscoveryServiceDetailInfoList().addAll(cacheDetailInfoList);
		}
		return copyCacheServiceInfo;
	}
}
