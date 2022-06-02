package com.example.score.service;

import com.example.score.dao.ClassTeMapper;
import com.example.score.entity.ClassTeacher;
import com.example.score.pojo.DO.TeacherDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTeService {

    @Autowired
    private ClassTeMapper classTeMapper;

    /**
     *获取所有班级的教师
     * @return
     */
   public List<ClassTeacher> select(String acaYear,int seme){
       return classTeMapper.select(acaYear,seme);
   }


   public Integer classTeAdd(int classId, int teId){
    return classTeMapper.classTeAdd(classId,teId);
   }

   public Integer subjectAdd(String subject , String acaYear , int seme,int classTeId){
       return classTeMapper.subjectAdd(subject,acaYear,seme,classTeId);
   }

   public Integer classTeDelete(int classTeId){
       return classTeMapper.classTeDelete(classTeId);
   }

    public List<ClassTeacher> getClassTeacher(int classId){
        return classTeMapper.getClassTeacher(classId);
    }

    public List<ClassTeacher> selectClassTeacherBy(int classId,String teName,String subject,String acaYear,int seme){
        return classTeMapper.selectClassTeacherBy(classId,teName,subject,acaYear,seme);
    }

    public List<TeacherDO> getAllTeacher(String teName,String collegeName){
       return classTeMapper.getAllTeacher(teName,collegeName);
    }

}
