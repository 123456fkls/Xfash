package com.example.xfash.pojo;

import lombok.Data;

@Data
public class StudentQueryParm {
    String name;
    Integer degree;
    Integer clazzId;
    Integer page=1;
    Integer pageSize=10;
}
