package org.example.server;

import org.example.pojo.Dept;

import java.util.List;


public interface DeptServer {

    List<Dept> findAll();

    void deleteById(Integer id);

    void deptInsert(Dept dept);

    Dept getInfo(Integer deptid);

    void deptUpdate(Dept dept);
}
