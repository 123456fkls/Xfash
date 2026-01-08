package com.example.xfash.Service.Impl;

import com.example.xfash.Mapper.EmpMapper;
import com.example.xfash.Service.EmpService;
import com.example.xfash.pojo.Emp;
import com.example.xfash.pojo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
//    //原始分页查询操作
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //调用mapper接口，查询总记录数
//         Long total =empMapper.count();
//        //调用mapper接口，查询结果列表
//        List<Emp> rows = empMapper.list((page-1)*pageSize,pageSize);
//        //组装PageResult对象并返回
//        return new PageResult<Emp>(total,rows);
  //  }
    //PageHelper分页查询
public PageResult<Emp> page(Integer page, Integer pageSize) {
    //设置分页参数
    PageHelper.startPage(page,pageSize);
    //执行查询
    List<Emp> empList = empMapper.list();
    //解析结果并封装数据
    Page<Emp> p = (Page<Emp>) empList;
    return new PageResult<Emp>(p.getTotal(),p.getResult());



      }
}
