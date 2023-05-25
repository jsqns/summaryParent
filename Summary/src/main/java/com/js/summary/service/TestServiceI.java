package com.js.summary.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface TestServiceI {
    @GetMapping("test/service")
    String testService();
}
