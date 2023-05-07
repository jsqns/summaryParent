package com.js.summary.controller;


import com.js.common.response.Result;
import com.js.summary.entity.Summary;
import com.js.summary.service.SummaryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/summary/summaryService")
@Api(tags = "summary")
public class SummaryController {
    @Resource
    private SummaryService summaryService;

    @PostMapping("/update1")
    public Result update1(){
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
