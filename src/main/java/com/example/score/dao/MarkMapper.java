package com.example.score.dao;

import com.example.score.pojo.dto.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生打分
 */
@Mapper
public interface MarkMapper {

    /**
     * 根据班级id  学年 学期 获取学科列表
     * @param cid
     * @param year
     * @param seme
     * @return
     */
    List<Subject> getSubjects(@Param("cid") int cid, @Param("year") String year, @Param("seme") int seme);

    /**
     * 打分
     * @param sid
     * @param cid
     * @param year
     * @param seme
     * @param score
     * @return
     */
    int mark(@Param("sid") int sid, @Param("cid") int cid, @Param("year") String year, @Param("seme") int seme, @Param("score") int score);
}
