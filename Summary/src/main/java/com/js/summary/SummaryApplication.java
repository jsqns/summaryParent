package com.js.summary;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@MapperScan("com.js.summary.mapper")
@EnableFeignClients(value = {"com.js.summary.feign"})
@EnableDiscoveryClient
public class SummaryApplication {

    public static void main(String[] args) {
        try {

            SpringApplication.run(SummaryApplication.class, args);
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
