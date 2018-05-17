package cn.wulin.rocketMq.demo;
import java.util.List;

import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class SyncProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.0.80:9876");
//        QueryResult queryMessage = producer.queryMessage("", "", 100000, 11L, 11L);
//        List<MessageExt> messageList = queryMessage.getMessageList();
        try {
            producer.start();

            Message msg = new Message("PushTopic", "push", "1", "哈哈哈!".getBytes());
//            new Message
            producer.send(msg, new SendCallback(){
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.println(sendResult.getMsgId());
				}

				@Override
				public void onException(Throwable e) {
					
				}
            }, 1000*60);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
