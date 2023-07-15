package com.js.common.rabbitmq;

import com.js.common.rabbitmq.RabbitMqQueueNameEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitMqConfig {
    public static final String SIMPLE_QUEUE = "simple_queue";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String FANOUT_QUEUE1 = "fanout_queue1";
    public static final String FANOUT_QUEUE2 = "fanout_queue2";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String TOPIC_QUEUE1 = "topic_queue1";
    public static final String TOPIC_QUEUE2 = "topic_queue2";
    public static final String DIE_EXCHANGE = "die_exchange";
    public static final String DIE_QUEUE = "die_queue";
    public static final String BUSINESS_QUEUE = "business_queue";
    public static final String BUSINESS_EXCHANGE = "business_exchange";

    @Bean(BUSINESS_QUEUE)
    public Queue businessQueue(){
        Map<String, Object> map = new HashMap<>();
        // x-dead-letter-exchange：这里声明当前队列绑定的死信交换机
        map.put("x-dead-letter-exchange",DIE_EXCHANGE);
        // x-dead-letter-routing-key：这里声明当前队列的死信路由 key
        map.put("x-dead-letter-routing-key", "");
//        map.put("x-max-length", 3);
        map.put("x-message-ttl", 10000);
        return new Queue(BUSINESS_QUEUE,true,false,false,map);
    }
    @Bean(BUSINESS_EXCHANGE)
    public FanoutExchange businessExchange(){
        return new FanoutExchange(BUSINESS_EXCHANGE, true,false);
    }
    @Bean
    public Binding businessToBusinessExchange(@Qualifier(BUSINESS_QUEUE)Queue queue, @Qualifier(BUSINESS_EXCHANGE)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
    @Bean(DIE_EXCHANGE)
    public FanoutExchange dieExchange(){
        return new FanoutExchange(DIE_EXCHANGE,true,false);
    }
    @Bean(DIE_QUEUE)
    public Queue dieQueue(){
        return new Queue(DIE_QUEUE);
    }
    @Bean
    public Binding bindDieQueueToDieExchange(@Qualifier(DIE_QUEUE)Queue queue, @Qualifier(DIE_EXCHANGE)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean(TOPIC_EXCHANGE)
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE).build();
    }
    @Bean(TOPIC_QUEUE1)
    public Queue topicQueue1(){
        return QueueBuilder.durable(TOPIC_QUEUE1).build();
    }
    @Bean(TOPIC_QUEUE2)
    public Queue topicQueue2(){
        return QueueBuilder.durable(TOPIC_QUEUE2).build();
    }
    @Bean
    public Binding bindQueue1ToTopicExchange(@Qualifier(TOPIC_QUEUE1) Queue queue, Exchange topic_exchange){
        return BindingBuilder.bind(queue).to(topic_exchange).with("queue1.#").noargs();
    }
    @Bean
    public Binding bindQueue2ToTopicExchange(Queue topic_queue2,Exchange topic_exchange){
        return BindingBuilder.bind(topic_queue2).to(topic_exchange).with("queue2.#").noargs();
    }

    @Bean
    public Queue simpleQueue(){
        return QueueBuilder.durable(SIMPLE_QUEUE).build();
    }
    @Bean(FANOUT_EXCHANGE)
    public Exchange fanoutExchange(){
        /**
         * 交换机类型:
         * DIRECT: 定向
         * FANOUT: 扇形(广播):发送消费给每一个与之绑定的队列
         * TOPIC: 通配符
         * HEADERS: 参数
         *
         * durable: 是否持久化
         * autoDelete: 自动删除
         * internal: 内部使用
         * arguments: 参数
         */
        return ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE).durable(true).build();
    }
    @Bean(FANOUT_QUEUE1)
    public Queue fanoutQueue1(){
        return QueueBuilder.durable(FANOUT_QUEUE1).build();
    }
    @Bean(FANOUT_QUEUE2)
    public Queue fanoutQueue2(){
        return QueueBuilder.durable(FANOUT_QUEUE2).build();
    }
    @Bean
    public Binding bindQueue1ToFanoutExchange(@Qualifier(FANOUT_QUEUE1) Queue queue, @Qualifier(FANOUT_EXCHANGE) Exchange exchange){
        // 如果交换机类型为fanout, routingKey(路由键,绑定规则) 设置为""
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
    @Bean
    public Binding bindQueue2ToFanoutExchange(@Qualifier(FANOUT_QUEUE2) Queue queue, @Qualifier(FANOUT_EXCHANGE) Exchange exchange){
        // 如果交换机类型为fanout, routingKey(路由键,绑定规则) 设置为""
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        // 创建死信交换机和对列
        rabbitAdmin.declareExchange(dieExchange());
        rabbitAdmin.declareQueue(dieQueue());
        // 创建业务交换机和对列
        rabbitAdmin.declareExchange(businessExchange());
        rabbitAdmin.declareQueue(businessQueue());
        return rabbitAdmin;
    }
}
