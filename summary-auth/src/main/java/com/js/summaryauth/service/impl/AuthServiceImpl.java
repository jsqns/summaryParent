package com.js.summaryauth.service.impl;

import com.js.common.comtants.CommonConstants;
import com.js.common.jwt.JwtInfo;
import com.js.common.jwt.JwtUtils.JwtHelper;
import com.js.common.response.Result;
import com.js.common.response.reqUtils.ResultUtils;
import com.js.summaryauth.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class AuthServiceImpl implements AuthService {


    @PostConstruct
    public void intPriKey(){

    }

    @Override
    public Result getTokenByUserInfo(JwtInfo jwtInfo) throws Exception {
        String token = JwtHelper.getToken(jwtInfo, CommonConstants.PRI_KEY.getBytes(), jwtInfo.getExpireSecond());
        return ResultUtils.createSuccessRes(token);
    }
}
