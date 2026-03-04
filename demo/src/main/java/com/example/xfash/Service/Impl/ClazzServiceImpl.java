package com.example.xfash.Service.Impl;

import com.example.xfash.Exception.BusinessException;
import com.example.xfash.Mapper.ClazzMapper;
import com.example.xfash.Mapper.DeptMapper;
import com.example.xfash.Service.ClazzService;
import com.example.xfash.pojo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    //查
    @Override
    public PageResult<Clazz> page(ClazzQueryParm clazzQueryParm) {
        //设置分页参数
        PageHelper.startPage(clazzQueryParm.getPage(), clazzQueryParm.getPageSize());
        //执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParm);
        //解析结果并封装数据
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());

    }

    //增
    @Override
    public void add(Clazz clazz) {
        if (clazz == null) {
            throw new BusinessException("班级信息不能为空");
        }
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    //根据id查询
    @Override
    public Clazz getById(Integer id) {
        if (id == null) {
            throw new BusinessException("班级 ID 不能为空");
        }
        Clazz clazz = clazzMapper.getById(id);
        if (clazz == null) {
            throw new BusinessException("班级不存在");
        }
        return clazz;
    }

    //改
    @Override
    public void update(Clazz clazz) {
        if (clazz == null || clazz.getId() == null) {
            throw new BusinessException("班级 ID 不能为空");
        }
        Clazz existingClazz = clazzMapper.getById(clazz.getId());
        if (existingClazz == null) {
            throw new BusinessException("班级不存在");
        }
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new BusinessException("班级 ID 不能为空");
        }
        Clazz clazz = clazzMapper.getById(id);
        if (clazz == null) {
            throw new BusinessException("班级不存在");
        }
        clazzMapper.delete(id);
    }

    //查询所有班级
    @Override
    public List<Clazz> list() {
        return clazzMapper.clazzlist();
    }
}
