package com.js.summaryauth.service;

import com.js.common.jwt.JwtInfo;
import com.js.common.response.Result;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    Result getTokenByUserInfo(JwtInfo jwtInfo) throws Exception;

    void saveToken2Redis(String token);

    Result<String> getRandomNum();

    Result getPdf(HttpServletResponse httpServletResponse);
}
