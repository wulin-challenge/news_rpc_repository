package com.bjhy.news.common.zookeeper;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.util.NewsConstants;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * 注册zk服务
 * @author wubo
 */
public class RegistryZkService {
	private static RegistryZkService registryZkService;
	
	/**
	 * 将发布的信息缓存起来
	 */
	private List<PublishServiceInfo> cachePublishServiceInfo = new ArrayList<>();
	
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	/**
	 * 发布服务
	 */
	public void publishService(){
		PublishService publishService = InterfaceExtensionLoader.getExtensionLoader(PublishService.class).getAdaptiveExtension();
		List<PublishServiceInfo> publishServiceInfoList = publishService.getPublishServiceInfo();
		cachePublishServiceInfo.addAll(publishServiceInfoList);
		
		//注册zk服务
		registerZkService(publishServiceInfoList);
	}
	
	/**
	 * 注册zk服务
	 * @param publishServiceInfo
	 */
	private void registerZkService(List<PublishServiceInfo> publishServiceInfoList){
		ZookeeperConfig zookeeperConfig = ZookeeperConfig.getInstance();
		for (PublishServiceInfo psInfo : publishServiceInfoList) {
			String registerPath = getRegisterPath(psInfo);
			zookeeperConfig.createNode(registerPath, CreateMode.PERSISTENT);
			createLeafNode(registerPath, psInfo);;
		}
	}
	
	/**
	 * 得到注册路径
	 * @param psInfo 服务发布信息
	 * @return
	 */
	private String getRegisterPath(PublishServiceInfo psInfo){
		StringBuffer registerPath = new StringBuffer(NewsConstants.ZK_ROOT_NODE);
		registerPath.append("/"+newsConnect.clientTopic());
		registerPath.append("/"+newsConnect.clientTag());
		registerPath.append("/"+psInfo.getServiceClass().getName());
		return registerPath.toString();
	}
	
	/**
	 * 创建叶子节点并设置值
	 * @param registerPath
	 * @param psInfo
	 */
	private void createLeafNode(String registerPath,PublishServiceInfo psInfo){
		ZookeeperConfig zookeeperConfig = ZookeeperConfig.getInstance();
		
		registerPath = registerPath+"/"+NewsConstants.ZK_EPHEMERAL_NODE_PREFIX;
		
		Map<String,String> params = new HashMap<>();
		String syncVersion = StringUtils.isBlank(psInfo.getSyncVersion())?"":psInfo.getSyncVersion();
		params.put(NewsConstants.SYNC_VERSION_KEY, syncVersion);
		params.put(NewsConstants.SYNC_TIMEOUT_KEY, psInfo.getSyncTimeout().toString());
		
		URL url = new URL(NewsConstants.REGISTER_PROTOCOL_KEY, newsConnect.clientIp(), newsConnect.clientPort(),params);
		String fullString = url.toFullString();
		
		registerPath = zookeeperConfig.createNode(registerPath, CreateMode.EPHEMERAL_SEQUENTIAL);
		zookeeperConfig.setNodeData(registerPath, fullString.getBytes(Charset.defaultCharset()));
	}
	
	public static RegistryZkService getInstance(){
		if(registryZkService == null){
			registryZkService = new RegistryZkService();
		}
		return registryZkService;
	}

	public List<PublishServiceInfo> getCachePublishServiceInfo() {
		return cachePublishServiceInfo;
	}

}
