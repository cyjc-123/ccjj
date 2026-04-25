package org.example.server;

import org.example.pojo.Clazz;
import org.example.pojo.PageResult;

import java.time.LocalDate;

public interface ClazzServer {
    PageResult<Clazz> PageSelect(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);
}
