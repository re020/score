package com.example.score.service;

import com.example.score.dao.StudentMapper;
import com.example.score.entity.AllScore;
import com.example.score.entity.ClassTeacher;
import com.example.score.entity.Student;
import com.example.score.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    /**
     * 通过班级id 学年 学期 获取教师
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<ClassTeacher> findTeById(int classId,String acaYear,int seme);

    /**
     * 通过教师id获取教师姓名
     * @param teId
     * @return
     */
    public Teacher findTeNameById(int teId);

    /**
     * 获取学生用户密码
     * @param stuNum
     * @return
     */
    public String getStuPassWd(String stuNum);

    /**
     * 通过学号获取班级
     * @param stuNum
     * @return
     */
    public Integer getClassIdByStuId(String stuNum);

    public AllScore getByStuNum(String stuNum);
    /**
     * 通过学号查找学生
     * @param username
     * @return
     */
    Student findStudentByName(String username);
}
