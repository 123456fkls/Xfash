package com.example.xfash.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Dept {
    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Size(max = 20, message = "部门名称长度不能超过20")
    private String name;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
