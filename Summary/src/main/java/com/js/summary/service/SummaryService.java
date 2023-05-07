package com.js.summary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.common.response.Result;
import com.js.summary.entity.Summary;

import java.util.List;

/**
* @author qns
* @description 针对表【summary】的数据库操作Service
* @createDate 2022-12-01 21:43:21
*/
public interface SummaryService extends IService<Summary> {

    Result<List<Long>> get();

    Result<Summary> saveOne(Summary summary);

    Result update1();

    Result update2();
}
