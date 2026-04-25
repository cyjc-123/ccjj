package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.server.ClazzServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzServer clazzServer;
    @GetMapping
    public Result pageSelect(String name, @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
                     @DateTimeFormat(pattern = "yyyy-mm-dd")LocalDate end,@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize){

        log.info("查询分页：{},{},{},{},{}",name,begin,end,page,pageSize);
        PageResult<Clazz>pageResult=clazzServer.PageSelect(name,begin,end,page,pageSize);
        return Result.success(pageResult);
    }


}
