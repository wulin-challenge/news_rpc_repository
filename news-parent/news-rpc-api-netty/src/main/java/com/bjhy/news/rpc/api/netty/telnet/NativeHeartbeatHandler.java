package com.bjhy.news.rpc.api.netty.telnet;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.ProviderConsumerType;
import com.bjhy.news.common.util.NewsRpcUtil;
import com.bjhy.news.rpc.api.netty.proxy.HeartbeatHandler;

import cn.wulin.brace.remoting.Channel;
import cn.wulin.brace.remoting.RemotingException;
import cn.wulin.brace.remoting.telnet.TelnetHandler;
import cn.wulin.brace.remoting.telnet.support.Help;
import cn.wulin.ioc.extension.Activate;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.StringUtils;

@Activate
@Help(parameter = "[max]", summary = "client heartbeat detail.", detail = "client heartbeat detail.")
public class NativeHeartbeatHandler implements TelnetHandler{
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	@Override
	public String telnet(Channel channel, String message) throws RemotingException {
		
		ProviderConsumerType providerConsumerType = ProviderConsumerType.getProviderConsumerTypeByCode(newsConnect.providerConsumer().trim());
		
		if(ProviderConsumerType.PROVIDER == providerConsumerType) {
			return "当前服务为提供者端,没有心跳信息!";
		}
		
		int max = StringUtils.isBlank(message)?5:Integer.parseInt(message);
		HeartbeatHandler.getInstance().addNativeHeartbeat(channel, max);
		
		String msg = "将监控 本地进程 pid:"+NewsRpcUtil.getPid()+" 的客户端进行 "+max+" 次心跳情况";
		channel.send(msg);
		return null;
	}

}
