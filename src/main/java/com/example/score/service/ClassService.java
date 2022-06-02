package com.example.score.service;

import com.example.score.dao.ClassMapper;
import com.example.score.pojo.DO.ClassDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassMapper classMapper;

    /**
     * 获取班级列表
     * @return
     */
    public List<Integer> getClassList(){
        return classMapper.getClassList();
    }

    public Integer classAdd(String clName,int collegeId){
        return classMapper.classAdd(clName,collegeId);
    }


    public Integer classDelete(int classId){
        return classMapper.classDelete(classId);
    }

    public Integer classUpdate(String clName,int collegeId,int classId){
        return classMapper.classUpdate(clName,collegeId,classId);
    }

    public Integer getClassIdByClName(String clName){
        return classMapper.getClassIdByClName(clName);
    }

    public List<ClassDO> getAllClass(String clName,String collegeName){
        return classMapper.getAllClass(clName,collegeName);
    }
}
