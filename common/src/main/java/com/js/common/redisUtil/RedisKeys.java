package com.js.common.redisUtil;

import lombok.Getter;

@Getter
public enum RedisKeys {
    QUEUE_LIST("myk","测试用"),
    SINGLE("my", "测试用");

    private final String key;
    private final String keyDesc;
    RedisKeys(String key, String keyDesc){
        this.key = key;
        this.keyDesc = keyDesc;
    }

}
