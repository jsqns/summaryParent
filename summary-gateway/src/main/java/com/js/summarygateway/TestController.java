//package com.js.summarygateway;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RefreshScope
//@RequestMapping("/config")
//public class TestController {
//    @Value("${auth.user.secret}")
//    private String secret;
//    @GetMapping("/get")
//    public String get(){
//        return secret;
//    }
//}
