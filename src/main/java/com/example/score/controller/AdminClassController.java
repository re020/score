package com.example.score.controller;


import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.entity.ClassTeacher;
import com.example.score.pojo.DO.ClassDO;
import com.example.score.pojo.DO.ClassTeDO;
import com.example.score.pojo.dto.ClassTeDTO;
import com.example.score.service.ClassService;
import com.example.score.service.ClassTeService;
import com.example.score.service.CollegeService;
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
@RequestMapping("/adminClass")
@CrossOrigin
public class AdminClassController {


    @Autowired
    private ClassTeService classTeService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private ClassService classService;



    @RequestMapping("/classDelete")
    @ResponseBody
    public Result classDelete(@RequestBody  ClassDO classDO){
      try{
          Integer integer = classService.classDelete(classDO.getClassId());
          return ResultUtils.success(integer);
      }catch (Exception e){
          e.printStackTrace();
          return ResultUtils.fail("删除失败");
      }
    }



    @RequestMapping("/classAdd")
    @ResponseBody
    public Result classAdd(@RequestBody ClassDO classDO) {

        try {
            Integer collegeId = collegeService.getCollegeIdByName(classDO.getCollegeName());
            //Integer classId = classService.getClassIdByClName(classDO.getClName());
            Integer i = classService.classAdd(classDO.getClName(), collegeId);
            return ResultUtils.success(i);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail("添加失败");
        }
    }

    @RequestMapping("/classUpdate")
    @ResponseBody
    public Result classUpdate(@RequestBody ClassDO classDO){

        try {
            Integer collegeId = collegeService.getCollegeIdByName(classDO.getCollegeName());
            // Integer classId = classService.getClassIdByClName(classDO.getClName());
            Integer i = classService.classUpdate(classDO.getClName(), collegeId, classDO.getClassId());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("更新失败");
        }
    }

    @RequestMapping("/getAllClass")
    @ResponseBody
    public String getAllClass( @RequestBody ClassDO classDO){
        List<ClassDO> allClass = classService.getAllClass(classDO.getClName(), classDO.getCollegeName());
        return JSON.toJSONString(allClass);
    }



}
