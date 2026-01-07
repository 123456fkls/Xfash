package com.example.xfash.pojo;

import lombok.Data;

import java.util.List;
@Data
//分页结果封装类
public class pageResult<T> {
    private long total;
    private List<T> rows;
}
