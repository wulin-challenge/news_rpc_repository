package com.bjhy.news.rpc.api.netty.proxy;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
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
import com.bjhy.news.common.heartbeat.telnet.TelnetHeartbeat;
import com.bjhy.news.common.heartbeat.telnet.TelnetHeartbeatInfo;
import com.bjhy.news.common.mock.MockService;
import com.bjhy.news.common.util.NewsRpcUtil;
import com.bjhy.news.common.zookeeper.DiscoveryZkService;
import com.bjhy.news.rpc.api.netty.domain.NettyRpcType;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import cn.wulin.brace.remoting.RemotingException;
import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.brace.utils.ThreadFactoryImpl;
import cn.wulin.ioc.Constants;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

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
	
	/**
	 * 远程心跳channel
	 */
	private ConcurrentHashMap<Channel, RpcResponse> telnetHeartbeat = new ConcurrentHashMap<Channel, RpcResponse>();
	
	/**
	 * 本地心跳channel
	 */
	private ConcurrentHashMap<cn.wulin.brace.remoting.Channel, Integer> nativeHeartbeat = new ConcurrentHashMap<cn.wulin.brace.remoting.Channel, Integer>();
	
	
	private ScheduledExecutorService heartbeatScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl("HeartbeatHandler"));
	
	private void heartbeat() throws InterruptedException, ExecutionException, TimeoutException {
		Collection<DiscoveryServiceInfo> values = newsRpcProviders.values();
		List<String> heartbeatMsgs = new ArrayList<String>();
		
		if(values.size() == 0) {
			heartbeatMsgs.add("当前没有任何提供者!");
		}
		
		for (DiscoveryServiceInfo serviceInfo : values) {
			StringBuilder heartbeatMsg = new StringBuilder("topic:"+serviceInfo.getClientTopic());
			heartbeatMsg.append(",tag:"+serviceInfo.getClientTag()+"\r\n");
			
			for (DiscoveryServiceDetailInfo detailInfo : serviceInfo.getDiscoveryServiceDetailInfoList()) {
				
				try {
					heartbeatMsg.append("    ");
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
				
					heartbeatMsg.append(newsConnect.clientIp()+":"+NewsRpcUtil.getPid()+">");
					heartbeatMsg.append(detailInfo.getServiceIp()+":"+detailInfo.getServicePort()+":"+detailInfo.getPid());
					
					Channel channel = NettyRpcClient.getInstance().getChannel(request);
					if(channel != null) {
						RPCFuture sendRequest = NettyRpcClient.getInstance().getClientHandler().sendRequest(channel, request);
						RpcResponse response = (RpcResponse) sendRequest.get(detailInfo.getTimeout(), TimeUnit.MILLISECONDS);
						
						if(response == null || response.getResult()  == null || (Integer)response.getResult() != 2) {
							LoggerUtils.error("心跳检测失败: "+serviceInfo);
							heartbeatMsg.append(" 失败!");
						}else {
							heartbeatMsg.append(" 成功!");
						}
					}
				} catch (Throwable e) {
					LoggerUtils.error("心跳检测失败: "+serviceInfo+",错误信息:"+e.getMessage());
					heartbeatMsg.append(" 失败!,错误信息:"+e.getMessage());
				}
				heartbeatMsg.append("\r\n");
			}
			heartbeatMsgs.add(heartbeatMsg.toString());
		}
		
		//发送telnet心跳消息
		sendRemoteHeartbeat(heartbeatMsgs);
		// 发送本地心跳消息
		sendNativeHeartbeat(heartbeatMsgs);
	}
	
	/**
	 * 发送本地心跳消息
	 * @param heartbeatMsgs
	 */
	private void sendNativeHeartbeat(List<String> heartbeatMsgs) {
		Enumeration<cn.wulin.brace.remoting.Channel> keys = nativeHeartbeat.keys();
		
		while(keys.hasMoreElements()) {
			cn.wulin.brace.remoting.Channel channel = keys.nextElement();
			Integer max = nativeHeartbeat.get(channel);
			nativeHeartbeat.put(channel, (max-1));
			
			List<String> msgs = new ArrayList<String>();
			for (String msg : heartbeatMsgs) {
				msgs.add(0,"["+max+"] "+msg);
			}
			
			if((max-1)<=0) {
				nativeHeartbeat.remove(channel);
				sendNativeeHeartbeat(channel,msgs,true);
			}else {
				sendNativeeHeartbeat(channel,msgs,false);
			}
		}
	}
	
	private void sendNativeeHeartbeat(cn.wulin.brace.remoting.Channel channel, List<String> msgs, boolean isEnd) {
		if(channel != null && msgs != null && msgs.size()>0) {
			String prompt = channel.getUrl().getParameter(Constants.PROMPT_KEY, Constants.DEFAULT_PROMPT);
			for (String msg : msgs) {
				try {
					
					channel.send("\r\n"+msg);
				} catch (RemotingException e) {
					LoggerUtils.error("telnet 处理客户端心跳失败! 详细信息: "+e.getMessage());
				}
			}
			
			if(isEnd) {
				try {
					channel.send("\r\n"+prompt+"心跳结束!");
					channel.send("\r\n"+prompt);
				} catch (RemotingException e) {
					LoggerUtils.error("telnet 处理客户端心跳失败! 详细信息: "+e.getMessage());
				}
			}
		}
	}
	
	/**
	 * 发送telnet心跳消息
	 * @param heartbeatMsgs
	 */
	private void sendRemoteHeartbeat(List<String> heartbeatMsgs) {
		Enumeration<Channel> keys = telnetHeartbeat.keys();
		while(keys.hasMoreElements()) {
			Channel channel = keys.nextElement();
			RpcResponse response = telnetHeartbeat.get(channel);
			
			Object result = response.getResult();
			if(result != null && result instanceof TelnetHeartbeatInfo) {
				TelnetHeartbeatInfo telnetHeartbeatInfo = (TelnetHeartbeatInfo) result;
				int max = telnetHeartbeatInfo.getResult();
				telnetHeartbeatInfo.setResult((max-1));
				
				List<String> mags = new ArrayList<String>();
				for (String msg : heartbeatMsgs) {
					mags.add(0,"["+max+"] "+msg);
				}
				
				if((max-1)<=0) {
					telnetHeartbeat.remove(channel);
					sendRemoteHeartbeat(response.getRequestId(),channel,mags,true);
				}else {
					sendRemoteHeartbeat(response.getRequestId(),channel,mags,false);
				}
			}
		}
	}
	
	private void sendRemoteHeartbeat(String requestId,Channel channel,List<String> heartbeatMsgs,boolean isEnd) {
		RpcRequest request = new RpcRequest();
		request.setRequestId(UUID.randomUUID().toString());
		
		if(channel.remoteAddress() instanceof InetSocketAddress) {
			InetSocketAddress remoteAddress = (InetSocketAddress)channel.remoteAddress();
			
			request.setHost(remoteAddress.getHostName());
			request.setPort(remoteAddress.getPort());
		}
		
		
		request.setInterfaceName(TelnetHeartbeat.class.getName());
		request.setMethodName("acceptTelnetHeartbeat");
		request.setParameters(new Object[] {requestId,isEnd,heartbeatMsgs});
		request.setParameterTypes(new Class[] {String.class,boolean.class,List.class,});
		request.setTimeout(3000);
		request.setRpcType(NettyRpcType.TELNET_SERVICE);
		request.setServiceVersion("");
		request.setClientId(newsConnect.clientId());
		request.setClientName(newsConnect.clientName());
		request.setClientPid(NewsRpcUtil.getPid());
		
		if(channel != null) {
			try {
				Channel channel2 = NettyRpcClient.getInstance().getChannel(channel);
				RPCFuture sendRequest = NettyRpcClient.getInstance().getClientHandler().sendRequest(channel2, request);
				RpcResponse response = (RpcResponse) sendRequest.get(3000, TimeUnit.MILLISECONDS);
				
				Object result = response.getResult();
				if(result instanceof TelnetHeartbeatInfo) {
					TelnetHeartbeatInfo info = (TelnetHeartbeatInfo) result;
					if(info.getResult() != 0) {
						LoggerUtils.error("发送telnet心跳检测失败: "+request);
					}
				}else {
					LoggerUtils.error("发送telnet心跳检测失败: "+request);
				}
					
			} catch (Throwable e) {
				LoggerUtils.error("心跳检测失败: "+request+",错误信息:"+e.getMessage());
			}
		}
	}
	
	/**
	 * 添加远程心跳channel信息
	 * @param ctx
	 * @param response
	 */
	public void addRemoteHeartbeat(ChannelHandlerContext ctx, RpcResponse response) {
		Channel channel = ctx.channel();
		telnetHeartbeat.put(channel,response);
	}
	
	/**
	 * 添加本地心跳channel信息
	 * @param channel
	 * @param max
	 */
	public void addNativeHeartbeat(cn.wulin.brace.remoting.Channel channel, Integer max) {
		nativeHeartbeat.put(channel,max);
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
				} catch (Throwable e) {
					LoggerUtils.error("更新提供者服务出错!",e);
				}
			}
			
		}, 5, 30, TimeUnit.SECONDS);
	
		heartbeatScheduledExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					heartbeat();
				} catch (Throwable e) {
					LoggerUtils.error("心跳检测时出错!",e);
				}
			}
			
		}, 5, 30, TimeUnit.SECONDS);
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
