package com.example.xfash.Mapper;

import com.example.xfash.pojo.Emp;
import com.example.xfash.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //----------------------------------原始分页 ----------------------------------
//    查询所有员工信息记录数
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
//    public Long count();
    //查询所有员工信息(分页)
//    @Select("select emp.*,dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc " +
//            "limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);
//    @Select("select emp.*,dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc ")
    public List<Emp> list(EmpQueryParam empQueryParam);

    //新增员工
    @Options(useGeneratedKeys = true, keyProperty = "id")// Mybatis知识点-----------获取自增主键
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);
//删除员工

    void deleteByIds(List<Integer> ids);

    //修改员工（查询员工回显）
    Emp getById(Integer id);

    //修改员工
    void update(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJoData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    //查找所有员工
    @Select("select * from emp")
    List<Emp> empList();

    //登录
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
