package com.example.trip.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.trip.entity.User;
import com.example.trip.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
public class TokenUtils {

    private static long expire = 900; // 15 minutes

    @Autowired
    private UserMapper userMapper;

    private static UserMapper staticUserMapper;

    @PostConstruct
    public void init() {
        staticUserMapper = userMapper;
    }

    /**
     * generate token
     * @param user
     * @return
     */
    public static String generateToken(User user){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expire);
        return JWT.create().withExpiresAt(expiration).withAudience(Integer.toString(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword())); // if encrypted, need to add decryption
    }

    /**
     * decode token and user info
     * @return
     */
    public static User getUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            String aud = JWT.decode(token).getAudience().get(0);
            Integer userId = Integer.valueOf(aud);
            return staticUserMapper.selectById(userId);
        } catch (Exception e) {
            log.error("decode token failed", e);
            return null;
        }
    }

}