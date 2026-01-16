package com.example.xfash.Mapper;

import com.example.xfash.pojo.Emp;
import com.example.xfash.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> exprList);
//批量删除员工信息
    void deleteByEmpIds(List<Integer> empIds);

}
