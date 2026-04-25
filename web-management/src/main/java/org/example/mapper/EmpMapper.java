package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    /*@Select("select count(*) from emp")
   public Long count();
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            "order by e.update_time limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);*/
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id  " +
//            "order by e.update_time")
    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
   @Options(useGeneratedKeys = true,keyProperty = "id")//主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
        " VALUES (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByid(List<Integer> ids);

    Emp getinfo(Integer id);

    void update(Emp emp);
    @MapKey("pos")//可不要
    List<Map<String, Object>> countEmpJOb();
   @MapKey("name")
     List<Map<String, Object>> getGender();
   @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}