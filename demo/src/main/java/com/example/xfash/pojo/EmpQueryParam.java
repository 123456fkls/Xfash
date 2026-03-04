package com.example.xfash.pojo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
@Data
public class EmpQueryParam {
    @Min(value = 1, message = "页码最小为1")
    Integer page=1;
    @Min(value = 1, message = "每页记录数最小为1")
    @Max(value = 100, message = "每页记录数最大为100")
    Integer pageSize=10;

    String name;

    @Min(value = 1, message = "性别值范围为 1-2")
    @Max(value = 2, message = "性别值范围为 1-2")
    Integer gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end;
}
