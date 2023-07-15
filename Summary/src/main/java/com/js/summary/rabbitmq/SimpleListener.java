package com.js.summary.rabbitmq;


import com.js.common.rabbitmq.RabbitMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {
    @RabbitListener(queues = RabbitMqConfig.BUSINESS_QUEUE)
    public void businessListener(Message message){
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        System.out.println("正常消费");
    }
    @RabbitListener(queues = RabbitMqConfig.DIE_QUEUE)
    public void dieListener(Message message){
        System.out.println(message);
        System.out.println(new String(message.getBody()));
        System.out.println("私信队列消费");
    }
    @RabbitListener(queues = RabbitMqConfig.SIMPLE_QUEUE)
    public void simpleListener(Message message){
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }
    @RabbitListener(queues = RabbitMqConfig.FANOUT_QUEUE1)
    public void fanoutQueue1Listener(Message message){
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }
    @RabbitListener(queues = RabbitMqConfig.FANOUT_QUEUE2)
    public void fanoutQueue2Listener(Message message){
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }
    @RabbitListener(queues = RabbitMqConfig.TOPIC_QUEUE1)
    public void topicQueue1Listener(Message message){
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }
    @RabbitListener(queues = RabbitMqConfig.TOPIC_QUEUE2)
    public void topicQueue2Listener(Message message){
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }
}
