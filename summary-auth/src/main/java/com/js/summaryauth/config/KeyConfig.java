package com.js.summaryauth.config;


import lombok.Data;

@Data
public class KeyConfig {
    private String userSecret = "secret";
    private byte[] userPubKey;
    private byte[] userPriKey;
}
