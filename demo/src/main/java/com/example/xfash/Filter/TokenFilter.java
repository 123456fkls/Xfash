package com.example.xfash.Filter;

import com.example.xfash.Utils.CurrentHolder;
import com.example.xfash.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String RequestUrl = request.getRequestURI();///api/user/login

        //2.是否为登录请求
        if (RequestUrl.contains("/login")) {
            log.info("登录请求：{}", RequestUrl);
            filterChain.doFilter(request, response);
            return;
        }

        //3.获取请求头token
        String token = request.getHeader("token");
        log.info("请求头token：{}", token);

        //4.判断token是否存在，不存在返回401
        if (token == null || token.isEmpty()) {
            log.info("令牌为空");
            response.setStatus(401);
            return;
        }

        //5.解析token，失败返回401
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前用户id：{},将其存入ThreadLocal", empId);
        } catch (Exception e) {
            log.info("令牌错误");
            response.setStatus(401);
            return;
        }
        //6.放行
        log.info("令牌合法,放行");
        filterChain.doFilter(request, response);
        //7.删除ThreadLocal数据
        CurrentHolder.remove();
    }
}
