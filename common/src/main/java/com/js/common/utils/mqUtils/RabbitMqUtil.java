package com.js.common.utils.mqUtils;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
public class RabbitMqUtil {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String exchange, String routingKey, Object object){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if (b){
                    System.out.println("发送成功");
                }else {
                    System.out.println("发送失败");
                }
            }
        });
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                byte[] body = returnedMessage.getMessage().getBody();
                System.out.println(Arrays.toString(body) + "触发");
            }
        });
        rabbitTemplate.convertAndSend(exchange,routingKey,object);
    }
}
