package com.bjhy.news.common.proxy;

import com.bjhy.news.common.domain.AsyncSendResult;

/**
 * 远程异步代理
 * @author wubo
 *
 */
public interface RemoteAsyncProxy {

	/**
	 * 异步调用
	 * @param methodName
	 * @param args
	 * @return
	 */
	AsyncSendResult asyncInvoke(String methodName, Object... args);
}
