package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.LoginInfo;
import org.example.pojo.Result;
import org.example.server.EmpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpServer empServer;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录：{}",emp);
        LoginInfo loginInfo=empServer.login(emp);
       if (loginInfo!=null){
           return Result.success(loginInfo);
       }
       return Result.error("用户名或密码错误");
    }
}
