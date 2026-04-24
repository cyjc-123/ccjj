package org.example.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;
import org.example.pojo.LoginInfo;
import org.example.pojo.PageResult;
import org.example.server.EmpServer;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServerImpl implements EmpServer {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    /*@Override
    public PageResult<Emp> selectPage(Integer page, Integer pageSize) {
          Long count=empMapper.count();
         List<Emp> list=empMapper.list((page-1)*pageSize,pageSize);
         return new PageResult<Emp>(count,list);

    }*/
    @Override
    public PageResult<Emp> selectPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp>list=empMapper.list(name, gender,begin,end);
        Page<Emp> p= (Page<Emp>) list;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

@Transactional  //默认运行时异常回滚
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insert(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByid(ids);
        empExprMapper.deleteByempid(ids);
    }

    @Override
    public Emp getinfo(Integer id) {
        Emp emp=empMapper.getinfo(id);
        return emp;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        empExprMapper.deleteByempid(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insert(exprList);

        }
    }

    @Override
    public LoginInfo login(Emp emp) {
    Emp e= empMapper.selectByUsernameAndPassword(emp);
        if (e==null){
            return null;
        }
        Map<String, Object> claim=new HashMap<>();
        claim.put("id",e.getId());
        claim.put("username",e.getUsername());
        String jwt = JwtUtils.generateJwt(claim);
        return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);

    }
}
