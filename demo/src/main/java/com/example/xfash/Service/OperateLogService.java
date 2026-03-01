package com.example.xfash.Service;

import com.example.xfash.pojo.OperateLog;
import com.example.xfash.pojo.OperateLogParm;
import com.example.xfash.pojo.PageResult;

public interface OperateLogService {

    PageResult<OperateLog> page(OperateLogParm operateLogParm);
}
