package com.example.score.dao;

import com.example.score.pojo.DO.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer teAdd(@Param("teNum") String teNum,@Param("teName") String teName,@Param("passWd") String passWd,@Param("collegeId") int collegeId,@Param("roleId") int roleId);


    Integer stuAdd(@Param("stuNum") String stuNum,@Param("passWd") String passWd,@Param("stuName") String stuName);

    List<StudentDO> selectStudent(@Param("stuNum") String stuNum ,@Param("stuName") String stuName);

    Integer teDelete(int teId);

    Integer stuDelete(int stuId);

    Integer teUpdate(@Param("teNum") String teNum,@Param("teName") String teName,@Param("passWd") String passWd,@Param("collegeId") int collegeId,@Param("roleId") int roleId,@Param("teId") int teId);

    Integer stuUpdate(@Param("stuNum") String stuNum,@Param("passWd") String passWd,@Param("stuName") String stuName,@Param("stuId") int stuId);


}
