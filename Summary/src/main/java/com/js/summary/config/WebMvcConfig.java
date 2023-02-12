package com.js.summary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        // 静态资源访问路径和存放路径配置
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","classpath:/public/");
        // swagger访问配置
        registry.addResourceHandler("/**").addResourceLocations("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/configuration/**","/images/**","/doc.html");
    }

}
