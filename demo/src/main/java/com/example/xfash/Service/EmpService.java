package com.example.xfash.Service;

import com.example.xfash.pojo.Emp;
import com.example.xfash.pojo.EmpQueryParam;
import com.example.xfash.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
//分页查询

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);
}
