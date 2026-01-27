package com.example.xfash.Controller;

import com.example.xfash.Service.ReportService;
import com.example.xfash.pojo.ClassOption;
import com.example.xfash.pojo.JobOption;
import com.example.xfash.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    //员工职位人数数据统计报表
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("生成员工数据统计报表");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("生成员工性别数据统计报表");
        List<Map<String,Object>> genderlist = reportService.getEmpGenderData();
        return Result.success(genderlist);
    }
    //学生学历数据统计报表
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("生成学生学历数据统计报表");
        List<Map<String,Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
    //学生班级数据统计报表
    @GetMapping("/studentCountData")
    public Result getStudentClassData() {
        log.info("生成学生班级数据统计报表");
         ClassOption classOption = reportService.getStudentClassData();
        return Result.success(classOption);
    }
}
