package com.example.score.service;

import com.example.score.dao.ClassStuMapper;
import com.example.score.pojo.DO.StudentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassStuService {

    @Autowired
    private ClassStuMapper classStuMapper;

    public Integer classStuAdd(int classId,int stuId){
        return classStuMapper.classStuAdd(classId,stuId);
    }

    public Integer classStuDelete(int stuId){
        return classStuMapper.classStuDelete(stuId);
    }

    public List<StudentDO> selectClassStudent(String stuNum,String stuName,int classId){
        return classStuMapper.selectClassStudent(stuNum,stuName,classId);
    }

}
