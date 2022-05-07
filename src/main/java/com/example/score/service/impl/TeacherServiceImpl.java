package com.example.score.service.impl;

import com.example.score.dao.TeacherMapper;
import com.example.score.dto.ClassScoreDTO;
import com.example.score.dto.CollegeScoreDTO;
import com.example.score.dto.Subject;
import com.example.score.entity.*;
import com.example.score.service.TeacherService;
import com.example.score.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacher")
public class TeacherServiceImpl implements TeacherService, UserService {

    @Autowired
    private TeacherMapper teacherMapper;


    /**
     * 通过班级id 学年 学期 获取教师在班级的排名
     * @param clName
     * @param acaYear
     * @param seme
     * @return
     */
    public List<TeScore> findTeClassRanking(String clName,String acaYear,int seme){
        return teacherMapper.selectClassRanking(clName,acaYear,seme);
    }

    /**
     * 通过学院id 学年 学期 获取教师在学院的排名
     * @param collegeId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<CollegeScore> findTeCollegeRanking(int collegeId,String acaYear ,int seme){
        return teacherMapper.selectCollegeRanking(collegeId,acaYear,seme);
    }

    /**
     * 将处理好的教师评分插入学院评分表
     * @param cs
     * @return
     */
    public Integer insertCollegeScore(CollegeScore cs){
        return teacherMapper.insert(cs);
    }

    /**
     * 获取教师id
     * @return
     */
    public List<Integer> getTeIds(){
        return teacherMapper.getTeIds();
    }

    /**
     * 通过教师id 学年 学期 获取教师在不同班级的评分
     * @param teId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<FinalScore> getClassScores(int teId, String acaYear, int seme){
        return teacherMapper.selectClassScores(teId,acaYear,seme);
    }

    /**
     * 获取教师用户密码
     * @param teNum
     * @return
     */
    public String getTePassWd(String teNum){
        return teacherMapper.getTePassWd(teNum);
    }

    /**
     * 通过教师工号获取该教师所教班级
     * @param teNum
     * @return
     */
    public List<Integer> getClassIdByTeNum(String teNum,String acaYear,int seme){
        return teacherMapper.getClassIdByTeNum(teNum,acaYear,seme);
    }
    /**
     * 获取教师学科集合
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<Subject> getTeList(int classId,String acaYear,int seme){
        return teacherMapper.getTeList(classId,acaYear,seme);
    }

    public int getTeCollegeId(int teId){
        return teacherMapper.getTeCollegeId(teId);
    }

    public List<ClassScoreDTO> geTeClassScore(String clName,String acaYear,int seme){
        return teacherMapper.geTeClassScore(clName,acaYear,seme);
    }

    public List<CollegeScoreDTO> geTeCollegeScore(int college, String acaYear, int seme){
        return teacherMapper.geTeCollegeScore(college,acaYear,seme);
    }

    public int getCollegeIdByTeNum(String teNum){
        return teacherMapper.getCollegeIdByTeNum(teNum);
    }

    public int getTeIdByTeName(String teName){
        return teacherMapper.getTeIdByTeName(teName);
    }

    /**
     * 通过教职工号查找教师
     * @param username
     * @return
     */
    @Override
    public Teacher findTeacherByName(String username) {
        return teacherMapper.getTeacherByName(username);
    }

    @Override
    public User findUserByName(String username) {
        Teacher teacher = this.findTeacherByName(username);
        if (teacher != null) {
            return new User(teacher.getTeNum(), teacher.getPassWd(), "teacher");
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("教职工号或密码错误");
        }
        return user;
    }
}
