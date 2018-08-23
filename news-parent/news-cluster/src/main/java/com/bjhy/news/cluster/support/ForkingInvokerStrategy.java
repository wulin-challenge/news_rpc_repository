package com.bjhy.news.cluster.support;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;
import com.bjhy.news.common.util.NamedThreadFactory;
import com.bjhy.news.common.util.NewsConstants;
import com.bjhy.news.common.zookeeper.DiscoveryZkService;

import cn.wulin.ioc.URL;

/**
 * 调用时 并行调用策略
 * 并行调用，只要一个成功即返回，通常用于实时性要求较高的操作，但需要浪费更多服务资源。
 * @author wubo
 */
public class ForkingInvokerStrategy extends AbstractInvokerStrategyCluster{
	private final ExecutorService executor = Executors.newCachedThreadPool(new NamedThreadFactory("forking-cluster-timer", true));


	@Override
	public Object doInvoke(URL url, Class<?> interfaceClass,Object proxy, Method method, Object[] args) throws NewsRpcException {
		Integer timeout = url.getParameter(NewsConstants.SYNC_TIMEOUT_KEY,0);
		timeout = timeout <=0?NewsConstants.DEFUALT_SYNC_TIMEOUT:timeout;
		
		
		DiscoveryServiceInfo allProviders = DiscoveryZkService.getInstance().subscribeService(
				url.getParameter(NewsConstants.CLIENT_TOPIC_KEY),
				url.getParameter(NewsConstants.CLIENT_TAG_KEY),
				interfaceClass, 
				url.getParameter(NewsConstants.SYNC_VERSION_KEY),
				null);
		if(allProviders == null || allProviders.getDiscoveryServiceDetailInfoList().size()==0){
			throw new NewsRpcException("没有找到可用的提供者!");
		}
		
		List<DiscoveryServiceDetailInfo> detailInfoList = allProviders.getDiscoveryServiceDetailInfoList();
		
		final AtomicInteger count = new AtomicInteger();
        final BlockingQueue<Object> ref = new LinkedBlockingQueue<Object>();
        for (final DiscoveryServiceDetailInfo detailInfo : detailInfoList) {
        	//得到最大的超时时间
        	if(detailInfo.getTimeout() != null && detailInfo.getTimeout()>0){
        		timeout = detailInfo.getTimeout()>timeout?detailInfo.getTimeout():timeout;
        	}
        	
        	//拷贝一份新服务列表
			DiscoveryServiceInfo selected = DiscoveryZkService.getInstance().copyNewDiscoveryServiceInfo(allProviders, detailInfo);
            executor.execute(new Runnable() {
                public void run() {
                    try {
                    	Object result = remoteInvoke(url, interfaceClass, selected, proxy, method, args);
                        ref.offer(result);
                    } catch (Throwable e) {
                        int value = count.incrementAndGet();
                        if (value >= detailInfoList.size()) {
                            ref.offer(e);
                        }
                    }
                }
            });
		}
        try {
        	//最大超时时间+1000毫秒,是为了保证上述的远程调用都成功返回
            Object ret = ref.poll(timeout+1000, TimeUnit.MILLISECONDS);
            if (ret instanceof Throwable) {
                Throwable e = (Throwable) ret;
                throw new NewsRpcException(e.getMessage(),e);
            }
            return ret;
        } catch (InterruptedException e) {
            throw new NewsRpcException("条用线程被中断!",e);
        }
	}
}
