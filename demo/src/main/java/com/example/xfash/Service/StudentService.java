package com.example.xfash.Service;

import com.example.xfash.pojo.PageResult;
import com.example.xfash.pojo.Student;
import com.example.xfash.pojo.StudentQueryParm;

public interface StudentService {
    //查
    PageResult<Student> page(StudentQueryParm studentQueryParm);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(Integer id);

    void violation(Integer id, Integer score);
}
