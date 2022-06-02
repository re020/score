package com.example.score.controller;


import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.entity.ClassTeacher;
import com.example.score.pojo.DO.ClassDO;
import com.example.score.pojo.DO.ClassTeDO;
import com.example.score.pojo.DO.SubjectDO;
import com.example.score.pojo.DO.TeacherDO;
import com.example.score.pojo.dto.ClassTeDTO;
import com.example.score.service.ClassService;
import com.example.score.service.ClassTeService;
import com.example.score.service.TeacherService;
import com.example.score.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/adminClassTe")
@CrossOrigin
public class AdminClassTeController {

    @Autowired
    private ClassTeService classTeService;

    @RequestMapping("/classTeAdd")
    @ResponseBody
    public Result classTeAdd(@RequestBody  ClassTeDTO classTeDTO){
       try {
           List<ClassTeDO> classTeDOList = classTeDTO.getClassTeDOs();
           for (ClassTeDO classTeDO : classTeDOList) {
               int classId = classTeDO.getClassId();
               int teId = classTeDO.getTeId();
               classTeService.classTeAdd(classId,teId);
           }
           return ResultUtils.success(classTeDOList);
       }catch (Exception e){
           e.printStackTrace();
           return ResultUtils.fail("添加失败");
       }
    }

    @RequestMapping("/classTeDelete")
    @ResponseBody
    public Result classTeDelete(@RequestBody  SubjectDO subjectDO){
        try {
            Integer i = classTeService.classTeDelete(subjectDO.getClassTeId());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("删除失败");
        }
    }

 /*   @RequestMapping("/getClassTeacher")
    @ResponseBody
    public String getClassTeacher(@RequestBody int classId){

        List<ClassTeacher> classTeacher = classTeService.getClassTeacher(classId);
        return JSON.toJSONString(classTeacher);
    }*/

    @RequestMapping("/selectClassTeacher")
    @ResponseBody
    public String selectClassTeacherBy( @RequestBody SubjectDO subjectDO){

        List<ClassTeacher> classTeachers = classTeService.selectClassTeacherBy(subjectDO.getClassId(), subjectDO.getTeName(), subjectDO.getSubject(), subjectDO.getAcaYear(), subjectDO.getSeme());
        return JSON.toJSONString(classTeachers);
    }

    @RequestMapping("/selectTeacher")
    @ResponseBody
    public String selectTeacher(@RequestBody  TeacherDO teacherDO){

        List<TeacherDO> allTeacher = classTeService.getAllTeacher(teacherDO.getTeName(), teacherDO.getCollegeName());
        return JSON.toJSONString(allTeacher);
    }
}
