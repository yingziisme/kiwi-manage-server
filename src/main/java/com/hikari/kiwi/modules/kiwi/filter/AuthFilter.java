package com.hikari.kiwi.modules.kiwi.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hikari.kiwi.modules.constant.LoginConstant;
import com.hikari.kiwi.modules.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@Order(-1000)
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = UUID.randomUUID().toString();
        String url = request.getRequestURI();
        log.info("[AuthFilter] doFilterInternal URI: {}", url);
        if (!url.startsWith("/little-box-api/api/v1/kiwi") || url.startsWith("/little-box-api/api/v1/kiwi/common")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(LoginConstant.HEADER_TOKEN_KEY);
        if (StringUtils.isNotBlank(token)) {
            try {
                Boolean hasToken = redisTemplate.hasKey(token);
                if (null != hasToken && hasToken) {
                    redisTemplate.expire(token, LoginConstant.TOKEN_TIMEOUT, TimeUnit.MINUTES);
                    HttpRequestBodyFilter bodyFilter = new HttpRequestBodyFilter(request);
                    String oldBody = bodyFilter.getBody();
                    JSONObject jsonObject = JSONObject.parseObject(oldBody);
                    JSONObject baseRequestJson = JSONObject.parseObject(JSON.toJSONString(tokenUtils.findBaseRequest(token)));

                    baseRequestJson.keySet().forEach(key -> {
                        jsonObject.put(key, baseRequestJson.get(key));
                        jsonObject.put("requestId", requestId);
                    });
                    bodyFilter.setBody(jsonObject.toString());
                    filterChain.doFilter(bodyFilter, response);
                    return;
                }

            } catch (Exception e) {
                logger.error("filter error", e);
            }
        }
        log.info("[AuthFilter] doFilterInternal token: {}", token);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            filterChain.doFilter(request, response);
    }
}
