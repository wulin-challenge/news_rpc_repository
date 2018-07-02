package cn.wulin.rocketMq.demo;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class SyncConsumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("news_default_rocketmq_group");
        consumer.setNamesrvAddr("192.168.0.80:9876");
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("PushTopic", "push||pull");
           /**
            * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
            * 如果非第一次启动，那么按照上次消费的位置继续消费
            */
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//            consumer.sendMessageBack(msg, delayLevel);
            consumer.registerMessageListener(
                    new MessageListenerConcurrently() {
                        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext Context) {
                            for (Message msg : msgs) {
                            	
                            	MessageExt msgExt = (MessageExt) msg;
//                            	System.out.println(msg.new String(msg.getBody())); 
                                System.out.println(new String(msgExt.getBody()) + ":" + msgExt.toString());
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
}
