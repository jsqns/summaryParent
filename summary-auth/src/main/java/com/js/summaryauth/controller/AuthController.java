package com.js.summaryauth.controller;


import com.js.common.jwt.JwtInfo;
import com.js.common.response.Result;
import com.js.summaryauth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth/service")
public class AuthController {
    @Resource
    private AuthService authService;


    @PostMapping("/getPdf")
    public Result getPdf(HttpServletResponse httpServletResponse){
        return authService.getPdf(httpServletResponse);
    }
    @GetMapping("/getRandomNum")
    public Result getRandomNum(){
        return authService.getRandomNum();
    }

    @PostMapping("/getTokenByUserInfo")
    public Result getTokenByUserInfo(@RequestBody JwtInfo jwtInfo) throws Exception {
        return authService.getTokenByUserInfo(jwtInfo);
    }
}
