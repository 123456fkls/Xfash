
package com.example.xfash.Aop;

import com.example.xfash.Mapper.OperateLogMapper;
import com.example.xfash.Utils.CurrentHolder;
import com.example.xfash.pojo.OperateLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {
    // 注解注入 OperateLogMapper
    @Autowired
    private OperateLogMapper operateLogMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // 拦截 controller 包下所有 public 方法，并结合 @LogOperation 注解或 CRUD 关键字
    @Around("@annotation(com.example.xfash.Anno.Log)")
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 获取方法签名

        //执行目标 方法
        Object result = joinPoint.proceed();

        // 计算耗时
        long costTime = System.currentTimeMillis() - startTime;
        //构建日志实体
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(getCurrentUserId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(joinPoint.getSignature().getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result != null ? result.toString() : "void");
        olog.setCostTime(costTime);
        //保存日志
        log.info("记录操作日志：{}", log);
        operateLogMapper.insert(olog);
        return result;
    }

    private Integer getCurrentUserId() {
       return CurrentHolder.getCurrentId();
    }


}
