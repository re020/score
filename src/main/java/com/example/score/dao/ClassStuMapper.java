package com.example.score.dao;

import com.example.score.pojo.DO.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassStuMapper {

    Integer classStuAdd(@Param("classId") int classId,@Param("stuId") int stuId);

    Integer classStuDelete(int stuId);

    List<StudentDO> selectClassStudent(@Param("stuNum") String stuNum , @Param("stuName") String stuName,@Param("classId") int classId);



}
