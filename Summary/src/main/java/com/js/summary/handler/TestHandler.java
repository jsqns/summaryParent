package com.js.summary.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class TestHandler {
    private static Logger logger = LoggerFactory.getLogger(TestHandler.class);
    @XxlJob("summaryTestTask")
    public void summaryTestTask(String param){
        System.out.println("测试定时任务执行了");
    }
}
