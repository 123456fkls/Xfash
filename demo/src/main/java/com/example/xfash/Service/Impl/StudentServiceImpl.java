package com.example.xfash.Service.Impl;

import com.example.xfash.Mapper.StudentMapper;
import com.example.xfash.Service.StudentService;
import com.example.xfash.pojo.PageResult;
import com.example.xfash.pojo.Student;
import com.example.xfash.pojo.StudentQueryParm;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //查
    @Override
    public PageResult<Student> page(StudentQueryParm studentQueryParm) {
        //设置分页参数
        PageHelper.startPage(studentQueryParm.getPage(), studentQueryParm.getPageSize());
        //执行查询
        List<Student> stuList = studentMapper.list(studentQueryParm);
        //解析结果并封装数据
        Page<Student> p = (Page<Student>) stuList;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }
//增
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);

    }
//根据id查询
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void delete(Integer id) {
        studentMapper.delete(id);

    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id, score);

    }
}
