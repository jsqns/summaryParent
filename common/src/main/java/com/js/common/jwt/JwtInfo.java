package com.js.common.jwt;


import lombok.Data;

@Data
public class JwtInfo {
    private Long userId;
    private String username;
    private Integer expireSecond;
}
