package com.example.score.dao;

import com.example.score.pojo.dto.ClassScoreDTO;
import com.example.score.pojo.dto.CollegeScoreDTO;
import com.example.score.pojo.dto.Subject;
import com.example.score.entity.CollegeScore;
import com.example.score.entity.FinalScore;
import com.example.score.entity.TeScore;
import com.example.score.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface TeacherMapper {

    /**
     * 通过班级id 学年 学期 获取教师在班级的排名
     * @param clName
     * @param acaYear
     * @param seme
     * @return
     */
    List<TeScore> selectClassRanking(@Param("clName") String clName,@Param("acaYear") String acaYear,@Param("seme") int seme);

    /**
     * 通过学院id 学年 学期 获取教师在学院的排名
     * @param collegeId
     * @param acaYear
     * @param seme
     * @return
     */
    List<CollegeScore> selectCollegeRanking(@Param("collegeId") int collegeId,@Param("acaYear") String acaYear,@Param("seme") int seme);

    /**
     * 将处理好的教师评分插入学院评分表
     * @param cs
     * @return
     */
    Integer insert(CollegeScore cs);

    /**
     * 获取教师id
     * @return
     */
    List<Integer> getTeIds();

    /**
     * 通过教师id 学年 学期 获取教师在不同班级的评分
     * @param teId
     * @param acaYear
     * @param seme
     * @return
     */
    List<FinalScore> selectClassScores(@Param("teId") int teId, @Param("acaYear") String acaYear, @Param("seme") int seme);


    /**
     * 获取教师用户密码
     * @param teNum
     * @return
     */
    String getTePassWd(String teNum);

    /**
     * 通过教师工号获取该教师所教班级
     * @param teNum
     * @return
     */
    List<Integer> getClassIdByTeNum(@Param("teNum") String teNum,@Param("acaYear") String acaYear,@Param("seme") int seme);

    /**
     * 获取教师学科集合
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    List<Subject> getTeList(@Param("classId") int classId,@Param("acaYear") String acaYear,@Param("seme") int seme);

    int getTeCollegeId(int teId);

    List<ClassScoreDTO> geTeClassScore(@Param("clName") String clName,@Param("acaYear") String acaYear,@Param("seme") int seme);

    List<CollegeScoreDTO> geTeCollegeScore(@Param("collegeId") int collegeId, @Param("acaYear") String acaYear, @Param("seme") int seme);

    Integer getCollegeIdByTeNum(String teNum);

    Integer getTeIdByTeName(String teName);

    Teacher getTeacherByName(String username);


    String getRole(String teNum);

    Integer getRoleId(String roleName);
}
