package com.example.xfash.pojo;

import jakarta.validation.constraints.*;
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
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度必须在2-20之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20之间")
    private String password;

    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度必须在1-20之间")
    private String name;

    @NotNull(message = "性别不能为空")
    @Min(value = 1, message = "性别只能是1或2")
    @Max(value = 2, message = "性别只能是1或2")
    private Integer gender;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone;
    private Integer job;

    @Min(value = 0, message = "薪资不能小于0")
    private Integer salary;
    private String image;

    @NotNull(message = "入职时间不能为空")
    private LocalDate entryDate;

    @NotNull(message = "部门不能为空")
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    //封装部门名称
    private String deptName;
    //封装工作经历
    private List<EmpExpr> exprList;


}
