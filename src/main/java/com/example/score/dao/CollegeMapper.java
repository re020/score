package com.example.score.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollegeMapper {

    Integer collegeAdd(String CollegeName);

    Integer collegeDelete();

    Integer collegeUpdate();

}
