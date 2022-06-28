package com.example.cloudprojectcollection.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wwx
 * @createTime: 2022年06月28日
 * @version: 0.0.1
 * @Description: token
 */

public class TokenUtils {

    /**
     * (一)
     * JWT(JSON Web Token)，是一种用于通信双方之间传递安全信息的简洁的、URL安全的表述性声明规范。
     * 一个JWT实际上就是一个字符串，它由三部分组成，头部、载荷与签名。加密解密使用的是相同的密钥，不安全
     */
    //创建token
    @Test
     public void createToken(){
         JwtBuilder builder = Jwts.builder()
                 .setId("test")
                 .setSubject("cloud")
                 .setAudience("王卫翔")
                 .setIssuedAt(new Date())
                 //HS256-算法，cloud2201-密钥
                 .signWith(SignatureAlgorithm.HS256,"cloud2201");
         Map map = new HashMap();
         map.put("userName","wwx");
         map.put("pwd","wang130524");
         builder.addClaims(map);
        System.out.println(builder.compact());
     }

     //token校验
    @Test
    public void checkToken(){
        String compactJwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ0ZXN0Iiwic3ViIjoiY2xvdWQiLCJhdWQiOiLnjovljavnv5QiLCJpYXQiOjE2NTYzODg1OTgsInVzZXJOYW1lIjoid3d4IiwicHdkIjoid2FuZzEzMDUyNCJ9.9VMONDHge01zluNvicnoAthj3enwrx6bsbt3Kzg_JtY";
        Claims claims = Jwts.parser()
                //密钥
                .setSigningKey("cloud2201")
                .parseClaimsJws(compactJwt)
                .getBody();
        System.out.println("claims = " + claims.toString());
    }

    /**
     * (二)
     * RSA的基本原理有两点：
     * 私钥加密，持有私钥或公钥才可以解密
     * 公钥加密，持有私钥才可解密
     */


}
