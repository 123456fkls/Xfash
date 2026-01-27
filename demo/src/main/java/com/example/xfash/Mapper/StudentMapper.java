package com.example.xfash.Mapper;

import com.example.xfash.pojo.Student;
import com.example.xfash.pojo.StudentQueryParm;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //查
    public List<Student> list(StudentQueryParm studentQueryParm);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(Integer id);

    void violation(Integer id, Integer score);

    @MapKey("degree")
    List<Map<String, Object>> countStudentDegreeData();

    @MapKey("class")
    List<Map<String, Object>> countStudentClassData();
}
