package com.bjhy.news.rpc.api.netty.telnet;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.bjhy.news.common.heartbeat.telnet.TelnetHeartbeat;
import com.bjhy.news.common.heartbeat.telnet.TelnetHeartbeatInfo;
import com.bjhy.news.rpc.api.netty.domain.NettyRpcType;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;
import com.bjhy.news.rpc.api.netty.handler.RpcServerHandler;

import cn.wulin.brace.remoting.Channel;
import cn.wulin.brace.remoting.RemotingException;
import cn.wulin.brace.remoting.telnet.TelnetHandler;
import cn.wulin.brace.remoting.telnet.support.Help;
import cn.wulin.brace.remoting.telnet.support.TelnetUtils;
import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.ioc.Constants;
import cn.wulin.ioc.extension.Activate;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.StringUtils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

@Activate
@Help(parameter = "[heartbeat sortId max]", summary = "client detail.", detail = "client detail.")
public class ClientTelnetHandler  implements TelnetHandler {

	@Override
	public String telnet(Channel channel, String message) throws RemotingException {
		String prompt = channel.getUrl().getParameter(Constants.PROMPT_KEY, Constants.DEFAULT_PROMPT);
		Map<io.netty.channel.Channel, RpcRequest> clientChannels = RpcServerHandler.getClientChannel();
		
		if(StringUtils.isBlank(message)) {
			return showClients(clientChannels);
		}else {
			String[] queueArray = message.split(" ");
			String heartbeat = queueArray.length>=1?queueArray[0]:"";
			Integer sortId = queueArray.length>=2?Integer.parseInt(queueArray[1]):0;
			Integer max = queueArray.length>=3?Integer.parseInt(queueArray[2]):5;
			
			if("heartbeat".equals(heartbeat)) {
				Set<Entry<io.netty.channel.Channel, RpcRequest>> entrySet = clientChannels.entrySet();
				
				int i=0;
				for (Entry<io.netty.channel.Channel, RpcRequest> entry : entrySet) {
					if(sortId == i) {
						io.netty.channel.Channel clientChannel = entry.getKey();
						RpcRequest value = entry.getValue();
						String requestId = UUID.randomUUID().toString();
						
						RpcResponse response = new RpcResponse();
						response.setRequestId(requestId);
						response.setResult(new TelnetHeartbeatInfo(0, max));
						response.setRpcType(NettyRpcType.TELNET_SERVICE);
						
						TelnetHeartbeat telnetHeartbeat = InterfaceExtensionLoader.getExtensionLoader(TelnetHeartbeat.class).getAdaptiveExtension();
						telnetHeartbeat.addTelnetChannel(requestId, channel);
						
						clientChannel.writeAndFlush(response).addListener(new ChannelFutureListener() {
			                    @Override
			                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
			                        LoggerUtils.debug("Send response for request " + requestId);
			                    }
			                });
						InetSocketAddress remoteAddress = (InetSocketAddress) clientChannel.remoteAddress();
						String msg = "将监控 ip:"+remoteAddress.getHostName()+",pid:"+value.getClientPid()+" 的客户端进行 "+max+" 次心跳情况";
						channel.send(msg);
						return null;
					}
					i++;
				}
				return prompt+"输入的序号值不存在!";
			}else {
				return prompt+"输入参数错误,请重新输入!";
			}
		}
	}

	/**
	 * 显示客户端
	 * @param clientChannel
	 * @return
	 */
	private String showClients(Map<io.netty.channel.Channel, RpcRequest> clientChannel) {
		List<String> header = new ArrayList<String>();
		header.add("sortId");
		header.add("client ip");
		header.add("client port");
		header.add("client pid");
		header.add("client id");
		header.add("client name");
		//为了避免最后一行出现计算错误的情况
		header.add("");
		
		List<List<String>> table = new ArrayList<List<String>>();
		
		Set<Entry<io.netty.channel.Channel, RpcRequest>> entrySet = clientChannel.entrySet();
		
		int i = 0;
		for (Entry<io.netty.channel.Channel, RpcRequest> entry : entrySet) {
			io.netty.channel.Channel key = entry.getKey();
			RpcRequest request = entry.getValue();
			List<String> row = new ArrayList<String>();
			InetSocketAddress remoteAddress = (InetSocketAddress) key.remoteAddress();
			row.add(Integer.toString(i));
			row.add(remoteAddress.getAddress().getHostAddress());
			row.add(Integer.toString(remoteAddress.getPort()));
			row.add(Integer.toString(request.getClientPid()));
			row.add(request.getClientId() == null?"":request.getClientId());
			row.add(request.getClientName() == null?"":request.getClientName());
			row.add("");
			table.add(row);
			i++;
		}
		
		return TelnetUtils.toTable(header, table);
	}

}
