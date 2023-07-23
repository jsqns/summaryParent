package com.js.common.jwt.JwtUtils;

import cn.hutool.core.date.DateTime;
import com.js.common.comtants.CommonConstants;
import com.js.common.jwt.JwtConstant;
import com.js.common.jwt.JwtInfo;
import com.js.common.priKeyUtils.RsaKeyHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

public class JwtHelper {
    private static final RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
    public static String getToken(JwtInfo jwtInfo, byte[] priKey, int expire) throws Exception {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(CommonConstants.USER_ID, jwtInfo.getUserId())
                .claim(CommonConstants.USER_NAME, jwtInfo.getUsername())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey));
        return jwtBuilder.compact();
    }
    public static Claims parseToken(String token){
        return Jwts.parser().setSigningKey(JwtConstant.secret).parseClaimsJwt(token).getBody();
    }
}
