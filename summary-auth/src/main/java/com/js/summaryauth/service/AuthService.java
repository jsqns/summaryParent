package com.js.summaryauth.service;

import com.js.common.jwt.JwtInfo;
import com.js.common.response.Result;

public interface AuthService {

    Result getTokenByUserInfo(JwtInfo jwtInfo) throws Exception;
}
