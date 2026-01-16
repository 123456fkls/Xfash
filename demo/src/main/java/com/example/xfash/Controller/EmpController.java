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
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    //查询
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    //删除(数组)
//    @DeleteMapping
//    public Result delete(Integer[] ids) {
//        log.info("删除员工:{}", Arrays.toString(ids));
//        return Result.success();
//    }
    //删除（集合）、
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工:{}", ids);
        empService.delete(ids);
        return Result.success();
    }
    //修改
    //查询回显
    @GetMapping ("/{id}")
    public Result getInfo(@PathVariable Integer id ) {
        log.info("根据Id查询员工:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

}
