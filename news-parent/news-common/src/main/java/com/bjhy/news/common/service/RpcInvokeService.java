package com.bjhy.news.common.service;

import java.util.List;

import com.bjhy.news.common.domain.PublishServiceInfo;

import cn.wulin.ioc.extension.SPI;

/**
 * 当远程同步调用rpc方法过来时,就执行该service
 * @author wubo
 *
 */
@SPI("default.rpcinvoke.service")
public interface RpcInvokeService extends InvokeService{
	
	/**
	 * 当远程同步调用rpc方法过来时,就执行该service的这方法
	 * @param publishServiceInfoList
	 */
	void executeRpc(List<PublishServiceInfo> publishServiceInfoList);

}
