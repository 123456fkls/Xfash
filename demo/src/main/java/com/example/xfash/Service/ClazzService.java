package com.example.xfash.Service;

import com.example.xfash.pojo.Clazz;
import com.example.xfash.pojo.ClazzQueryParm;
import com.example.xfash.pojo.Dept;
import com.example.xfash.pojo.PageResult;
import com.github.pagehelper.Page;

import java.util.List;

public interface ClazzService {

    //查
    PageResult<Clazz> page(ClazzQueryParm clazzQueryParm);

    //增
    void add(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> list();
}
