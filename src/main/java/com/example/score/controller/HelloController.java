package com.example.score.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.score.dto.Subject;
import com.example.score.entity.Student;
import com.example.score.service.MarkService;
import com.example.score.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {


    @Autowired
    private MarkService markService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello world!";
    }

    @RequestMapping("/stu/{id}")
    @ResponseBody
    public String getStu(@PathVariable("id") int id) {
      /*  Student stu = studentService.findStuById(id);
        JSONObject json = new JSONObject();
        json.put("stu", stu);
        return json.toJSONString();*/
        return null;
    }

    @RequestMapping("/sub")
    @ResponseBody
    public String getSubjects() {
        List<Subject> subjects = markService.getSubjects(1, "2021-2022", 1);
        return JSON.toJSONString(subjects);
    }

    /**
     * 学生登录
     */
  /*  @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody StudentDO studentDO) {
        // 使用shiro框架进行认证
        // 获取当前用户对象，状态为“未认证”
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new CustomLoginToken(studentDO.getStuNumber(), Md5Utils.toMD5(studentDO.getStuPassword()), UserType.STUDENT.getCode());
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(ResultEnum.ERROR.getCode(), "用户名或密码错误！");
        }
        // 查询登录成功的数据，放到redis中
        StudentDO student = (StudentDO) subject.getPrincipal();
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("token", "stu" + sessionId);
        dataMap.put("student", student);
        redisTemplate.opsForValue().set(student.getStuNumber(), sessionId);
        return Result.ok("登陆成功！", dataMap);
    }*/
}
