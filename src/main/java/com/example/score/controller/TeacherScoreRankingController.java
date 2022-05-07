package com.example.score.controller;

import com.alibaba.fastjson.JSON;
import com.example.score.dto.*;
import com.example.score.entity.CollegeScore;
import com.example.score.entity.TeScore;
import com.example.score.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherScoreRankingController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private EnDoService enDoService;
    @Autowired
    private RedisTemplate redisTemplate;



    @RequestMapping("/getClassRanking")
    @ResponseBody
    public String getClassRanking(@RequestBody ClassDTO classDTO){

        List<ClassScoreDTO> scoreDTOS = teacherService.geTeClassScore(classDTO.getClName(), classDTO.getAcaYear(), classDTO.getSeme());
        for (ClassScoreDTO scoreDTO : scoreDTOS) {
            System.out.println(scoreDTO);
        }
        return JSON.toJSONString(scoreDTOS);

    }

    @RequestMapping("/getCollegeRanking")
    @ResponseBody
    public String getCollegeRanking(@RequestBody CollegeDTO collegeDTO){
        System.out.println(collegeDTO);
        int collegeId = teacherService.getCollegeIdByTeNum(collegeDTO.getTeNum());
        System.out.println("collegeId"+collegeId);
        List<CollegeScoreDTO> collegeScoreDTOS = teacherService.geTeCollegeScore(collegeId, collegeDTO.getAcaYear(), collegeDTO.getSeme());
       // List<CollegeScoreDTO> classScoreDTOS = teacherService.geTeCollegeScore(collegeId, collegeDTO.getAcaYear(), collegeDTO.getSeme());
        for (CollegeScoreDTO collegeScoreDTO : collegeScoreDTOS) {
            System.out.println(collegeScoreDTO);
        }
        return JSON.toJSONString(collegeScoreDTOS);
    }

    @RequestMapping("/enDoScore")
    @ResponseBody
    public void enDoScore(@RequestBody ClassDTO classDTO){
        System.out.println(classDTO);
        enDoService.insetTeScore(classDTO.getAcaYear(),classDTO.getSeme());
       enDoService.insertEnDoScore(classDTO.getAcaYear(),classDTO.getSeme());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody TeUserDTO teacher) {
        if (teacher.getPassWd().equals(teacherService.getTePassWd(teacher.getTeNum()))) {
            return JSON.toJSONString(teacher.getTeNum());
        } else {
            return JSON.toJSONString("工号或者密码错误");
        }
    }

    @RequestMapping("/getClassList")
    @ResponseBody
    public String getClassList(@RequestBody TeacherDTO teacherDTO){
        List<Integer> classList = teacherService.getClassIdByTeNum(teacherDTO.getTeNum(),teacherDTO.getAcaYear(),teacherDTO.getSeme());
        return JSON.toJSONString(classList);
    }
}

