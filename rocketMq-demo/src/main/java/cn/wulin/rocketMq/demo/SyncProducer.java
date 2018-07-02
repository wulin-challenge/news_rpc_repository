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
        DefaultMQProducer producer = new DefaultMQProducer("news_default_rocketmq_group");
        producer.setNamesrvAddr("192.168.0.107:9876;192.168.0.108:9876");
        try {
            producer.start();

            Message msg = new Message("test_5109_1__1", "appId", "1", "哈哈哈!".getBytes());
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
