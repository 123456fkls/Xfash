package com.example.xfash.Service;

import com.example.xfash.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();//查询所有部门信息
//根据id 删除部门
    void deleteById(Integer id);
//增加部门
    void insert(Dept name);
//根据Id查询部门
    Dept getById(Integer id);
//修改部门
    void update(Dept dept);
}
