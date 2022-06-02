package com.example.score.controller;

import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.dao.UserMapper;
import com.example.score.pojo.DO.*;
import com.example.score.service.AllUserService;
import com.example.score.service.ClassService;
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
@RequestMapping("/adminUser")
@CrossOrigin
public class AdminUserController {

    @Autowired
    private AllUserService allUserService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/teAdd")
    @ResponseBody
    public Result teAdd(@RequestBody  UserTeDO userTeDO){
        try {
            int roleId = teacherService.getRoleId(userTeDO.getRoleName());
            Integer collegeId = collegeService.getCollegeIdByName(userTeDO.getCollegeName());
            Integer i = allUserService.teAdd(userTeDO.getTeNum(), userTeDO.getTeName(), userTeDO.getPassWd(), collegeId, roleId);
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("添加失败");
        }
    }

    @RequestMapping("/teUpdate")
    @ResponseBody
    public Result teUpdate(@RequestBody  UserTeDO userTeDO){
        try {
            int roleId = teacherService.getRoleId(userTeDO.getRoleName());
            Integer collegeId = collegeService.getCollegeIdByName(userTeDO.getCollegeName());
            Integer i = allUserService.teUpdate(userTeDO.getTeNum(), userTeDO.getTeName(), userTeDO.getPassWd(), collegeId, roleId, userTeDO.getTeId());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("更新失败");
        }
    }

    @RequestMapping("/teDelete")
    @ResponseBody
    public Result teDelete(@RequestBody  TeacherDO teacherDO){
        try {
            Integer i = allUserService.teDelete(teacherDO.getTeId());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("删除失败");
        }
    }

    @RequestMapping("/stuAdd")
    @ResponseBody
    public Result stuAdd(@RequestBody  UserStuDO userStuDO){

        try {
            Integer i = allUserService.stuAdd(userStuDO.getStuNum(), userStuDO.getPassWd(), userStuDO.getStuName());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("添加失败");
        }
    }

    @RequestMapping("/stuUpdate")
    @ResponseBody
    public Result stuUpdate(@RequestBody  UserStuDO userStuDO){

        try {
            Integer i = allUserService.stuUpdate(userStuDO.getStuNum(),userStuDO.getPassWd(),userStuDO.getStuName(),userStuDO.getStuId());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("更新失败");
        }
    }

    @RequestMapping("/stuDelete")
    @ResponseBody
    public Result stuDelete(@RequestBody  StudentDO studentDO){
        try {
            Integer i = allUserService.stuDelete(studentDO.getStuId());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("删除失败");
        }

    }

    @RequestMapping("/selectStudent")
    @ResponseBody
    public String selectTeacher(@RequestBody  StudentDO studentDO){
        System.out.println(studentDO);
        List<StudentDO> student = allUserService.selectStudent(studentDO.getStuNum(), studentDO.getStuName());
        return JSON.toJSONString(student);
    }
}
