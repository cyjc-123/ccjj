package org.example.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.example.server.ClazzServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClazzServerImpl implements ClazzServer {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> PageSelect(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        LocalDate now = LocalDate.now();
        PageHelper.startPage(page,pageSize);
        List<Clazz> list=clazzMapper.PageSelect(name,begin,end);
        Page<Clazz> clazzPage= (Page<Clazz>) list;
        list.forEach(clazz ->
        {
            LocalDate beginDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();
            if (now.isBefore(beginDate))
                clazz.setStatus("未开班");
            else if (now.isAfter(endDate)) {
                clazz.setStatus("已结课");
            }else {
                clazz.setStatus("已开班");
            }

        });
        return new PageResult<>(clazzPage.getTotal(),clazzPage.getResult());
    }
}
