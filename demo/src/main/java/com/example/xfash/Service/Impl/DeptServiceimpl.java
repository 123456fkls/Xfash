package com.example.xfash.Service.Impl;

import com.example.xfash.Exception.BusinessException;
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
        if (id == null) {
            throw new BusinessException("部门 ID 不能为空");
        }
        Dept dept = deptMapper.searchById(id);
        if (dept == null) {
            throw new BusinessException("部门不存在");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public Dept getById(Integer id) {
        if (id == null) {
            throw new BusinessException("部门 ID 不能为空");
        }
        Dept dept = deptMapper.searchById(id);
        if (dept == null) {
            throw new BusinessException("部门不存在");
        }
        return dept;
    }

    @Override
    public void insert(Dept name) {
        if (name == null || name.getName() == null || name.getName().trim().isEmpty()) {
            throw new BusinessException("部门名称不能为空");
        }
        name.setCreateTime(LocalDateTime.now());
        name.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(name);
    }

    @Override
    public void update(Dept dept) {
        if (dept == null || dept.getId() == null) {
            throw new BusinessException("部门 ID 不能为空");
        }
        if (dept.getName() == null || dept.getName().trim().isEmpty()) {
            throw new BusinessException("部门名称不能为空");
        }
        Dept existingDept = deptMapper.searchById(dept.getId());
        if (existingDept == null) {
            throw new BusinessException("部门不存在");
        }
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}