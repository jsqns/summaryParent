package com.js.summary.controller;


import com.js.common.response.Result;
import com.js.common.response.reqUtils.ResultUtils;
import com.js.summary.entity.Summary;
import com.js.summary.factory.PriceComputeFactory;
import com.js.summary.feign.SummaryAuthFeignService;
import com.js.summary.service.SummaryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/summary/summaryService")
@Api(tags = "summary")
@Slf4j
public class SummaryController {
    @Resource
    private SummaryService summaryService;
    @Resource
    private SummaryAuthFeignService summaryAuthFeignService;

    @GetMapping("/getPrice")
    public Result getPrice(String key, BigDecimal price){
        log.info("getPrice-param:{}", key);
//        BigDecimal compute = PriceComputeFactory.getPriceComputeService(key).compute(price);
        return summaryAuthFeignService.getRandomNum();
//        return ResultUtils.createSuccessRes(compute);
    }
    @PostMapping("/update1")
    public Result update1() throws IOException {
        return summaryService.update1();
    }

    @PostMapping("/update2")
    public Result update2(){
        return summaryService.update2();
    }

    @GetMapping("/getAll")
    public Result<List<Long>> get(){
        return summaryService.get();
    }

    @PostMapping("/save")
    public Result<Summary> save(@RequestBody Summary summary){
        return summaryService.saveOne(summary);
    }
}
