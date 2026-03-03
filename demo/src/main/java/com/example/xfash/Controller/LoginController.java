package com.example.xfash.Controller;

import com.example.xfash.Service.EmpService;
import com.example.xfash.Utils.JwtUtils;
import com.example.xfash.pojo.Emp;
import com.example.xfash.pojo.LoginInfo;
import com.example.xfash.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private EmpService empService;
    @Autowired
    private JwtUtils jwtUtils;
    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录：{}", emp);
        LoginInfo loginInfo = empService.login(emp);
       if (loginInfo != null) {
           return Result.success(loginInfo);
       }else{
           return Result.error("用户名或密码错误");
       }
    }
}
