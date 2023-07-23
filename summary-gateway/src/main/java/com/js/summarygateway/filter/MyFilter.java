package com.js.summarygateway.filter;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.js.common.jwt.JwtUtils.JwtHelper;
import com.js.common.response.Result;
import com.js.common.response.ResultConstant;
import com.js.common.utils.StringUtil;
import com.js.summarygateway.properties.IgnoreUriProperties;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class MyFilter implements GlobalFilter{
    @Value("auth.user.token-header")
    private String token;
    @Resource
    private IgnoreUriProperties ignoreUriProperties;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if(StringUtil.startWith2List(path, ignoreUriProperties.getIgnoreUris())){
            return chain.filter(exchange);
        }
        List<String> headerList = request.getHeaders().get(token);
        if (CollUtil.isEmpty(headerList)){
            return getVoidMono(exchange,new Result<>(ResultConstant.TOKEN_EXCEPTION.getStatus(), ResultConstant.TOKEN_EXCEPTION.getMessage()));
        }
        String token = headerList.get(0);
        if (token == null){
            return getVoidMono(exchange,new Result<>(ResultConstant.TOKEN_EXCEPTION.getStatus(), ResultConstant.TOKEN_EXCEPTION.getMessage()));
        }
        Claims claims = JwtHelper.parseToken(token);
        if (claims == null){

        }
        return chain.filter(exchange);
    }

    /**
     * 网关抛异常
     *
     * @param body
     */
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, Result body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
