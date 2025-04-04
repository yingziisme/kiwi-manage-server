package com.hikari.kiwi.modules.utils;

import com.alibaba.fastjson.JSONObject;
import com.hikari.kiwi.common.dto.BaseRequest;
import com.hikari.kiwi.modules.constant.LoginConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TokenUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public BaseRequest findBaseRequest(String token) {
        BaseRequest baseRequest = new BaseRequest();
        try {
            Long userId = Long.valueOf((String)redisTemplate.opsForHash().get(token, LoginConstant.REDIS_USERID_KEY));
            baseRequest.setTokenUserId(userId);

        } catch (Exception e) {
            return null;
        }

        return baseRequest;
    }

    public Boolean setToken(String token, Long userId) {
        Map<String, String> map = new HashMap<>();
        map.put(LoginConstant.REDIS_USERID_KEY, String.valueOf(userId));
        redisTemplate.opsForHash().putAll(token, map);
        redisTemplate.expire(token, LoginConstant.TOKEN_TIMEOUT, TimeUnit.MINUTES);
        return true;
    }
}
