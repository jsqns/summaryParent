package com.js.summarygateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "security.ignore")
@RefreshScope
@Configuration
public class IgnoreUriProperties {
    private List<String> ignoreUris = new ArrayList<>();

    public List<String> getIgnoreUris(){
        return ignoreUris;
    }
    public void setIgnoreUris(List<String> ignoreUris){
        this.ignoreUris = ignoreUris;
    }
}
