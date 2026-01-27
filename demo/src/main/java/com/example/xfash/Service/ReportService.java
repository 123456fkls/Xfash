package com.example.xfash.Service;

import com.example.xfash.pojo.ClassOption;
import com.example.xfash.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String,Object>> getEmpGenderData();

    ClassOption getStudentClassData();

    List<Map<String, Object>> getStudentDegreeData();
}
