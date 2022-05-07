package com.example.score.service.impl;

import com.example.score.entity.AllScore;
import com.example.score.entity.ClassTeacher;
import com.example.score.entity.TeScore;
import com.example.score.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnDoserviceImpl implements EnDoService {

    @Autowired
    private EnDoScoreService enDoScoreService;
    @Autowired
    private CollegeScoreService collegeScoreService;
    @Autowired
    private ClassService classService;
    @Autowired
    private FinalScoreService finalScoreService;
    @Autowired
    private ClassTeService classTeService;
    @Autowired
    private TeScoreService teScoreService;
    @Autowired
    private TeacherService teacherService;

    @Override
    public void insertEnDoScore(String acaYear ,int seme ) {
        List<Integer> classList = classService.getClassList();
        for (Integer integer : classList) {
            System.out.println(integer);
        }

      /*  acaYears.add("2019-2020");
        acaYears.add("2020-2021");
        acaYears.add("2020-2022");
        acaYears.add("2018-2019");*/
        for (int classId : classList) {
                    List<TeScore> teScores = enDoScoreService.enDoScore(classId, acaYear, seme);

                    for (TeScore teScore : teScores) {
                        finalScoreService.insertScore(teScore);
            }
            collegeScoreService.insertCollegeScores(acaYear,seme);
        }
    }

    @Override
    public void insetTeScore( String acaYear,int seme) {

        List<ClassTeacher> select = classTeService.select(acaYear,seme);
        for (ClassTeacher classTeacher : select) {

            int teId = classTeacher.getTeId();
            int classId = classTeacher.getClassId();
            /*String acaYear1 = classTeacher.getAcaYear();
            int seme1 = classTeacher.getSeme();*/
            List<AllScore> allScores = teScoreService.selectScore(teId, classId, acaYear, seme);
            BigDecimal sum = new BigDecimal("0");
            BigDecimal i = new BigDecimal("0");
            BigDecimal j = new BigDecimal("1");
            for (AllScore allscore : allScores) {
                sum=sum.add(allscore.getTeScore());
                i = i.add(j);
            }
            if(i.compareTo(new BigDecimal("0"))==1){
                BigDecimal teScore = sum.divide(i, 2);
                int collegeId = teacherService.getTeCollegeId(teId);
                if(sum.compareTo(new BigDecimal("0")) == 1){
                    TeScore ts = new TeScore();

                    //ts.setTsId(allScores.get(0).getAsId());
                    ts.setClassId(classId);
                    ts.setCollegeId(collegeId);
                    ts.setTeId(teId);
                    ts.setTeScore(teScore);
                    ts.setAcaYear(acaYear);
                    ts.setSeme(seme);

                    System.out.println(ts);

                    teScoreService.insertTeScore(ts);
                }

            }


        }

    }
}
