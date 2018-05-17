package com.bjhy.news.common.init;

import com.bjhy.news.common.connect.NewsConnect;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

/**
 * 客户端通信初始化
 * @author wubo
 *
 */
@SPI("netty")
public interface ClientCommunicationInitializing {

	@Adaptive("client.communication.initializing")
	void init(URL url,NewsConnect newsConnect);
}
