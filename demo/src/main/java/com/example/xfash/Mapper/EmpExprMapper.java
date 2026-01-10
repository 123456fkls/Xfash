package com.example.xfash.Mapper;

import com.example.xfash.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> exprList);
}
