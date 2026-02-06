package com.example.xfash.Interceptor;

import com.example.xfash.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //1.获取请求路径
//        String RequestUrl = request.getRequestURI();///api/user/login
//
//        //2.是否为登录请求
//        if (RequestUrl.contains("/login")) {
//            log.info("登录请求：{}", RequestUrl);
//            return true;
//        }

        //3.获取请求头token
        String token = request.getHeader("token");
        log.info("请求头token：{}", token);

        //4.判断token是否存在，不存在返回401
        if (token == null || token.isEmpty()) {
            log.info("令牌为空");
            response.setStatus(401);
            return false;
        }

        //5.解析token，失败返回401
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌错误");
            response.setStatus(401);
            return false;
        }
        //6.放行
        log.info("令牌合法,放行");
        return true;
    }

}