package com.example.Achitecture.common.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {


//    一天的毫秒数
    private static long time = 1000*60*60*24;
    private static String signatureKey; // 用于签名的密钥


    // 初始化签名密钥的方法
    public static void initializeSignatureKey(String secretKey) {
        signatureKey = secretKey; // 假设您有一个获取密钥的方法
    }

//    初始化用户名


    public static String createToken(){

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
//                头
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
//                载荷
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
//                签名
                .signWith(SignatureAlgorithm.HS256,signatureKey)
                .compact();

        return jwtToken;

    }




    public static boolean checkToken(String token){

        if (token == null){

            return false;
        }
        try{

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signatureKey).parseClaimsJws(token);

        }
        catch (Exception e){

            return false;

        }
        return true;

    }

}
