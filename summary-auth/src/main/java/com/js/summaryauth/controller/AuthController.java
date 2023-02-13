package com.js.summaryauth.controller;


import com.js.common.jwt.JwtInfo;
import com.js.common.response.Result;
import com.js.summaryauth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth/service")
public class AuthController {
    @Resource
    private AuthService authService;

    @PostMapping("/getTokenByUserInfo")
    public Result getTokenByUserInfo(@RequestBody JwtInfo jwtInfo) throws Exception {
        return authService.getTokenByUserInfo(jwtInfo);
    }
}
