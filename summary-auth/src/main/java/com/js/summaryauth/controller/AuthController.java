package com.js.summaryauth.controller;


import com.js.common.jwt.JwtInfo;
import com.js.common.response.Result;
import com.js.summaryauth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/auth/service")
@Slf4j
public class AuthController {
    @Resource
    private AuthService authService;


    @PostMapping("/getPdf")
    public Result getPdf(HttpServletResponse httpServletResponse){
        return authService.getPdf(httpServletResponse);
    }
    @GetMapping("/getRandomNum")
    public Result getRandomNum(){
        log.info("getRandomNum");
        return authService.getRandomNum();
    }

    @PostMapping("/getTokenByUserInfo")
    public Result getTokenByUserInfo(@RequestBody JwtInfo jwtInfo) throws Exception {
        return authService.getTokenByUserInfo(jwtInfo);
    }
}
