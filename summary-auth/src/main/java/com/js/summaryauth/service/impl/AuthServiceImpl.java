package com.js.summaryauth.service.impl;

import com.js.common.comtants.CommonConstants;
import com.js.common.jwt.JwtInfo;
import com.js.common.jwt.JwtUtils.JwtHelper;
import com.js.common.priKeyUtils.RsaKeyHelper;
import com.js.common.redisUtil.RedisKeys;
import com.js.common.redisUtil.RedisUtils;
import com.js.common.response.Result;
import com.js.common.response.reqUtils.ResultUtils;
import com.js.summaryauth.config.KeyConfig;
import com.js.summaryauth.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {


    @Resource
    private KeyConfig keyConfig;
    @Resource
    private RedisUtils redisUtils;

    @PostConstruct
    public void intPriKey() throws IOException, NoSuchAlgorithmException {
        Map<String, byte[]> map = RsaKeyHelper.generateKey(keyConfig.getUserSecret());
        keyConfig.setUserPriKey(map.get("pri"));
        keyConfig.setUserPubKey(map.get("pub"));
    }

    @Override
    public Result getTokenByUserInfo(JwtInfo jwtInfo) throws Exception {
        String token = JwtHelper.getToken(jwtInfo, keyConfig.getUserPriKey(), jwtInfo.getExpireSecond());
        this.saveToken2Redis(token);
        return ResultUtils.createSuccessRes(token);
    }

    @Override
    public void saveToken2Redis(String token) {
        redisUtils.setKey(RedisKeys.QUEUE_LIST.getKey(),token);
    }
}
