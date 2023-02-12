package com.js.summary;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.js.summary.mapper")
public class SummaryApplication {

    public static void main(String[] args) {
        try {

            SpringApplication.run(SummaryApplication.class, args);
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
