package com.example.xfash.Mapper;

import com.example.xfash.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    //方式一 ：使用@Results注解
//    @Results({
//            @Result(column = "create_time",property = "createTime"),
//            @Result(column = "update_time",property = "updateTime")
//    })//告诉MyBatis如何将数据库字段映射到实体类中
    //查询所有部门信息
    //方式二：起别名
    @Select("select id,name,create_time ,update_time from dept order by update_time desc;")
    List<Dept> findAll();
    //根据id 删除部门
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);
    //增加部门
    @Insert("insert into dept(name,create_time,update_time)values (#{name},#{createTime},#{updateTime})")
    void insert(Dept name);
    //根据id查询部门
    @Select("select id,name,create_time ,update_time from dept where id=#{id};")
    Dept searchById(Integer id);
    //修改部门
    @Update("update dept set name=#{name},update_time=#{updateTime}where id=#{id};")
    void update(Dept dept);
}
