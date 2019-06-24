package com.bjhy.news.rpc.api.netty.telnet;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.handler.RpcServerHandler;

import cn.wulin.brace.remoting.Channel;
import cn.wulin.brace.remoting.RemotingException;
import cn.wulin.brace.remoting.telnet.TelnetHandler;
import cn.wulin.brace.remoting.telnet.support.Help;
import cn.wulin.brace.remoting.telnet.support.TelnetUtils;
import cn.wulin.ioc.extension.Activate;

@Activate
@Help(parameter = "", summary = "client detail.", detail = "client detail.")
public class ClientTelnetHandler  implements TelnetHandler {

	@Override
	public String telnet(Channel channel, String message) throws RemotingException {
		ConcurrentHashMap<io.netty.channel.Channel, RpcRequest> clientChannel = RpcServerHandler.getClientChannel();
		
		List<String> header = new ArrayList<String>();
		header.add("client ip");
		header.add("client port");
		header.add("client pid");
		header.add("client id");
		header.add("client name");
		//为了避免最后一行出现计算错误的情况
		header.add("");
		
		List<List<String>> table = new ArrayList<List<String>>();
		
		Set<Entry<io.netty.channel.Channel, RpcRequest>> entrySet = clientChannel.entrySet();
		
		for (Entry<io.netty.channel.Channel, RpcRequest> entry : entrySet) {
			io.netty.channel.Channel key = entry.getKey();
			RpcRequest request = entry.getValue();
			List<String> row = new ArrayList<String>();
			InetSocketAddress remoteAddress = (InetSocketAddress) key.remoteAddress();
			row.add(remoteAddress.getAddress().getHostAddress());
			row.add(Integer.toString(remoteAddress.getPort()));
			row.add(Integer.toString(request.getClientPid()));
			row.add(request.getClientId() == null?"":request.getClientId());
			row.add(request.getClientName() == null?"":request.getClientName());
			row.add("");
			table.add(row);
		}
		
		return TelnetUtils.toTable(header, table);
	}

}
