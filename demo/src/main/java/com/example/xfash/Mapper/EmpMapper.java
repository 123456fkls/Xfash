package com.example.xfash.Mapper;

import com.example.xfash.pojo.Emp;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
//    查询所有员工信息记录数
    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
    public Lang count();
//    查询所有员工信息(分页)
    @Select("select emp.*,dept.name from emp left join dept on emp.dept_id = dept.id " +
            "order by emp.update_time " +
            "limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);
}
