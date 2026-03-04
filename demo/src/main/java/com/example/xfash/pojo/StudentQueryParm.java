package com.example.xfash.pojo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StudentQueryParm {
    String name;
    @Min(value = 1, message = "学历值范围为 1-6")
    @Max(value = 6, message = "学历值范围为 1-6")
    Integer degree;

    Integer clazzId;

    @Min(value = 1, message = "页码最小值为 1")
    Integer page = 1;

    @Min(value = 1, message = "每页条数页码最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    Integer pageSize = 10;
}
