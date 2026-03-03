package com.example.xfash.Service.Impl;

import com.example.xfash.Mapper.EmpExprMapper;
import com.example.xfash.Mapper.EmpMapper;
import com.example.xfash.Service.EmpService;
import com.example.xfash.Utils.JwtUtils;
import com.example.xfash.pojo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.Long;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //解析结果并封装数据
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    //新增员工
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        //保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //密码加密
        if (emp.getPassword() != null && !emp.getPassword().isEmpty()) {
            emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        }
        empMapper.insert(emp);
        //保存员工工作信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //遍历集合，为empiD赋值
            exprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    //删除员工
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        //删除员工基本信息
        empMapper.deleteByIds(ids);
        //删除员工工作信息
        empExprMapper.deleteByEmpIds(ids);

    }

    //修改员工（查询员工回显）
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    //修改信息
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        if (emp.getPassword() != null && !emp.getPassword().isEmpty()) {
            emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        } else {
            emp.setPassword(null);
        }
        //修改员工基本信息
        empMapper.update(emp);
        //修改员工工作信息
        //先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //后添加
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //遍历集合，为empid赋值
            exprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    //查询所有员工
    @Override
    public List<Emp> list() {
        return empMapper.empList();
    }

    @Override
    public LoginInfo login(Emp emp) {
        //调用接口根据用户名和密码查询员工
        Emp e = empMapper.getByUsername(emp.getUsername());
        if (e != null) {
            boolean matches = false;

            // 判断数据库密码是否为 BCrypt 格式
            if (e.getPassword() != null && e.getPassword().startsWith("$2a$")) {
                // BCrypt 加密密码
                matches = passwordEncoder.matches(emp.getPassword(), e.getPassword());
            } else {
                // 明文密码（临时兼容）
                matches = emp.getPassword().equals(e.getPassword());
            }
            //判断是否存在这个员工
            if (matches) {
                log.info("登录成功：{}", e);
                //生成jwt令牌
                Map<String, Object> claims = Map.of("id", e.getId(), "username", e.getUsername(), "name", e.getName());
                String jwt = JwtUtils.generateToken(claims);

                return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
            }
        }
        //不存在返回null
        return null;

    }
}


