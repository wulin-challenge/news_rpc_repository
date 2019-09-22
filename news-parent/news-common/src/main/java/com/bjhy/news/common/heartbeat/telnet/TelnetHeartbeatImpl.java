package com.bjhy.news.common.heartbeat.telnet;

import java.util.List;
import java.util.WeakHashMap;

import cn.wulin.brace.remoting.Channel;
import cn.wulin.brace.remoting.RemotingException;
import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.ioc.Constants;
import cn.wulin.ioc.extension.Adaptive;

/**
 * 处理客户端发送过来的心跳
 * @author wulin
 *
 */
@Adaptive
public class TelnetHeartbeatImpl implements TelnetHeartbeat{
	
	private static final WeakHashMap<String/*requestId*/, Channel/*telnet 的 channel*/> telnetChannel = new WeakHashMap<String/*requestId*/, Channel/*telnet 的 channel*/>();

	@Override
	public TelnetHeartbeatInfo acceptTelnetHeartbeat(String requestId, boolean isEnd, List<String> msgs) {
		Channel channel = telnetChannel.get(requestId);
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
				telnetChannel.remove(requestId);
			}
		}
		
		
		return new TelnetHeartbeatInfo(1, 0);
	}

	@Override
	public void addTelnetChannel(String requestId, Channel channel) {
		telnetChannel.put(requestId, channel);
	}

}
