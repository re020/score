package com.example.score.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    Integer teAdd(@Param("te_number") String teNum,@Param("name") String teName,@Param("password") String passWd,@Param("college_id") int collegeId,@Param("role_id") int roleId);


    Integer stuAdd(@Param("sno") String stuNum,@Param("password") String passWd,@Param("name") String stuName);


    Integer teDelete();

    Integer stuDelete();

    Integer teUpdate();

    Integer stuUpdate();

}
