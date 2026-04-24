package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.server.ReportServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportServer reportServer;
    @GetMapping("/empJobData")
    public Result getJob(){
        log.info("员工职位人数：");
       JobOption jobOption= reportServer.getJob();
       return Result.success(jobOption);
    }
    @GetMapping("/empGenderData")
    public  Result getGender(){
        log.info("员工性别：");
        List<Map<String,Object>> gender=reportServer.getGender();
        return Result.success(gender);
    }
}
