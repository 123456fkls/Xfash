package com.example.xfash.Controller;

import com.example.xfash.Service.ClazzService;
import com.example.xfash.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    //查
    @GetMapping
    public Result page(ClazzQueryParm clazzQueryParm) {
        log.info("分页查询:{}", clazzQueryParm);
        PageResult<Clazz> clazzList = clazzService.page(clazzQueryParm);
        return Result.success(clazzList);
    }

    //增
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("新增:{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    //根据Id查询（修改回显）
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据Id查询:{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
    //改
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改:{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    //删
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除:{}", id);
        clazzService.delete(id);
        return Result.success();
    }
    //查询所有班级
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }
}
