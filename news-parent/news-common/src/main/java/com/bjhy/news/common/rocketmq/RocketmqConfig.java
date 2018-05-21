package com.bjhy.news.common.rocketmq;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.domain.RocketmqRequest;
import com.bjhy.news.common.util.NewsConstants;
import com.bjhy.news.common.util.SerializationUtil;
import com.bjhy.news.common.zookeeper.RegistryZkService;

import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * rocketmq配置
 * @author wubo
 */
public class RocketmqConfig {
	
	private Logger logger = LoggerFactory.getLogger(RocketmqConfig.class);
	private static RocketmqConfig rocketmqConfig;
	
	private DefaultMQProducer mqProducer = null;
	
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	private RocketmqConfig(){}
	
	/**
	 * 连接rocketmq
	 */
	public void connectRocketmq(){
		connectRocketmqConsumer();//连接rocketmq消费者
		connectRocketmqProducer();//连接rocketmq生产值
	}
	
	/**
	 * 连接rocketmq生产值
	 */
	private void connectRocketmqProducer(){
		String rocketmqAddress = newsConnect.rocketmqIp()+":"+newsConnect.rocketmqPort();
		mqProducer = new DefaultMQProducer(NewsConstants.NEWS_DEFAULT_ROCKETMQ_GROUP);
		mqProducer.setNamesrvAddr(rocketmqAddress);
		try {
			mqProducer.start();
		} catch (MQClientException e) {
			logger.error("rocketmq生产者连接失败!",e);
		}
	}
	
	/**
	 * 得到默认的mq生产者
	 * @return
	 */
	public DefaultMQProducer getDefaultMQProducer(){
		return mqProducer;
	}
	
	/**
	 * 连接rocketmq消费者
	 */
	private void connectRocketmqConsumer(){
		String rocketmqAddress = newsConnect.rocketmqIp()+":"+newsConnect.rocketmqPort();
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(NewsConstants.NEWS_DEFAULT_ROCKETMQ_GROUP);
        consumer.setNamesrvAddr(rocketmqAddress);
        try {
            //订阅PushTopic下Tag为push的消息
        	String version = StringUtils.isBlank(newsConnect.rocketmqPublishVersion())?"":newsConnect.rocketmqPublishVersion();
        	String topic = newsConnect.rocketmqPublishEnvironment()+"_"+newsConnect.clientTopic()+"_"+version;
        	topic = topic.replaceAll("\\.", "__");
            consumer.subscribe(topic, newsConnect.clientTag());
           /**
            * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
            * 如果非第一次启动，那么按照上次消费的位置继续消费
            */
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(
                    new MessageListenerConcurrently() {
                        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext Context) {
                        	for (MessageExt msg : msgs) {
                        		try {
                        			byte[] data = msg.getBody();
                        			if(data == null){
                        				logger.error("当前消息为空,拒绝再次消费!消息Id:"+msg.getMsgId());
                        				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        			}
                        			RocketmqRequest request = SerializationUtil.deserialize(data, RocketmqRequest.class);
									handle(request);
								} catch (Exception e) {
									logger.error("处理业务逻辑失败!稍后将会再次尝试处理,但最多只会尝试处理16次,消息Id:"+msg.getMsgId(),e);
									return ConsumeConcurrentlyStatus.RECONSUME_LATER;
								}
                            }
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    }
            );
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
    private Object handle(RocketmqRequest request) throws Exception {
    	List<PublishServiceInfo> publishServiceInfoList = RegistryZkService.getInstance().getCachePublishServiceInfo();
        // 获取服务对象
        String serviceName = request.getInterfaceName();
        
        Object serviceBean = getBeanObject(serviceName, publishServiceInfoList);
        if (serviceBean == null) {
            throw new RuntimeException(String.format("can not find service bean by key: %s", serviceName));
        }
        // 获取反射调用所需的参数
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();
        // 使用 CGLib 执行反射调用
        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        return serviceFastMethod.invoke(serviceBean, parameters);
    }
    
	/**
	 * 通过 服务名和 版本得到bean对象
	 * @param serviceName 服务名
	 * @param version 版本
	 * @return
	 */
	private Object getBeanObject(String serviceName,List<PublishServiceInfo> publishServiceInfoList){
		for (PublishServiceInfo publishServiceInfo : publishServiceInfoList) {
			String name = publishServiceInfo.getServiceClass().getName();
			if(serviceName.equals(name)){
				return publishServiceInfo.getServiceImplObject();
			}
		}
		return null;
	}
	
	public static RocketmqConfig getInstance(){
		if(rocketmqConfig == null){
			rocketmqConfig = new RocketmqConfig();
		}
		return rocketmqConfig;
	}

}