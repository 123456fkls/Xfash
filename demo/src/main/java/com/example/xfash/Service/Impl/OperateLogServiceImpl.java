package com.example.xfash.Service.Impl;

import com.example.xfash.Mapper.OperateLogMapper;
import com.example.xfash.Service.OperateLogService;
import com.example.xfash.pojo.OperateLog;
import com.example.xfash.pojo.OperateLogParm;
import com.example.xfash.pojo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(OperateLogParm operateLogParm) {
        //设置分页参数
        PageHelper.startPage(operateLogParm.getPage(), operateLogParm.getPageSize());
        //执行查询
        List<OperateLog> operateLogList = operateLogMapper.list(operateLogParm);
        //解析结果并封装数据
        Page<OperateLog> p = (Page<OperateLog>) operateLogList;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());


    }
}
