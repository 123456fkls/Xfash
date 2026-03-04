package com.example.xfash.Controller;

import com.example.xfash.Service.StudentService;
import com.example.xfash.pojo.PageResult;
import com.example.xfash.pojo.Result;
import com.example.xfash.pojo.Student;
import com.example.xfash.pojo.StudentQueryParm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    //查
    @GetMapping
    public Result page(@Validated StudentQueryParm studentQueryParm) {
        log.info("分页查询:{}", studentQueryParm);
        PageResult<Student> studentList = studentService.page(studentQueryParm);
        return Result.success(studentList);
    }
    //增
    @PostMapping
    public Result add(@RequestBody @Validated Student student) {
        log.info("新增:{}", student);
        studentService.add(student);
        return Result.success();
    }
    //根据Id查询（修改回显）
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据Id查询:{}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }
    //改
    @PutMapping
    public Result update(@RequestBody @Validated Student student) {
        log.info("修改:{}", student);
        studentService.update(student);
        return Result.success();
    }
    //删
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除:{}", id);
        studentService.delete(id);
        return Result.success();
    }
    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪处理:{}", id);
        studentService.violation(id, score);
        return Result.success();
    }
}
