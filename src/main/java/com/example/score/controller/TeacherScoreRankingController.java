package com.example.score.controller;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.pojo.DO.RoleDO;
import com.example.score.pojo.dto.*;
import com.example.score.service.*;
import com.example.score.utils.ResultUtils;
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
    private TeScoreService teScoreService;
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
    public Result enDoScore(@RequestBody ClassDTO classDTO){
        try {
            System.out.println(classDTO);
            enDoService.insetTeScore(classDTO.getAcaYear(),classDTO.getSeme());
            enDoService.insertEnDoScore(classDTO.getAcaYear(),classDTO.getSeme());
            return ResultUtils.success("赋分成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("赋分失败");
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody TeUserDTO teacher) {
        if (teacher.getPassWd().equals(teacherService.getTePassWd(teacher.getTeNum()))) {
            String role = teacherService.getRole(teacher.getTeNum());
            RoleDO roleDO = new RoleDO();
            roleDO.setRoleName(role);
            roleDO.setTeNum(teacher.getTeNum());
            return JSON.toJSONString(roleDO);
        } else {
            return JSON.toJSONString(200);
        }
    }

    @RequestMapping("/getClassList")
    @ResponseBody
    public String getClassList(@RequestBody TeacherDTO teacherDTO){
        List<Integer> classList = teacherService.getClassIdByTeNum(teacherDTO.getTeNum(),teacherDTO.getAcaYear(),teacherDTO.getSeme());
        return JSON.toJSONString(classList);
    }

    @RequestMapping("/getFirstScore")
    @ResponseBody
    public String getFirstScore(@RequestBody ClassDTO classDTO){
        List<ClassScoreDTO> score = teScoreService.getScore(classDTO.getClName(), classDTO.getAcaYear(), classDTO.getSeme());
        return JSON.toJSONString(score);
    }

    @RequestMapping("/getAllClassList")
    @ResponseBody
    public String getAllClassList(){
        List<String> clNames = teScoreService.getClNames();
        return  JSON.toJSONString(clNames);
    }
}

