package com.bjhy.news.rpc.api.netty.proxy;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.mock.MockService;
import com.bjhy.news.common.util.NewsRpcUtil;
import com.bjhy.news.common.zookeeper.DiscoveryZkService;
import com.bjhy.news.rpc.api.netty.domain.NettyRpcType;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.brace.utils.ThreadFactoryImpl;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import io.netty.channel.Channel;

/**
 * 心跳检测
 * @author wulin
 *
 */
public class HeartbeatHandler {
	private static HeartbeatHandler heartbeatHandler;
	
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	/**
	 * 所用的news rpc 提供者
	 */
	private ConcurrentHashMap<String,DiscoveryServiceInfo> newsRpcProviders = new ConcurrentHashMap<String,DiscoveryServiceInfo>();
	
	private ScheduledExecutorService heartbeatScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl("HeartbeatHandler"));
	
	private void heartbeat() throws InterruptedException, ExecutionException, TimeoutException {
		Collection<DiscoveryServiceInfo> values = newsRpcProviders.values();
		for (DiscoveryServiceInfo serviceInfo : values) {
			DiscoveryServiceDetailInfo detailInfo = serviceInfo.getDiscoveryServiceDetailInfoList().get(0);
			RpcRequest request = new RpcRequest();
			request.setRequestId(UUID.randomUUID().toString());
			request.setHost(detailInfo.getServiceIp());
			request.setPort(detailInfo.getServicePort());
			request.setInterfaceName(MockService.class.getName());
			request.setMethodName("echo");
			request.setParameters(new Object[] {1});
			request.setParameterTypes(new Class[] {Integer.class});
			request.setTimeout(detailInfo.getTimeout());
			request.setRpcType(NettyRpcType.MOCK_SERVICE);
			request.setServiceVersion("");
			request.setClientId(newsConnect.clientId());
			request.setClientName(newsConnect.clientName());
			request.setClientPid(NewsRpcUtil.getPid());
			Channel channel = NettyRpcClient.getInstance().getChannel(request);
			RPCFuture sendRequest = NettyRpcClient.getInstance().getClientHandler().sendRequest(channel, request);
			RpcResponse response = (RpcResponse) sendRequest.get(detailInfo.getTimeout(), TimeUnit.SECONDS);
			
			if(response == null || response.getResult()  == null || (Integer)response.getResult() != 2) {
				LoggerUtils.error("心跳检测失败: "+request);
			}
		}
	}
	
	public static HeartbeatHandler getInstance() {
		if(heartbeatHandler == null) {
			synchronized(HeartbeatHandler.class) {
				if(heartbeatHandler == null) {
					heartbeatHandler = new HeartbeatHandler();
					heartbeatHandler.startScheduled();
				}
			}
		}
		return heartbeatHandler;
	}
	
	/**
	 * 开始定时器
	 */
	private void startScheduled() {
		heartbeatScheduledExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					updateProviders();
				} catch (Exception e) {
					LoggerUtils.error("更新提供者服务出错!",e);
				}
			}
			
		}, 5, 30, TimeUnit.SECONDS);
	
		heartbeatScheduledExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					heartbeat();
				} catch (Exception e) {
					LoggerUtils.error("心跳检测时出错!",e);
				}
			}
			
		}, 5, 60, TimeUnit.SECONDS);
	}
	
	/**
	 * 更新提供者服务列表
	 */
	private void updateProviders() {
        List<DiscoveryServiceInfo> discoveryServiceInfoList = DiscoveryZkService.getInstance().getAllDiscoveryServiceInfoList(false);
        
        newsRpcProviders.clear();
		if(discoveryServiceInfoList == null || discoveryServiceInfoList.size() == 0) {
			return;
		}
		
		for (DiscoveryServiceInfo discoveryServiceInfo : discoveryServiceInfoList) {
			
			if(discoveryServiceInfo.getServiceClass() != MockService.class) {
				continue;
			}
			
			DiscoveryServiceDetailInfo mockDetailInfo = discoveryServiceInfo.getDiscoveryServiceDetailInfoList().get(0);
			
			if(NewsRpcUtil.getPid() == mockDetailInfo.getPid()) {
				continue;
			}
			
			StringBuilder key = new StringBuilder();
			key.append(discoveryServiceInfo.getClientTopic());
			key.append(":"+discoveryServiceInfo.getClientTag());
			key.append(":"+mockDetailInfo.getServiceIp());
			key.append(":"+mockDetailInfo.getServicePort());
			newsRpcProviders.put(key.toString(), discoveryServiceInfo);
		}
	}

}
