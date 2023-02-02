package cn.jingchenxi.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.Builder;

import java.util.Date;
import java.util.Map;

/**
 * @author : jingchenxi
 * @since : 2023/1/13
 **/
public class JwtTokenUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * 过期时间是3600秒，既是1个小时
     */

    private static final Long EXPIRATION = 3600L;

    /**
     * 选择了记住我之后的过期时间为7天
     */

    private static final Long EXPIRATION_REMEMBER = 604800L;

    /**
     * jwt密钥
     */
    private static final String SECRET = "petHospital_secret";

    /**
     * 生成JWT Token字符串
     *
     * @param userId
     * @param isRememberMe
     * @param info         info,Map的value只能存放值的类型为：Map，List，Boolean，Integer，Long，Double，String and Date
     * @return
     */
    public static String sign(Integer userId, Boolean isRememberMe, Map<String, Object> info) {
        Long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    //将userId保存到token里面
                    .withAudience(String.valueOf(userId))
                    //存放自定义数据
                    .withClaim("info", info)
                    //token过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000))
                    //token的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token获取userId
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根据token获取自定义数据info
     *
     * @param token
     * @return
     */
    public static Map<String, Object> getInfo(String token) {
        try {
            return JWT.decode(token).getClaim("info").asMap();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withClaim("username, username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("token 无效，请重新获取");
        }
    }
}
