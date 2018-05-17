package com.bjhy.news.common.init;

import com.bjhy.news.common.connect.NewsConnect;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

/**
 * 消息rpc初始化接口
 * @author wubo
 *
 */
@SPI
public interface NewsInitializing {
	
	/**
	 * 初始化前
	 */
	void initBefore(NewsConnect newsConnect);
	
	/**
	 * 初始化后
	 */
	void initAfter(NewsConnect newsConnect);
	
}
