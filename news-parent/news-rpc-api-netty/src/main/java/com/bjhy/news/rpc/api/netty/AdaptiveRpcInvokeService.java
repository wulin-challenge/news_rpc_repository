package com.bjhy.news.rpc.api.netty;

import java.util.List;

import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.service.RpcInvokeService;

import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

@Adaptive
public class AdaptiveRpcInvokeService implements RpcInvokeService{
	
	private static volatile String DEFAULT_RPC_INVOKE;

	public static void setDefaultRpcInvokeService(String rpcInvoke) {
		DEFAULT_RPC_INVOKE = rpcInvoke;
	}

	/**
	 * 得到真正的消息rpc连接
	 * @return
	 */
	private RpcInvokeService getRpcInvokeService() {
		RpcInvokeService rpcInvoke;
		InterfaceExtensionLoader<RpcInvokeService> loader = InterfaceExtensionLoader.getExtensionLoader(RpcInvokeService.class);
		String name = DEFAULT_RPC_INVOKE; // copy reference
		if (name != null && name.length() > 0) {
			rpcInvoke = loader.getExtension(name);
		} else {
			rpcInvoke = loader.getDefaultExtension();
		}
		return rpcInvoke;
	}

	@Override
	public void executeRpc(List<PublishServiceInfo> publishServiceInfoList) {
		getRpcInvokeService().executeRpc(publishServiceInfoList);
	}

}
