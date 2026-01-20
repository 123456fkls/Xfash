package com.example.xfash.pojo;

import lombok.Data;

@Data
public class Result {
    private Integer code;//状态码
    private String msg;//返回信息
    private Object data;//泛型

    public static Result success() {

        Result result = new Result();
        result.code = 1;
        result.msg = "操作成功";
        return result;
    }

    public static Result success(Object object) {
        Result result = success();
        result.data = object;
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }

}
