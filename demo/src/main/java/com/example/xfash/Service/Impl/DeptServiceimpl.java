package com.example.xfash.Service.Impl;

import com.example.xfash.Mapper.DeptMapper;
import com.example.xfash.Service.DeptService;
import com.example.xfash.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceimpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.searchById(id);
    }

    @Override
    public void insert(Dept name) {
        name.setCreateTime(LocalDateTime.now());
        name.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(name);
    }

    @Override
    public void update(Dept dept) {
        //补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}