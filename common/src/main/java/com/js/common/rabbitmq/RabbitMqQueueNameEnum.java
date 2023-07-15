package com.js.common.rabbitmq;

import lombok.Getter;

@Getter
public enum RabbitMqQueueNameEnum {
    SIMPLE_QUEUE(1,"simple_queue","测试用");
    private final Integer id;
    private final String queueName;
    private final String queueDesc;

    RabbitMqQueueNameEnum(Integer id, String queueName, String queueDesc) {
        this.id = id;
        this.queueName = queueName;
        this.queueDesc = queueDesc;
    }
}
