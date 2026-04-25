package org.example.server;

import org.example.pojo.Emp;
import org.example.pojo.LoginInfo;
import org.example.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpServer {
    PageResult<Emp> selectPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getinfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
