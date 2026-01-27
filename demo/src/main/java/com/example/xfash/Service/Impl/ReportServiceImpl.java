package com.example.xfash.Service.Impl;

import com.example.xfash.Mapper.EmpMapper;
import com.example.xfash.Mapper.StudentMapper;
import com.example.xfash.Service.ReportService;
import com.example.xfash.pojo.ClassOption;
import com.example.xfash.pojo.JobOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        //1.调用Mapper接口
        List<Map<String, Object>> list = empMapper.countEmpJoData();
        //2.组装结果并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();

    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
       return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClassOption getStudentClassData() {
        //1.调用Mapper接口
        List<Map<String, Object>> list = studentMapper.countStudentClassData();
        //2.组装结果并返回
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("class")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClassOption(clazzList, dataList);

    }
}
