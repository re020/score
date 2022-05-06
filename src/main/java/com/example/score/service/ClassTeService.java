package com.example.score.service;

import com.example.score.dao.ClassTeMapper;
import com.example.score.entity.ClassTeacher;
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



}
