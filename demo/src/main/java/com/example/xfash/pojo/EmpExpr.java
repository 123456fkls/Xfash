package com.example.xfash.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpExpr {
    private Integer id;
    private String empId;
    private LocalDate begin;
    private LocalDate end;
    private String company;
    private String jon;
}
