package com.bjhy.news.common.notify;

import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

/**
 * 通知监听
 * @author wubo
 *
 */
@SPI
public interface NotifyListener {
	
	/**
	 * url必须有一个category参数表示执行的类型
	 * @param url 
	 * @throws NewsRpcException
	 */
	@Adaptive
	void notify(URL url);

}
