package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Clazz;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> PageSelect(String name, LocalDate begin, LocalDate end);
}
