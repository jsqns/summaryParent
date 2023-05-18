package com.js.summary.service.impl;

import com.js.summary.handler.TestHandler;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@SpringBootTest
@RunWith(SpringRunner.class)
class SummaryServiceImplTest {

    @Resource
    private SummaryServiceImpl summaryService;

    @BeforeEach
    void setUp(){
        TestHandler testHandler = new TestHandler();
        summaryService = new SummaryServiceImpl();
    }
    @Test
    void getTestHandle() {
        summaryService.getTestHandle();
    }
}