package com.example.xfash.pojo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParm {
    String name;
    @Min(value = 1, message = "页码最小为1")
    Integer page=1;

    @Min(value = 1, message = "每页条数最小为1")
    @Max(value = 100, message = "页大小最大为100")
    Integer pageSize=10;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate end;
}
