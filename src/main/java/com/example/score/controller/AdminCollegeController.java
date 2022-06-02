package com.example.score.controller;


import com.alibaba.fastjson.JSON;
import com.example.score.common.Result;
import com.example.score.pojo.DO.CollegeDO;
import com.example.score.service.CollegeService;
import com.example.score.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/adminCollege")
@CrossOrigin
public class AdminCollegeController {

    @Autowired
    private CollegeService collegeService;

    @RequestMapping("/getAllCollegeName")
    @ResponseBody
    public String getAllCollegeName(){
        List<String> allCollegeName = collegeService.getAllCollegeName();
        return JSON.toJSONString(allCollegeName);
    }

    @RequestMapping("/getAllCollege")
    @ResponseBody
    public String getAllCollege(){
        List<CollegeDO> allCollege = collegeService.getAllCollege();
        return JSON.toJSONString(allCollege);
    }

    @RequestMapping("/collegeAdd")
    @ResponseBody
    public Result collegeAdd(@RequestBody CollegeDO collegeDO){
      try {
          Integer i = collegeService.collegeAdd(collegeDO.getCollegeName());
          return ResultUtils.success(i);
      }catch (Exception e){
          e.printStackTrace();
          return ResultUtils.fail("添加失败");
      }
    }

    @RequestMapping("/collegeUpdate")
    @ResponseBody
    public Result collegeUpdate(@RequestBody CollegeDO collegeDO){
        try {
            Integer i = collegeService.collegeUpdate(collegeDO.getCollegeName(), collegeDO.getCollegeId());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("更新失败");
        }
    }

    @RequestMapping("/collegeDelete")
    @ResponseBody
    public Result collegeDelete(@RequestBody CollegeDO collegeDO){
        try {
            Integer i = collegeService.collegeDelete(collegeDO.getCollegeId());
            return ResultUtils.success(i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail("删除失败");
        }
    }
}
