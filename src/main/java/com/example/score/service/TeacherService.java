package com.example.score.service;

import com.example.score.dao.TeacherMapper;
import com.example.score.dto.ClassScoreDTO;
import com.example.score.dto.CollegeScoreDTO;
import com.example.score.dto.Subject;
import com.example.score.entity.CollegeScore;
import com.example.score.entity.TeScore;
import com.example.score.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TeacherService {


    /**
     * 通过班级id 学年 学期 获取教师在班级的排名
     * @param clName
     * @param acaYear
     * @param seme
     * @return
     */
    public List<TeScore> findTeClassRanking(String clName,String acaYear,int seme);

    /**
     * 通过学院id 学年 学期 获取教师在学院的排名
     * @param collegeId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<CollegeScore> findTeCollegeRanking(int collegeId,String acaYear ,int seme);

    /**
     * 将处理好的教师评分插入学院评分表
     * @param cs
     * @return
     */
    public Integer insertCollegeScore(CollegeScore cs);

    /**
     * 获取教师id
     * @return
     */
    public List<Integer> getTeIds();

    /**
     * 通过教师id 学年 学期 获取教师在不同班级的评分
     * @param teId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<TeScore> getClassScores(int teId,String acaYear,int seme);

    /**
     * 获取教师用户密码
     * @param teNum
     * @return
     */
    public String getTePassWd(String teNum);

    /**
     * 通过教师工号获取该教师所教班级
     * @param teNum
     * @return
     */
    public List<Integer> getClassIdByTeNum(String teNum,String acaYear,int seme);
    /**
     * 获取教师学科集合
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<Subject> getTeList(int classId,String acaYear,int seme);

    public int getTeCollegeId(int teId);

    public List<ClassScoreDTO> geTeClassScore(String clName,String acaYear,int seme);

    public List<CollegeScoreDTO> geTeCollegeScore(int college, String acaYear, int seme);

    public int getCollegeIdByTeNum(String teNum);

    public int getTeIdByTeName(String teName);

    /**
     * 通过教职工号查找教师
     * @param username
     * @return
     */
    Teacher findTeacherByName(String username);
}
