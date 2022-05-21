package com.example.score.controller;

import com.alibaba.fastjson.JSON;
import com.example.score.pojo.dto.*;
import com.example.score.entity.AllScore;
import com.example.score.service.StudentService;
import com.example.score.service.TeScoreService;
import com.example.score.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
@CrossOrigin
public class StudentScoreController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeScoreService teScoreService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/getTeList")
    @ResponseBody
    public String getTeList(@RequestBody StudentDTO studentDTO) {
        //List<ClassScoreDTO> classScore = teacherService.geTeClassScore(classDTO.getClName(), classDTO.getAcaYear(), classDTO.getSeme());
        //studentService.
        //return JSON.toJSONString(classScore);
        Integer classId = studentService.getClassIdByStuId(studentDTO.getStuNum());
        List<Subject> teList = teacherService.getTeList(classId, studentDTO.getAcaYear(), studentDTO.getSeme());
        for (Subject subject : teList) {
            System.out.println(subject);
        }
        return JSON.toJSONString(teList);
    }

    @RequestMapping("/insertAllScore")
    @ResponseBody
    public void insertAllScore(@RequestBody AllScoreDTO allScoreDTO) {

        int teId = teacherService.getTeIdByTeName(allScoreDTO.getTeName());
        AllScore allScore = studentService.getByStuNum(allScoreDTO.getStuNum());
        allScore.setTeScore(allScoreDTO.getTeScore());
        allScore.setAcaYear(allScoreDTO.getAcaYear());
        allScore.setSeme(allScoreDTO.getSeme());
        allScore.setTeId(teId);
        teScoreService.insertAllScore(allScore);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody StuUserDTO stuUserDTO) {
        if (stuUserDTO.getPassWd().equals(studentService.getStuPassWd(stuUserDTO.getStuNum()))) {
            System.out.println("登入成功");
            return JSON.toJSONString(stuUserDTO.getStuNum());
        } else {
            System.out.println("登入失败");
            return JSON.toJSONString("学号或者密码错误");
        }
    }
}