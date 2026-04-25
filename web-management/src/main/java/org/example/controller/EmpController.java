package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.example.pojo.Emp;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.server.EmpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpServer empServer;
    @GetMapping
    public Result selectPage(@RequestParam(defaultValue ="1") Integer page,
                             @RequestParam(defaultValue ="10")Integer pageSize,
                             String name, Integer gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询：{},{},{},{},{}，{}",page,pageSize,name,gender,begin,end);
        PageResult<Emp> pageResult=empServer.selectPage(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增部门：{}",emp);
        empServer.save(emp);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
      log.info("删除员工：{}", ids);
      empServer.delete(ids);
      return Result.success();
    }
    @GetMapping("{id}")
        public Result getinfo(@PathVariable Integer id){
        log.info("查询：{}",id);
        Emp emp=empServer.getinfo(id);
        return Result.success(emp);
        }
        @PutMapping
        public Result update(@RequestBody Emp emp){
         log.info("修改：{}",emp);
         empServer.update(emp);
         return Result.success();
        }

}
