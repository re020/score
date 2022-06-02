package com.example.score.controller;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.pojo.DO.ClassStuDO;
import com.example.score.pojo.DO.ClassTeDO;
import com.example.score.pojo.DO.StudentDO;
import com.example.score.pojo.DO.TeacherDO;
import com.example.score.pojo.dto.ClassStuDTO;
import com.example.score.pojo.dto.ClassTeDTO;
import com.example.score.service.ClassStuService;
import com.example.score.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/adminClassStu")
@CrossOrigin
public class AdminClassStuController {

    @Autowired
    private ClassStuService classStuService;

    @RequestMapping("/classStuAdd")
    @ResponseBody
    public Result classTeAdd(@RequestBody ClassStuDTO classStuDTO){

        System.out.println(classStuDTO);
        List<ClassStuDO> classStuDOList = classStuDTO.getClassStuDOs();

        try {
            for (ClassStuDO classStuDO : classStuDOList) {
                classStuService.classStuAdd(classStuDO.getClassId(),classStuDO.getStuId());
            }
            return ResultUtils.success(classStuDOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("添加失败");
        }
    }

    @RequestMapping("/classStuDelete")
    @ResponseBody
    public Result classTeAdd(@RequestBody  TeacherDO teacherDO){
       try {
           Integer i = classStuService.classStuDelete(teacherDO.getTeId());
           return ResultUtils.success(i);
       }catch (Exception e){
           e.printStackTrace();
           return ResultUtils.fail("删除失败");
       }
    }


    @RequestMapping("/selectClassStudent")
    @ResponseBody
    public String selectClassStudent(@RequestBody  StudentDO studentDO){

        List<StudentDO> studentDOS = classStuService.selectClassStudent(studentDO.getStuNum(), studentDO.getStuName(),studentDO.getClassId());
        return JSON.toJSONString(studentDOS);

    }

}
