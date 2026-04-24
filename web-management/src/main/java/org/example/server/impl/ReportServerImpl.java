package org.example.server.impl;

import org.example.mapper.EmpMapper;
import org.example.pojo.JobOption;
import org.example.server.ReportServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReportServerImpl implements ReportServer {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getJob() {
        List<Map<String, Object>> countEmpJOb = empMapper.countEmpJOb();
        List<Object> jobList= countEmpJOb.stream().map(empjob->empjob.get("pos")).toList();
        List<Object> dataList= countEmpJOb.stream().map(empjob->empjob.get("num")).toList();
        return new JobOption(jobList,dataList);

    }

    @Override
    public List<Map<String, Object>> getGender() {
        return empMapper.getGender();

    }
}
