package com.js.summary.handler;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;


@Component
public class TestHandler {
    @XxlJob("summaryTestTask")
    public void summaryTestTask(String param){
        System.out.println("测试定时任务执行了");
    }
}
