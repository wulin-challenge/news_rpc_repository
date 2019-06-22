package com.bjhy.news.common.connect;

import com.bjhy.news.common.init.ClientCommunicationInitializing;
import com.bjhy.news.common.init.NewsInitializing;
import com.bjhy.news.common.rocketmq.RocketmqConfig;
import com.bjhy.news.common.service.RpcInvokeService;
import com.bjhy.news.common.zookeeper.RegistryZkService;
import com.bjhy.news.common.zookeeper.ZookeeperConfig;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * 连接config
 * @author wubo
 */
public class ConnectConfig {
	private static ConnectConfig connectConfig;
	
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	
	private ConnectConfig(){}
	
	/**
	 * 得到连接配置信息
	 */
	public void connectEntry(URL url){
		NewsInitializing newsInitializing = InterfaceExtensionLoader.getExtensionLoader(NewsInitializing.class).getAdaptiveExtension();
		ClientCommunicationInitializing cci = InterfaceExtensionLoader.getExtensionLoader(ClientCommunicationInitializing.class).getAdaptiveExtension();
		newsInitializing.initBefore(newsConnect);
		//连接zookeeper
		ZookeeperConfig.getInstance().connectZk();
		
		//发布服务
		RegistryZkService.getInstance().publishService();
		
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				//当远程服务调用rpc过来时,处理远程请求
				RpcInvokeService rpcInvokeService = InterfaceExtensionLoader.getExtensionLoader(RpcInvokeService.class).getAdaptiveExtension();
				rpcInvokeService.executeRpc(RegistryZkService.getInstance().getCachePublishServiceInfo());
			}
		});
		thread.start();
		
		//初始化通信客户端
		cci.init(url, newsConnect);
		//连接rocketmq
		RocketmqConfig.getInstance().connectRocketmq();
		newsInitializing.initAfter(newsConnect);
	}
	
	public static ConnectConfig getInstance(){
		if(connectConfig == null){
			connectConfig = new ConnectConfig();
		}
		return connectConfig;
	}

}
