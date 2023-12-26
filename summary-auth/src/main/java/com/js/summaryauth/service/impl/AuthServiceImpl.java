package com.js.summaryauth.service.impl;

import com.js.common.RandomUtil;
import com.js.common.jwt.JwtInfo;
import com.js.common.jwt.JwtUtils.JwtHelper;
import com.js.common.priKeyUtils.RsaKeyHelper;
import com.js.common.rabbitmq.RabbitMqConfig;
import com.js.common.response.Result;
import com.js.common.response.reqUtils.ResultUtils;
import com.js.common.utils.mqUtils.RabbitMqUtil;
import com.js.summaryauth.config.KeyConfig;
import com.js.summaryauth.service.AuthService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


@Service
public class AuthServiceImpl implements AuthService {


    @Resource
    private KeyConfig keyConfig;
//    @Resource
//    private RedisUtils redisUtils;
    @Resource
    private RabbitMqUtil rabbitMqUtil;

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
//        rabbitTemplate.convertAndSend(RabbitMqConfig.SIMPLE_QUEUE, "测试111".getBytes());
//        rabbitTemplate.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "", "测试222".getBytes());
//        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,"queue1.111", "主题队列11".getBytes());
        rabbitMqUtil.send(RabbitMqConfig.BUSINESS_EXCHANGE,"111","测试"+System.currentTimeMillis());
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
                if (fileInputStream != null){
                    fileInputStream.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
