package com.example.xfash.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String phone;
    private Integer job;
    private Integer salary;
    private String image;
    private LocalDate entryDate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    //封装部门名称
    private String deptName;
    //封装工作经历
    private List<EmpExpr> exprList;


}
