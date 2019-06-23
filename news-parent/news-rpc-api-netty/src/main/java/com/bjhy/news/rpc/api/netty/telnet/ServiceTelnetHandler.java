package com.bjhy.news.rpc.api.netty.telnet;

import java.util.ArrayList;
import java.util.List;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.zookeeper.RegistryZkService;

import cn.wulin.brace.remoting.Channel;
import cn.wulin.brace.remoting.RemotingException;
import cn.wulin.brace.remoting.telnet.TelnetHandler;
import cn.wulin.brace.remoting.telnet.support.Help;
import cn.wulin.brace.remoting.telnet.support.TelnetUtils;
import cn.wulin.ioc.extension.Activate;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * 查看所有服务的处理器
 * @author wulin
 *
 */
@Activate
@Help(parameter = "", summary = "service detail.", detail = "service detail.")
public class ServiceTelnetHandler implements TelnetHandler {
	
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	

	@Override
	public String telnet(Channel channel, String message) throws RemotingException {
		List<PublishServiceInfo> cachePublishServiceInfo = RegistryZkService.getInstance().getCachePublishServiceInfo();
		
//		String prompt = channel.getUrl().getParameter(Constants.PROMPT_KEY, Constants.DEFAULT_PROMPT);
		
		if("".equals(message)) {
			List<String> header = new ArrayList<String>();
			header.add("service name");
			header.add("service topic");
			header.add("service tag");
			header.add("service ip");
			header.add("service timeout");
			header.add("service version");
			
			//为了避免最后一行出现计算错误的情况
			header.add("");
			
			List<List<String>> table = new ArrayList<List<String>>();
			
			for (PublishServiceInfo publishServiceInfo : cachePublishServiceInfo) {
				List<String> row = new ArrayList<String>();
				
				row.add(publishServiceInfo.getServiceClass().getName());
				row.add(newsConnect.clientTopic());
				row.add(newsConnect.clientTag());
				row.add(newsConnect.clientIp());
				row.add(Integer.toString(publishServiceInfo.getSyncTimeout()));
				row.add(publishServiceInfo.getSyncVersion()==null?"":publishServiceInfo.getSyncVersion());
				row.add("");
				table.add(row);
			}
			
			return TelnetUtils.toTable(header, table);
		}
		// 返回" " 是为了避免telnet客户端不出现brace> 情况
		return " ";
	}

}
