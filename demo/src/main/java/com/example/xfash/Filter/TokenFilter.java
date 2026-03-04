package com.example.xfash.Filter;

import com.example.xfash.Utils.CurrentHolder;
import com.example.xfash.Utils.JwtUtils;
import com.example.xfash.Utils.SpringContextUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        JwtUtils jwtUtils = SpringContextUtil.getBean(JwtUtils.class);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String RequestUrl = request.getRequestURI();

        if (RequestUrl.contains("/login")) {
            log.info("登录请求：{}", RequestUrl);
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("token");
        log.info("请求头 token：{}", token);

        if (token == null || token.isEmpty()) {
            log.info("令牌为空");
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write("{\"code\":1,\"msg\":\"未登录，请先登录\",\"data\":null}");
            writer.flush();
            writer.close();
            return;
        }

        try {
            Claims claims = jwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前用户 id：{},将其存入 ThreadLocal", empId);
        } catch (Exception e) {
            log.info("令牌错误：{}", e.getMessage());
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write("{\"code\":1,\"msg\":\"令牌无效或已过期\",\"data\":null}");
            writer.flush();
            writer.close();
            return;
        }

        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);
        CurrentHolder.remove();
    }
}
