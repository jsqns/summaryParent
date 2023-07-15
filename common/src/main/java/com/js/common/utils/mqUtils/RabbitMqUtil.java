package com.js.common.utils.mqUtils;


import org.springframework.amqp.core.Message;
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
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println(new String(Arrays.toString(message.getBody()) +"触发"));
            }
        });
        rabbitTemplate.convertAndSend(exchange,routingKey,object);
    }
}
