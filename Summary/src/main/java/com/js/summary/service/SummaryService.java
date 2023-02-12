package com.js.summary.service;

import com.js.summary.common.Result;
import com.js.summary.entity.Summary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author qns
* @description 针对表【summary】的数据库操作Service
* @createDate 2022-12-01 21:43:21
*/
public interface SummaryService extends IService<Summary> {

    Result<List<Long>> get();

    Result<Summary> saveOne(Summary summary);
}
