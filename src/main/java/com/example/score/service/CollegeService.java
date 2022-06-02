package com.example.score.service;

import com.example.score.dao.CollegeMapper;
import com.example.score.pojo.DO.CollegeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    public List<String> getAllCollegeName(){
       return collegeMapper.getAllCollegeName();
    }

    public List<CollegeDO> getAllCollege(){
        return collegeMapper.getAllCollege();
    }

    public Integer getCollegeIdByName(String collegeName){
        return collegeMapper.getCollegeIdByName(collegeName);
    }
    public Integer collegeAdd(String collegeName){
        return collegeMapper.collegeAdd(collegeName);
    }
    public Integer collegeDelete(int collegeId){
        return collegeMapper.collegeDelete(collegeId);
    }
    public Integer collegeUpdate(String collegeName,int collegeId){
        return collegeMapper.collegeUpdate(collegeName,collegeId);
    }
}
