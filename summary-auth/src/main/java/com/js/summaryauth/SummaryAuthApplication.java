package com.js.summaryauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SummaryAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummaryAuthApplication.class, args);
    }

}
