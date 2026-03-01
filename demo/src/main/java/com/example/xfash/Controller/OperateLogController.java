package com.example.xfash.Controller;

import com.example.xfash.Service.OperateLogService;
import com.example.xfash.pojo.OperateLog;
import com.example.xfash.pojo.OperateLogParm;
import com.example.xfash.pojo.PageResult;
import com.example.xfash.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {
    @Autowired
    private OperateLogService operateLogService;
    @GetMapping("/page")
    public Result page(OperateLogParm operateLogParm){
        log.info("分页查询:{}",operateLogParm );
        PageResult<OperateLog> operateLogList = operateLogService.page(operateLogParm);
        return Result.success(operateLogList);
    }
}
