package com.js.summaryauth.service.impl;

import com.js.common.RandomUtil;
import com.js.common.comtants.CommonConstants;
import com.js.common.jwt.JwtInfo;
import com.js.common.jwt.JwtUtils.JwtHelper;
import com.js.common.priKeyUtils.RsaKeyHelper;
import com.js.common.redisUtil.RedisKeys;
//import com.js.common.redisUtil.RedisUtils;
import com.js.common.response.Result;
import com.js.common.response.reqUtils.ResultUtils;
import com.js.summaryauth.config.KeyConfig;
import com.js.summaryauth.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


@Service
public class AuthServiceImpl implements AuthService {


    @Resource
    private KeyConfig keyConfig;
//    @Resource
//    private RedisUtils redisUtils;

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
//        redisUtils.lPush(RedisKeys.QUEUE_LIST.getKey(),token);
    }

    @Override
    public Result<String> getRandomNum() {
        int i = RandomUtil.getRandomInt();
//        String s = redisUtils.lIndex(RedisKeys.QUEUE_LIST.getKey(), 0);
        Result<String> successRes = ResultUtils.createSuccessRes(i + "s");
        return successRes;
    }

    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("bsfs","2");
        treeMap.put("asfs","1");
        treeMap.put("csfs","3");
        Set<String> strings = treeMap.keySet();
        Set<String> set  = new TreeSet<>();
        set.add("123");
        set.add("12qrwedsfg3");
        set.add("12234qwer3");
        set.add("2342");
        set.size();
    }
    @Override
    public Result getPdf(HttpServletResponse httpServletResponse) {
        File file = new File("D:\\覃铄杰的简历.pdf");
        httpServletResponse.setContentType("application/pdf");
        ServletOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            outputStream = httpServletResponse.getOutputStream();
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[fileInputStream.available()];
            int n;
            while ((n = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, n);
            }
//            outputStream.write(bytes);
            outputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
