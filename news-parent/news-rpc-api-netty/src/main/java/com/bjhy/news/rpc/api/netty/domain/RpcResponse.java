package com.bjhy.news.rpc.api.netty.domain;

import java.io.Serializable;

/**
 * 封装 RPC 响应
 *
 * @author huangyong
 * @since 1.0.0
 */
public class RpcResponse  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String requestId;
    private Exception exception;
    private Object result;
    
    /**
     * rpc调用类型
     */
    private NettyRpcType rpcType = NettyRpcType.USER_SERVICE;

    public boolean hasException() {
        return exception != null;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

	public NettyRpcType getRpcType() {
		return rpcType;
	}

	public void setRpcType(NettyRpcType rpcType) {
		this.rpcType = rpcType;
	}
}
