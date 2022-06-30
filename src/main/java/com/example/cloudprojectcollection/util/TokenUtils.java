package com.example.cloudprojectcollection.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
    public void createToken() {
        JwtBuilder builder = Jwts.builder()
                .setId("test")
                .setSubject("cloud")
                .setAudience("王卫翔")
                .setIssuedAt(new Date())
                //HS256-算法，cloud2201-密钥
                .signWith(SignatureAlgorithm.HS256, "cloud2201");
        Map map = new HashMap();
        map.put("userName", "wwx");
        map.put("pwd", "wang130524");
        builder.addClaims(map);
        System.out.println(builder.compact());
    }

    //token校验
    @Test
    public void checkToken() {
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
    public static final String PUBLIC_KEY = "public_key";
    public static final String PRIVATE_KEY = "private_key";

    public static Map<String, String> generateRasKey() {
       Map<String,String> rs = new HashMap<>();
       try {
           //KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
           KeyPairGenerator keyPairGenerator =null;
           keyPairGenerator = KeyPairGenerator.getInstance("RSA");
           keyPairGenerator.initialize(1024,new SecureRandom());
           //生成一个密钥对，保存在keyPair中
           /**
            * 生成密钥对。
            * 如果尚未明确初始化此KeyPairGenerator，则生成的密钥的大小和其他（特定于算法）值将使用特定于提供程序的默认值。
            * 这将在每次调用时生成一个新的密钥对。
            * 该方法在功能上等同于genKeyPair。
            * 返回值：
            * 生成的密钥对
            */
           KeyPair keyPair = keyPairGenerator.generateKeyPair();
           //得到私钥 公钥
           RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
           RSAPublicKey aPublic = (RSAPublicKey) keyPair.getPublic();
           //得到公钥字符串
           String publicKeyStr = new String(Base64.encodeBase64(aPublic.getEncoded()));
           //得到私钥字符串
           String privateKeyStr = new String(Base64.encodeBase64(aPrivate.getEncoded()));
           //将公钥和私钥保存到Map
           rs.put(PUBLIC_KEY,publicKeyStr);
           rs.put(PRIVATE_KEY,privateKeyStr);
       }catch (Exception e){
           e.printStackTrace();
       }
       return rs;
    }

    public static String encrypt(String str, String publicKey) {
        try {
            //base64编码的公钥
            byte[] decoded = Base64.decodeBase64(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            //RSA加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,pubKey);
            return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RsaException("RsaUtils invoke encrypt failed");
        }
    }

    public static String decrypt(String str,String privateKey){
        try {
            //base64加密后的字符串
            byte[] inputKey = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
            //base64编码的私钥
            byte[] decode = Base64.decodeBase64(privateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decode));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,priKey);
            return new String(cipher.doFinal(inputKey));
        }catch (Exception e){
            e.printStackTrace();
            throw new RsaException("RsaUtils invoke decrypt failed");
        }
    }

    // Java中RSAPublicKeySpec、X509EncodedKeySpec支持生成RSA公钥
    // 此处使用X509EncodedKeySpec生成
    public static RSAPublicKey getPublicKey(String publicKey) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    // Java中只有RSAPrivateKeySpec、PKCS8EncodedKeySpec支持生成RSA私钥
    // 此处使用PKCS8EncodedKeySpec生成
    public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }

    /**
     * 三 RSA签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     */
    public static final String ALGORITHM_NAME = "RSA";
    public static final String MD5_RSA = "MD5withRSA";
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(MD5_RSA);
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(MD5_RSA);
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

}

@Getter
class RsaException extends RuntimeException{
    private final String message;
    RsaException(String message){
        this.message = message;
    }
}
