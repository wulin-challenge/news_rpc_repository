package com.bjhy.news.common.proxy;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

/**
 * 清除rpc客户端缓存接口
 * @author wubo
 */
@SPI("netty_rpc_client")
public interface RemoveRpcClient {

	/**
	 * 清除rpc客户端过期缓存
	 * @param url 通用的url参数
	 */
	@Adaptive
	void cleanRpcClient(URL url);
}
