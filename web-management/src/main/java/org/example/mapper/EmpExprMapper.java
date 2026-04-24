package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insert(List<EmpExpr> exprList);

    void deleteByempid(List<Integer> ids);
}
