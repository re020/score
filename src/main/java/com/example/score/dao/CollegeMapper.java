package com.example.score.dao;

import com.example.score.pojo.DO.CollegeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollegeMapper {

    Integer collegeAdd(String CollegeName);

    Integer collegeDelete(int collegeId);

    Integer collegeUpdate(@Param("collegeName") String collegeName,@Param("collegeId") int collegeId);

    Integer getCollegeIdByName(String collegeName);

    List<String> getAllCollegeName();

    List<CollegeDO> getAllCollege();

}
