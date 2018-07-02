package com.bjhy.news.common.rocketmq;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import javax.sql.rowset.spi.TransactionalWriter;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl;
import org.apache.rocketmq.client.impl.producer.TopicPublishInfo;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.AsyncSendResult;
import com.bjhy.news.common.domain.AsyncSendStatus;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.domain.RocketmqNewsType;
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
	
	private int maxMessageSize=1024*1024*4;
	private int sendMsgTimeout=10000;
	
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
	 * 普通顺序消息
	 * @param topic
	 * @param tag
	 * @param data
	 * @param timeout
	 * @return
	 */
	public AsyncSendResult orderSyncSend(String topic,String tag,byte[] data,int timeout){
		AsyncSendResult asyncSendResult = new AsyncSendResult();
		Message msg = new Message(topic, tag,data);
		
		try {
			SendResult sendResult = mqProducer.send(msg, new MessageQueueSelector(){
				@Override
				public MessageQueue select(List<MessageQueue> mqs, Message msg, Object index) {
					return mqs.get((int)index);
				}
			}, newsConnect.rocketmqOrderQueue(),timeout);
			
			asyncSendResult.setSendStatus(AsyncSendStatus.SUCCESS);
			asyncSendResult.setSendResult(sendResult);
		} catch (MQClientException e) {
			
			e.printStackTrace();
			asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
		} catch (RemotingException e) {
			asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
			e.printStackTrace();
		} catch (MQBrokerException e) {
			asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
			e.printStackTrace();
		} catch (InterruptedException e) {
			asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
			e.printStackTrace();
		}
		return asyncSendResult;
	}
	
	/**
	 * 普通异步发送
	 * @param topic
	 * @param tag
	 * @param data
	 * @param timeout
	 */
	public AsyncSendResult genenalAsyncSend(String topic,String tag,byte[] data,int timeout){
		
		Message msg = new Message(topic, tag,data);
		
		CountDownLatch count = new CountDownLatch(1);
		AsyncSendResult asyncSendResult = new AsyncSendResult();
		try {
			mqProducer.send(msg, new SendCallback(){
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
			}, timeout);
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
	
    private TopicPublishInfo tryToFindTopicPublishInfo(final String topic) {
    	DefaultMQProducerImpl defaultMQProducerImpl = mqProducer.getDefaultMQProducerImpl();
        TopicPublishInfo topicPublishInfo = defaultMQProducerImpl.getTopicPublishInfoTable().get(topic);
        if (null == topicPublishInfo || !topicPublishInfo.ok()) {
        	defaultMQProducerImpl.getTopicPublishInfoTable().putIfAbsent(topic, new TopicPublishInfo());
        	defaultMQProducerImpl.getmQClientFactory().updateTopicRouteInfoFromNameServer(topic);
            topicPublishInfo = defaultMQProducerImpl.getTopicPublishInfoTable().get(topic);
        }

        if (topicPublishInfo.isHaveTopicRouterInfo() || topicPublishInfo.ok()) {
            return topicPublishInfo;
        } else {
        	defaultMQProducerImpl.getmQClientFactory().updateTopicRouteInfoFromNameServer(topic, true, mqProducer);
            topicPublishInfo = defaultMQProducerImpl.getTopicPublishInfoTable().get(topic);
            return topicPublishInfo;
        }
    }
	
	/**
	 * 普通同步步发送
	 * @param topic
	 * @param tag
	 * @param data
	 * @param timeout
	 */
	public AsyncSendResult genenalSyncSend(String topic,String tag,byte[] data,int timeout){
		
		Message msg = new Message(topic, tag,data);
		AsyncSendResult asyncSendResult = new AsyncSendResult();
		try {
			TopicPublishInfo topicPublishInfo = tryToFindTopicPublishInfo(topic);
			List<MessageQueue> messageQueueList = topicPublishInfo.getMessageQueueList();
			int index = ThreadLocalRandom.current().nextInt(messageQueueList.size());
			MessageQueue messageQueue = messageQueueList.get(index);
			
			SendResult sendResult = mqProducer.send(msg, messageQueue,timeout);
			asyncSendResult.setSendStatus(AsyncSendStatus.SUCCESS);
			asyncSendResult.setSendResult(sendResult);
		} catch (MQClientException e) {
			e.printStackTrace();
			asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
		} catch (RemotingException e) {
			e.printStackTrace();
			asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
		} catch (InterruptedException e) {
			e.printStackTrace();
			asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
		} catch (MQBrokerException e) {
			e.printStackTrace();
			asyncSendResult.setSendStatus(AsyncSendStatus.FAILURE);
		}
		return asyncSendResult;
	}
	
	/**
	 * 得到默认的mq生产者
	 * @return
	 */
	public DefaultMQProducer getDefaultMQProducer(){
		return mqProducer;
	}
	
	/**
	 * 连接rocketmq生产值
	 */
	private void connectRocketmqProducer(){
		String rocketmqAddress = newsConnect.rocketmqAddress();
		//组装消费组
    	String producerGroup = NewsConstants.NEWS_ROCKETMQ_GROUP_PRODUCER+"_"+getTopic()+"_"+newsConnect.clientTag();
		if(newsConnect.rocketmqIsUniqueGroup()){
			producerGroup = producerGroup+"_"+getUUID();
		}
		mqProducer = new DefaultMQProducer(producerGroup);
		mqProducer.setNamesrvAddr(rocketmqAddress);
		mqProducer.setMaxMessageSize(maxMessageSize);
		mqProducer.setSendMsgTimeout(sendMsgTimeout);
		try {
			mqProducer.start();
		} catch (MQClientException e) {
			logger.error("rocketmq生产者连接失败!",e);
		}
	}
	
	/**
	 * 得到topic
	 * @return
	 */
	private String getTopic(){
		//订阅PushTopic下Tag为push的消息
    	String version = StringUtils.isBlank(newsConnect.rocketmqPublishVersion())?"":newsConnect.rocketmqPublishVersion();
    	String topic = newsConnect.rocketmqPublishEnvironment()+"_"+newsConnect.clientTopic()+"_"+version;
    	topic = topic.replaceAll("\\.", "__");
    	return topic;
	}
	
	/**
	 * 连接rocketmq消费者
	 */
	private void connectRocketmqConsumer(){
		String rocketmqAddress = newsConnect.rocketmqAddress();
    	//组装消费组
    	String consumerGroup = NewsConstants.NEWS_ROCKETMQ_GROUP_CONSUMER+"_"+getTopic()+"_"+newsConnect.clientTag();
		if(newsConnect.rocketmqIsUniqueGroup()){
			consumerGroup = consumerGroup+"_"+getUUID();
		}
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(rocketmqAddress);
        try {
            consumer.subscribe(getTopic(), newsConnect.clientTag());
            consumer.setConsumeThreadMin(Runtime.getRuntime().availableProcessors());
            consumer.setConsumeThreadMax(Runtime.getRuntime().availableProcessors()+5);
            consumer.setConsumeMessageBatchMaxSize(Runtime.getRuntime().availableProcessors());
           /**
            * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
            * 如果非第一次启动，那么按照上次消费的位置继续消费
            */
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            //普通消息
            if(newsConnect.rocketmqNewsType() == RocketmqNewsType.GENERAL){
            	consumer.registerMessageListener(this.new GeneralMessageListener());
            	
            //顺序消息
            }else if(newsConnect.rocketmqNewsType() == RocketmqNewsType.ORDER){
            	consumer.registerMessageListener(this.new OrderMessageListener());
            	
            //事物消息
            }else if(newsConnect.rocketmqNewsType() == RocketmqNewsType.TRANSACTIONAL){
            	//TODO 暂时没有实现
            	throw new RuntimeException("事物消息暂未实现!!");
            }
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private class OrderMessageListener implements MessageListenerOrderly{

		@Override
		@SuppressWarnings("deprecation")
		public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
			for (MessageExt msg : msgs) {
        		try {
        			byte[] data = msg.getBody();
        			if(data == null){
        				logger.error("当前消息为空,拒绝再次消费!消息Id:"+msg.getMsgId());
        				return ConsumeOrderlyStatus.SUCCESS;
        			}
        			RocketmqRequest request = SerializationUtil.deserialize(data, RocketmqRequest.class);
					handle(request);
				} catch (Exception e) {
					logger.error("处理业务逻辑失败,消息Id:"+msg.getMsgId(),e);
					return ConsumeOrderlyStatus.ROLLBACK;
				}
            }
            return ConsumeOrderlyStatus.SUCCESS;
		}
		
	}
	
	/**
	 * 普通消息监听
	 * @author wubo
	 *
	 */
	private class GeneralMessageListener implements MessageListenerConcurrently{
		@Override
		public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
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
	
	public static String getUUID(){
		return UUID.randomUUID().toString().toUpperCase().replace("-", "");
	}

}
