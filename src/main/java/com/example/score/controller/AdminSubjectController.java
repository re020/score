package com.example.score.controller;


import com.example.score.common.Result;
import com.example.score.pojo.DO.SubjectDO;
import com.example.score.service.ClassTeService;
import com.example.score.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adminSubject")
@CrossOrigin
public class AdminSubjectController {

    @Autowired
    private ClassTeService classTeService;


    @RequestMapping("/subjectAdd")
    @ResponseBody
    public Result subjectAdd(@RequestBody SubjectDO subjectDO){
    try {
        Integer i = classTeService.subjectAdd(subjectDO.getSubject(), subjectDO.getAcaYear(), subjectDO.getSeme(), subjectDO.getClassTeId());
        return ResultUtils.success(i);
    }catch (Exception e){
        e.printStackTrace();
        return ResultUtils.fail("添加失败");
    }
    }

}
