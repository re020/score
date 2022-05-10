package com.example.score.dao;

import com.example.score.dto.ClassScoreDTO;
import com.example.score.entity.AllScore;

import com.example.score.entity.TeScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeScoreMapper {


    /**
     * 将学生对教师的评分插入all_score表
     * @param as
     * @return
     */
    Integer insertAllScore(AllScore as);

    /**
     * 获取所有学生
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    List<AllScore> selectScore( @Param("teId") int teId,@Param("classId") int classId, @Param("acaYear") String acaYear, @Param("seme") int seme);

    Integer insertTeScore(TeScore teScore);

    Integer insertFinalScore(TeScore teScore);

    List<String> getClNames();

    List<ClassScoreDTO> geScore(@Param("clName") String clName,@Param("acaYear") String acaYear,@Param("seme") int seme);
 }
