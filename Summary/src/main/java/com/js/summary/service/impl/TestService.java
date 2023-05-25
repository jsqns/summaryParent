package com.js.summary.service.impl;

import com.js.summary.service.TestServiceI;
import org.springframework.stereotype.Service;

@Service
public class TestService implements TestServiceI {
    @Override
    public String testService() {
        return "ss";
    }
}
