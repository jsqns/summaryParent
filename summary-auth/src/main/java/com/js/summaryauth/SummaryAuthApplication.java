package com.js.summaryauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScans({@ComponentScan("com.js.common")})
@EnableFeignClients
@EnableDiscoveryClient
public class SummaryAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummaryAuthApplication.class, args);
    }

}
