package com.js.summary.feign;


import com.js.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "summary-auth")
public interface SummaryAuthFeignService {
    @RequestMapping(value = "/auth/service/getRandomNum", method = RequestMethod.GET)
    public Result getRandomNum();
}
