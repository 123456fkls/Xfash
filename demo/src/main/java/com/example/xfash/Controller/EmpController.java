package com.example.xfash.Controller;

import com.example.xfash.Service.EmpService;
import com.example.xfash.pojo.Emp;
import com.example.xfash.pojo.EmpQueryParam;
import com.example.xfash.pojo.PageResult;
import com.example.xfash.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询:{}",empQueryParam);
        PageResult< Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }

}
