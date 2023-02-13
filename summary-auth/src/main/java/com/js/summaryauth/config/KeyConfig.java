package com.js.summaryauth.config;


import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class KeyConfig {
    private String userSecret = "secret";
    private byte[] userPubKey;
    private byte[] userPriKey;
}
