package cn.wulin.rocketMq.demo.pull_push;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import com.bjhy.news.common.domain.RocketmqRequest;
import com.bjhy.news.common.util.SerializationUtil;

public class TestRocketmqPull {

	private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();
	 
	 
    public static void main(String[] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("news_default_rocketmq_group");
        consumer.setNamesrvAddr("192.168.0.107:9876;192.168.0.108:9876");
        consumer.start();
        //获取订阅topic的queue
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("test_5109_1__1");
        int i = 1;
        while(i==1){
            for (MessageQueue mq : mqs) {
                System.out.println("Consume from the queue: " + mq);
                SINGLE_MQ:
                while (true) {
                    try {//阻塞的拉去消息，中止时间默认20s
                    	System.out.println();
                        PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                        System.out.println(Thread.currentThread().getName()+new Date()+""+pullResult);
                        putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                        consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());;
                        writeMessage(pullResult);
                        
                        switch (pullResult.getPullStatus()) {
                            case FOUND://pullSataus
                                break;
                            case NO_MATCHED_MSG:
                                break;
                            case NO_NEW_MSG:
                                break SINGLE_MQ;
                            case OFFSET_ILLEGAL:
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

 
        consumer.shutdown();
    }

	private static void writeMessage(PullResult pullResult) {
		List<MessageExt> msgFoundList = pullResult.getMsgFoundList();
		if(msgFoundList != null && msgFoundList.size()>0){
			for (MessageExt msg : msgFoundList) {
				try {
					byte[] data = msg.getBody();
					if(data != null){
						RocketmqRequest request = SerializationUtil.deserialize(data, RocketmqRequest.class);
						Object[] parameters = request.getParameters();
						for (Object object : parameters) {
							System.out.println(object);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
 
    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offseTable.get(mq);
        if (offset != null)
            return offset;
 
        return 0;
    }
 
    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offseTable.put(mq, offset);
    }

}
