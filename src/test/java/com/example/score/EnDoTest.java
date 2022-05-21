package com.example.score;

import com.example.score.pojo.dto.AllScoreDTO;
import com.example.score.pojo.dto.StudentDTO;
import com.example.score.entity.AllScore;
import com.example.score.service.*;
import com.example.score.pojo.dto.Subject;
import com.example.score.entity.ClassTeacher;
import com.example.score.entity.TeScore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = ScoreApplication.class)
public class EnDoTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private MarkService markService;
    @Autowired
    private EnDoScoreService enDoScoreService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeScoreService teScoreService;
    @Autowired
    private CollegeScoreService collegeScoreService;
    @Autowired
    private EnDoService enDoService;
    @Autowired
    private ClassService classService;

    @Test
    public void test1(){

        List<TeScore> teScores = enDoScoreService.enDoScore(55, "2021-2022", 1);
//        for (TeScore teScore : teScores) {
//            System.out.println(teScore);
//        }

    }

    @Test
    public void test2(){

        List<ClassTeacher> stuById = studentService.findTeById(1,"2021-2022",1);
        for (ClassTeacher classTeacher : stuById) {
            System.out.println(classTeacher);
        }
    }
    @Test
    public void test3(){

        System.out.println(markService);
        List<Subject> subjects = markService.getSubjects(1, "2021-2022", 1);
        for (Subject subject : subjects) {
            System.out.println(subject);
        }
    }

    @Test
    public void test4(){

        List<Subject> teList = teacherService.getTeList(1, "2021-2022", 1);
        for (Subject subject : teList) {
            System.out.println(subject);
        }
    }

    @Test
    public void test5(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStuNum("2009101010");
        studentDTO.setAcaYear("2021-2022");
        studentDTO.setSeme(1);
        Integer classId = studentService.getClassIdByStuId(studentDTO.getStuNum());
        //List<ClassTeacher> teList = studentService.findTeById(classId, studentDTO.getAcaYear(), studentDTO.getSeme());
        //studentService.findTeNameById()
        List<Subject> teList = teacherService.getTeList(classId, studentDTO.getAcaYear(), studentDTO.getSeme());
    }

    @Test
    public void test6(){
        AllScoreDTO allScoreDTO = new AllScoreDTO();
        BigDecimal a = new BigDecimal("98");
        allScoreDTO.setTeScore(a);
        allScoreDTO.setTeName("李四");
        allScoreDTO.setSeme(1);
        allScoreDTO.setAcaYear("2021-2022");
        allScoreDTO.setStuNum("2009101010");
        int teId = teacherService.getTeIdByTeName(allScoreDTO.getTeName());
        AllScore allScore = studentService.getByStuNum(allScoreDTO.getStuNum());
        allScore.setTeScore(allScoreDTO.getTeScore());
        allScore.setAcaYear(allScoreDTO.getAcaYear());
        allScore.setSeme(allScoreDTO.getSeme());
        allScore.setTeId(teId);
        teScoreService.insertAllScore(allScore);
    }

    @Test
    public void test7(){
        collegeScoreService.insertCollegeScores("2021-2022",1);
    }

    @Test
    public void test8(){
        enDoService.insertEnDoScore("2021-2022",1);
    }


}
