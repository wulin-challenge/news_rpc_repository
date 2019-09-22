package com.bjhy.news.rpc.api.netty.init;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.ProviderConsumerType;
import com.bjhy.news.common.init.ClientCommunicationInitializing;
import com.bjhy.news.rpc.api.netty.proxy.HeartbeatHandler;
import com.bjhy.news.rpc.api.netty.proxy.NettyRpcClient;

import cn.wulin.ioc.URL;

/**
 * netty通信客户端初始化
 * @author wulin
 *
 */
public class NettyClientCommunicationInitializing implements ClientCommunicationInitializing{
	
	@Override
	public void init(URL url, NewsConnect newsConnect) {
		NettyRpcClient.getInstance().createNettyClient();
		
		ProviderConsumerType providerConsumerType = ProviderConsumerType.getProviderConsumerTypeByCode(newsConnect.providerConsumer().trim());
		if(ProviderConsumerType.CONSUMER == providerConsumerType || ProviderConsumerType.BOTH == providerConsumerType) {
			HeartbeatHandler.getInstance();
		}
	}
}
