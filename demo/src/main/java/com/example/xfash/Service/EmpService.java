package com.example.xfash.Service;

import com.example.xfash.pojo.Emp;
import com.example.xfash.pojo.PageResult;
//分页查询

public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
