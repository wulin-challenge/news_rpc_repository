package com.bjhy.news.api.rocketmq.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.AsyncSendResult;
import com.bjhy.news.common.domain.AsyncSendStatus;
import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.domain.NewsResult;
import com.bjhy.news.common.domain.RocketmqRequest;
import com.bjhy.news.common.proxy.RemoteAsyncProxy;
import com.bjhy.news.common.proxy.RemoteProxy;
import com.bjhy.news.common.rocketmq.RocketmqConfig;
import com.bjhy.news.common.util.SerializationUtil;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

@SuppressWarnings("unused")
public class RocketmqRemoteProxy implements RemoteProxy{
	
	private Logger logger = LoggerFactory.getLogger(RocketmqRemoteProxy.class);

	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	
	@Override
	public <T> T remoteInvoke(URL url, DiscoveryServiceInfo discoveryServiceInfo, Class<T> clazz) {
		return create(clazz, discoveryServiceInfo);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T create(final Class<?> interfaceClass, final DiscoveryServiceInfo discoveryServiceInfo) {
		// 创建动态代理对象
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object[] params = (Object[]) args[1];
						Class<?>[] paramType = new Class<?>[params.length];
						for (int i = 0; i < paramType.length; i++) {
							paramType[i] = params[i].getClass();
						}
						// 创建 RPC 请求对象并设置请求属性
						RocketmqRequest request = new RocketmqRequest();
						request.setRequestId(UUID.randomUUID().toString());
						request.setInterfaceName(discoveryServiceInfo.getServiceClass().getName());
						request.setMethodName((String)args[0]);
						request.setParameterTypes(paramType);
						request.setParameters(params);
						return send(request,discoveryServiceInfo);
					}
				});
	}
	
	public AsyncSendResult send(RocketmqRequest request,DiscoveryServiceInfo discoveryServiceInfo){
		DiscoveryServiceDetailInfo detailInfo = discoveryServiceInfo.getDiscoveryServiceDetailInfoList().get(0);
		
	 	String version = StringUtils.isBlank(newsConnect.rocketmqPublishVersion())?"":newsConnect.rocketmqPublishVersion();
    	String topic = newsConnect.rocketmqPublishEnvironment()+"_"+discoveryServiceInfo.getClientTopic()+"_"+version;
    	topic = topic.replaceAll("\\.", "__");
    	byte[] data = SerializationUtil.serialize(request);
		Message msg = new Message(topic, discoveryServiceInfo.getClientTag(),data);
		
		CountDownLatch count = new CountDownLatch(1);
		AsyncSendResult asyncSendResult = new AsyncSendResult();
		try {
			RocketmqConfig.getInstance().getDefaultMQProducer().send(msg, new SendCallback(){
				@Override
				public void onSuccess(SendResult sendResult) {
					asyncSendResult.setSendStatus(AsyncSendStatus.SUCCESS);
					asyncSendResult.setSendResult(sendResult);
					count.countDown();
				}

				@Override
				public void onException(Throwable e) {
					asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
					count.countDown();
				}
			}, detailInfo.getTimeout());
			count.await();
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return asyncSendResult;
	}
}
