package com.js.summaryauth.service.impl;

import com.js.summaryauth.service.TestServiceI;
import org.springframework.stereotype.Service;

@Service
public class TestService implements TestServiceI {
    @Override
    public String testService() {
        return "ss";
    }
}
