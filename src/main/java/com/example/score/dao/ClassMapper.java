package com.example.score.dao;

import com.example.score.pojo.DO.ClassDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassMapper {

    /**
     * 获取班级列表
     * @return
     */
    List<Integer> getClassList();

    Integer classAdd(@Param("clName") String clName,@Param("collegeId") int collegeId);

    Integer classUpdate(@Param("clName") String clName,@Param("collegeId") int collegeId,@Param("classId") int classId);

    Integer classDelete(int classId);

    Integer getClassIdByClName(String clName);

    List<ClassDO> getAllClass(@Param("clName") String clName,@Param("collegeName") String collegeName);



}
