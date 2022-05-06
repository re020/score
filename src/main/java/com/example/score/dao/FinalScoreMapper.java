package com.example.score.dao;

import com.example.score.entity.TeScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FinalScoreMapper {

    /**
     * 通过班级id 学年 学期 获取 学生对教师的所有评分
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    List<TeScore> selectTeScoreByClassId(@Param("classId") int classId, @Param("acaYear") String acaYear, @Param("seme") int seme);

    /**
     * 将赋分后的教师评分插入最终总评分表
     * @param ts
     * @return
     */
    Integer insert(TeScore ts);
}
