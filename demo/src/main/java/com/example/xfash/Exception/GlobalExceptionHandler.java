package com.example.xfash.Exception;

import com.example.xfash.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("出错啦!你现在满意了吧！", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error("[" + arr[2] + "]已存在");
    }

    // 处理参数校验异常（针对@RequestBody）
    @ExceptionHandler
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数校验失败：{}", e.getMessage());
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMsg.append(error.getField()).append(":").append(error.getDefaultMessage()).append(";");
        });
        return Result.error(errorMsg.toString());
    }
    // 处理参数校验异常（针对普通参数对象）
    @ExceptionHandler
    public Result handleConstraintViolationException(jakarta.validation.ConstraintViolationException e) {
        log.error("参数校验失败：{}", e.getMessage());
        return Result.error(e.getMessage());
    }
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("出错啦!再检查一下吧0v0", e);
        return Result.error("出现错误，请再检查一下");
    }
}
