package com.example.score.dao;

import com.example.score.entity.ClassTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassTeMapper {

    /**
     *获取所有班级的教师
     * @return
     */
    List<ClassTeacher> select(@Param("acaYear") String acaYear ,@Param("seme") int seme);


}
