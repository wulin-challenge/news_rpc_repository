package com.bjhy.news.rpc.api.netty.test;

import java.util.concurrent.ExecutionException;

import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.proxy.RPCFuture;

public class RPCFutureTest {
	
	public static void main(String[] args) {
		RPCFutureTest futureTest = new RPCFutureTest();
		futureTest.testRpcFutureBlockingStatus();
	}

	public void testRpcFutureBlockingStatus() {
		
		RpcRequest request = new RpcRequest();
		RPCFuture rpcFuture = new RPCFuture(request);
		
		try {
			Object object = rpcFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
