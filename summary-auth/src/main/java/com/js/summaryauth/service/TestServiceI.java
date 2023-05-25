package com.js.summaryauth.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface TestServiceI {
    @GetMapping("test/service")
    String testService();
}
