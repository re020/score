package com.example.score.controller;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.pojo.dto.*;
import com.example.score.entity.AllScore;
import com.example.score.service.StudentService;
import com.example.score.service.TeScoreService;
import com.example.score.service.TeacherService;
import com.example.score.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
        System.out.println("studentDTO:"+studentDTO);
        Integer classId = studentService.getClassIdByStuId(studentDTO.getStuNum());
        System.out.println("classId:" +classId);
        List<Subject> teList = teacherService.getTeList(classId, studentDTO.getAcaYear(), studentDTO.getSeme());
        for (Subject subject : teList) {
            BigDecimal teScore = teScoreService.getFirstScore(classId, studentDTO.getStuNum(), subject.getTeId(), studentDTO.getAcaYear(), studentDTO.getSeme());
            subject.setTeScore(teScore);
            System.out.println(subject);
        }

        return JSON.toJSONString(teList);
    }

    @RequestMapping("/insertAllScore")
    @ResponseBody
    public Result insertAllScore(@RequestBody AllScoreDTO allScoreDTO) {

        try {
            System.out.println(allScoreDTO+"allScoreDTO:");

            Integer teId = teacherService.getTeIdByTeName(allScoreDTO.getTeName());
            AllScore allScore = studentService.getByStuNum(allScoreDTO.getStuNum());
            System.out.println(allScore);
            allScore.setTeScore(allScoreDTO.getTeScore());
            allScore.setAcaYear(allScoreDTO.getAcaYear());
            allScore.setSeme(allScoreDTO.getSeme());
            allScore.setTeId(teId);
            int i = teScoreService.insertAllScore(allScore);
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("评分失败");
        }

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