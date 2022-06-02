package com.example.score.service;

import com.example.score.dao.UserMapper;
import com.example.score.pojo.DO.StudentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllUserService {

    @Autowired
    private UserMapper userMapper;

    public Integer teAdd(String teNum,String teName,String passWd , int collegeId ,int roleId){
        return userMapper.teAdd(teNum,teName,passWd,collegeId,roleId);
    }

    public Integer teDelete(int teId){
        return userMapper.teDelete(teId);
    }

    public Integer teUpdate(String teNum,String teName,String passWd , int collegeId ,int roleId,int teId){
        return userMapper.teUpdate(teNum,teName,passWd,collegeId,roleId,teId);
    }

    public List<StudentDO> selectStudent(String stuNum,String stuName){
        return userMapper.selectStudent(stuNum,stuName);
    }

    public Integer stuAdd(String stuNum,String passWd,String stuName){
        return userMapper.stuAdd(stuNum,passWd,stuName);
    }

    public Integer stuUpdate(String stuNum,String passWd,String stuName,int stuId){
        return userMapper.stuUpdate(stuNum,passWd,stuName,stuId);
    }

    public Integer stuDelete(int stuId){
        return userMapper.stuDelete(stuId);
    }
}
