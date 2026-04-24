package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.example.anno.Log;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.server.DeptServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //lombok提供
@RestController
public class DeptController {
   //private static final Logger log= LoggerFactory.getLogger(DeptController.class);
   @Autowired
   private DeptServer deptServer;
   @RequestMapping("/depts")
   //GetMapping
   public Result list(){
     // System.out.println("查询");
      log.info("查询全部部门数据");
       List<Dept> deptList=deptServer.findAll();
      return Result.success(deptList);
   }

   /*@DeleteMapping("/depts")
   public Result delete(HttpServletRequest request){
      String idStr=request.getParameter("id");
      int id=Integer.parseInt(idStr);
      System.out.println(id);
      return Result.success();
   }*/

   @Log
   @DeleteMapping("/depts")
//   RequestParam 中required默认为true
   //如果前端传递的请求参数名与服务器端参数名一致，RequestParam 可以省略
   public Result delete(@RequestParam("id") Integer deptid){
      //System.out.println(deptid);
      log.info("删除部门：{}",deptid);
      deptServer.deleteById(deptid);
      return Result.success();
   }

   @Log
   @PostMapping("/depts")
   public Result insert(@RequestBody Dept dept){
     // System.out.println("新增部门："+dept);
   log.info("新增部门：{}",dept);
      deptServer.deptInsert(dept);
      return Result.success();
   }

   @GetMapping("/depts/{id}")
//   如果前端传递的请求参数名与服务器端方法参数名一致，@PathVariable可以省略("id")  路径参数
   public Result getinfo(@PathVariable("id") Integer deptid){
//      System.out.println("根据id查询,id为"+deptid);
      log.info("根据id查询,id为:{}",deptid);
      Dept dept=deptServer.getInfo(deptid);
      return Result.success(dept);
   }
   @Log
   @PutMapping("/depts")
   public Result update(@RequestBody Dept dept){
       log.info("修改部门：{}",dept);
         deptServer.deptUpdate(dept);
      return Result.success();
   }
}
