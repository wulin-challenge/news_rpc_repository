package com.bjhy.news.common.heartbeat.telnet;

import java.util.List;

import cn.wulin.brace.remoting.Channel;
import cn.wulin.ioc.extension.SPI;

/**
 * 使用telnet连接客户端的心跳接口
 * @author ulin
 *
 */
@SPI("telnetHeartbeatServer")
public interface TelnetHeartbeat {

	/**
	 * 接收处理telnet心跳
	 * @param requestId 请求Id
	 * @param isEnd 心跳是否结束,true: 表示结束,false:没有结束
	 * @param msg 消息
	 */
	TelnetHeartbeatInfo acceptTelnetHeartbeat(String requestId,boolean isEnd,List<String> msgs);
	
	/**
	 * 添加telnet的channel
	 * @param requestId 请求Id
	 * @param channel telnet的channel
	 */
	void addTelnetChannel(String requestId,Channel channel);
}
