package com.example.score.service.impl;

import com.example.score.dao.StudentMapper;
import com.example.score.entity.*;
import com.example.score.service.StudentService;
import com.example.score.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("student")
public class StudentServiceImpl implements StudentService, UserService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 通过班级id 学年 学期 获取教师
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<ClassTeacher> findTeById(int classId,String acaYear,int seme) {
        return studentMapper.selectTe(classId,acaYear,seme);
    }
    /**
     * 通过教师id获取教师姓名
     * @param teId
     * @return
     */
    public Teacher findTeNameById(int teId){
        return studentMapper.selectTeName(teId);
    }

    /* *//**
     * 获取学生用户密码
     * @param stuNum
     * @return
     */
    public String getStuPassWd(String stuNum){
        return studentMapper.getStuPassWd(stuNum);
    }
    /* *//**
     * 通过学号获取班级
     * @param stuNum
     * @return
     */
    public Integer getClassIdByStuId(String stuNum){
        return studentMapper.getClassIdByStuId(stuNum);
    }
    public AllScore getByStuNum(String stuNum){
        return studentMapper.getByStuNum(stuNum);
    }

    @Override
    public Student findStudentByName(String username) {
        return studentMapper.getStudentByUsername(username);
    }


    /* *//**
     * 通过学号查找学生
     * @param username
     * @return
     */
    @Override
    public User findUserByName(String username) {
        Student student = this.findStudentByName(username);
        if (student != null) {
            return new User(student.getStuNum(), student.getPassWd(), "student");
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("学号或密码错误");
        }
        return user;
    }


}
