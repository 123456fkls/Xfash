package com.example.xfash.Mapper;

import com.example.xfash.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    //查
    public List<Clazz> list(ClazzQueryParm clazzQueryParm);

    //增
    void add(Clazz clazz);

    //根据id查询
    Clazz getById(Integer id);

    //改
    void update(Clazz clazz);

    //删
    void delete(Integer id);

    List<Clazz> clazzlist();
}
