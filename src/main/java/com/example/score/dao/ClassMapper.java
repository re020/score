package com.example.score.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ClassMapper {

    /**
     * 获取班级列表
     * @return
     */
    List<Integer> getClassList();
}
