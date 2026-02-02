package com.example.xfash.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtils {
    
    /**
     * 密钥（Base64编码的"123456"）
     * 注意：生产环境中应该使用更复杂的密钥，并从配置文件或环境变量中读取
     */
    private static final String SECRET_KEY = "MTIzNDU2";
    
    /**
     * 令牌过期时间（12小时）
     */
    private static final long EXPIRATION_TIME = 12 * 3600 * 1000L; // 12小时，单位：毫秒
    
    /**
     * 生成JWT令牌
     * 
     * @param id 用户ID
     * @param username 用户名
     * @return JWT令牌字符串
     */
    public static String generateToken(Integer id, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", username);
        
        return generateToken(claims);
    }
    
    /**
     * 生成JWT令牌（带自定义claims）
     * 
     * @param claims 自定义claims信息
     * @return JWT令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(claims) // 添加自定义信息
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间：12小时
                .compact(); // 生成令牌
    }
    
    /**
     * 解析JWT令牌
     * 
     * @param token JWT令牌字符串
     * @return Claims对象，包含所有的claims信息
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 指定密钥
                .parseClaimsJws(token) // 解析令牌
                .getBody(); // 获取claims信息
    }
    
    /**
     * 验证令牌是否有效
     * 
     * @param token JWT令牌字符串
     * @return 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 从令牌中获取用户ID
     * 
     * @param token JWT令牌字符串
     * @return 用户ID
     */
    public static Integer getIdFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("id", Integer.class);
    }
    
    /**
     * 从令牌中获取用户名
     * 
     * @param token JWT令牌字符串
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("username", String.class);
    }
    
    /**
     * 检查令牌是否即将过期（在指定时间内过期）
     * 
     * @param token JWT令牌字符串
     * @param threshold 阈值（毫秒）
     * @return 是否即将过期
     */
    public static boolean isTokenExpiringSoon(String token, long threshold) {
        try {
            Claims claims = parseToken(token);
            Date expiration = claims.getExpiration();
            Date now = new Date();
            return expiration.getTime() - now.getTime() <= threshold;
        } catch (Exception e) {
            return true;
        }
    }
    
    /**
     * 刷新令牌（创建新令牌，保留原有claims）
     * 
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        Claims claims = parseToken(token);
        return generateToken(claims);
    }
}