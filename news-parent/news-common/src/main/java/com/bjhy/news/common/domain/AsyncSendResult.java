package com.bjhy.news.common.domain;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * 异步发送结果
 * @author wubo
 *
 */
public class AsyncSendResult {
	
	/**
	 * 发送状态
	 */
	private AsyncSendStatus sendStatus;
	
	/**
	 * 失败原因
	 */
	private Throwable failureCause;
	
	private SendResult sendResult;

	public AsyncSendStatus getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(AsyncSendStatus sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Throwable getFailureCause() {
		return failureCause;
	}

	public void setFailureCause(Throwable failureCause) {
		this.failureCause = failureCause;
	}

	public SendResult getSendResult() {
		return sendResult;
	}

	public void setSendResult(SendResult sendResult) {
		this.sendResult = sendResult;
	}
}
