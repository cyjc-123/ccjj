package org.example.server.impl;

import org.example.mapper.DeptMapper;
import org.example.pojo.Dept;
import org.example.server.DeptServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServerImpl implements DeptServer {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id );
    }

    @Override
    public void deptInsert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.deptInsert(dept);
    }

    @Override
    public Dept getInfo(Integer deptid) {

        return deptMapper.getInfo(deptid);
    }

    @Override
    public void deptUpdate(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.deptUpdate(dept);
    }


}
