package com.js.summary.controller;


import com.js.summary.common.Result;
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

    @GetMapping("/getAll")
    public Result<List<Long>> get(){
        return summaryService.get();
    }

    @PostMapping("/save")
    public Result<Summary> save(@RequestBody Summary summary){
        return summaryService.saveOne(summary);
    }
}
