package com.bjhy.news.common.zookeeper;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.ProviderConsumerType;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.heartbeat.telnet.TelnetHeartbeat;
import com.bjhy.news.common.mock.MockService;
import com.bjhy.news.common.mock.MockServiceImpl;
import com.bjhy.news.common.util.NewsConstants;
import com.bjhy.news.common.util.NewsRpcUtil;

import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.UrlUtils;

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
		
		//设置是提供者还是消费者属性
		setProviderConsumer();
		
		publishMockService();//发布mock服务
		
		publishTelnetHeartbeatService();//发布telnet心跳服务

		//注册zk服务
		registerZkService(cachePublishServiceInfo);
	}
	
	/**
	 * 设置是提供者还是消费者属性
	 */
	private void setProviderConsumer() {
		String providerConsumer = newsConnect.providerConsumer();
		if(cachePublishServiceInfo.size() == 0) {
			if(StringUtils.isBlank(providerConsumer)) {
				newsConnect.setProviderConsumer(ProviderConsumerType.CONSUMER.getCode());
			}
			return;
		}
		
		if(StringUtils.isBlank(providerConsumer)) {
			newsConnect.setProviderConsumer(ProviderConsumerType.PROVIDER.getCode());
		}
	}
	
	/**
	 * 发布mock服务
	 */
	private void publishMockService() {
		if(cachePublishServiceInfo.size() == 0) {
			return;
		}
		
		PublishServiceInfo publishServiceInfo = new PublishServiceInfo();
		publishServiceInfo.setServiceClass(MockService.class);
		publishServiceInfo.setServiceImplObject(new MockServiceImpl());
		publishServiceInfo.setSyncTimeout(3000);
		publishServiceInfo.setSyncVersion("");
		cachePublishServiceInfo.add(publishServiceInfo);
	}
	
	/**
	 * 发布telnet心跳服务
	 */
	private void publishTelnetHeartbeatService() {
		if(cachePublishServiceInfo.size() == 0) {
			return;
		}
		TelnetHeartbeat telnetHeartbeat = InterfaceExtensionLoader.getExtensionLoader(TelnetHeartbeat.class).getAdaptiveExtension();
		
		PublishServiceInfo publishServiceInfo = new PublishServiceInfo();
		publishServiceInfo.setServiceClass(TelnetHeartbeat.class);
		publishServiceInfo.setServiceImplObject(telnetHeartbeat);
		publishServiceInfo.setSyncTimeout(5000);
		publishServiceInfo.setSyncVersion("");
		cachePublishServiceInfo.add(publishServiceInfo);
	}
	
	/**
	 * 重新发布服务
	 */
	public void republishService(){
		//失败重连
		List<PublishServiceInfo> publishServiceInfoList = RegistryZkService.getInstance().getCachePublishServiceInfo();
		if(publishServiceInfoList != null && publishServiceInfoList.size()>0){
			RegistryZkService.getInstance().registerZkService(publishServiceInfoList);
		}
	}
	
	/**
	 * 注册zk服务,实现幂等性注册,即向注册中心注册一次与注册多次是一样的
	 * @param publishServiceInfo
	 */
	public void registerZkService(List<PublishServiceInfo> publishServiceInfoList){
		ZookeeperConfig zookeeperConfig = ZookeeperConfig.getInstance();
		for (PublishServiceInfo psInfo : publishServiceInfoList) {
			String registerPath = getRegisterPath(psInfo);
			zookeeperConfig.createNode(registerPath, CreateMode.PERSISTENT);
			//检测当前服务是否已经注册,true:表示已经注册,false:表示没有注册
			boolean checkIsRegister = checkIsRegister(registerPath);
			if(!checkIsRegister){
				createLeafNode(registerPath, psInfo);
			}
		}
	}
	
	/**
	 * 检测当前服务是否已经注册,true:表示已经注册,false:表示没有注册
	 * @param registerPath 服务的注册路径
	 * @return
	 */
	private boolean checkIsRegister(String registerPath){
		ZookeeperConfig zookeeperConfig = ZookeeperConfig.getInstance();
		List<String> childrenFullPathList = zookeeperConfig.getChildrenFullPathList(registerPath);
		if(childrenFullPathList != null && childrenFullPathList.size()>0){
			for (String childrenFullPath : childrenFullPathList) {
				byte[] nodeData = zookeeperConfig.getNodeData(childrenFullPath);
				String urlString = new String(nodeData,Charset.defaultCharset());
				URL url = UrlUtils.parseURL(urlString, null);
				
				//当前主机ip
				String registerIp = url.getHost().trim();
				String localIp = newsConnect.clientIp().trim();
				//端口号
				int registerPort = url.getPort();
				int localPort = newsConnect.clientPort();
				//pid
				int registerPid = url.getParameter(NewsConstants.PID, -1);
				int localPid = NewsRpcUtil.getPid();
				if((registerPid == localPid) &&(registerIp.equals(localIp)) && (registerPort == localPort)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 得到注册路径
	 * @param psInfo 服务发布信息
	 * @return
	 */
	private String getRegisterPath(PublishServiceInfo psInfo){
		StringBuffer registerPath = new StringBuffer(NewsRpcUtil.getZkRootNode());
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
		//创建临时序列节点
		registerPath = registerPath+"/"+NewsConstants.ZK_EPHEMERAL_NODE_PREFIX;
		registerPath = zookeeperConfig.createNode(registerPath, CreateMode.EPHEMERAL_SEQUENTIAL);
		
		Map<String,String> params = new HashMap<>();
		String syncVersion = StringUtils.isBlank(psInfo.getSyncVersion())?"":psInfo.getSyncVersion();
		params.put(NewsConstants.SYNC_VERSION_KEY, syncVersion);
		params.put(NewsConstants.SYNC_TIMEOUT_KEY, psInfo.getSyncTimeout().toString());
		
		//组装url节点的完整信息
		String[] pathArray = registerPath.substring(1).split("/");
		URL url = new URL(NewsConstants.REGISTER_PROTOCOL_KEY, newsConnect.clientIp(), newsConnect.clientPort(),params);
		url = url.addParameter(NewsConstants.PID, NewsRpcUtil.getPid());
		url = url.addParameter(NewsRpcUtil.getZkRootNode(), pathArray[0]+"/"+pathArray[1]);
		url = url.addParameter(NewsConstants.DEFAULT_INTERFACE_GROUP, pathArray[1]);
		url = url.addParameter(NewsConstants.CLIENT_TOPIC_KEY, pathArray[2]);
		url = url.addParameter(NewsConstants.CLIENT_TAG_KEY, pathArray[3]);
		url = url.addParameter(NewsConstants.SERVICE_INTERFACE_KEY, pathArray[4]);
		url = url.addParameter(NewsConstants.ZK_EPHEMERAL_NODE_KEY, pathArray[5]);
		String fullString = url.toFullString();
		
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
