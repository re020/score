package com.example.score.service.impl;

import com.example.score.entity.CollegeScore;
import com.example.score.entity.FinalScore;
import com.example.score.entity.TeScore;
import com.example.score.service.CollegeScoreService;
import com.example.score.service.EnDoScoreService;
import com.example.score.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class CollegeScoreServiceImpl implements CollegeScoreService {

    @Autowired
    private TeacherService teacherService;
    /**
     * 将教师成绩插入学院成绩表
     */
    @Override
    public void insertCollegeScores() {

        List<Integer> teIds = teacherService.getTeIds();
        List<String> acaYears = new ArrayList<>();
        acaYears.add("2019-2020");
        acaYears.add("2020-2021");
        acaYears.add("2020-2022");
        acaYears.add("2018-2019");

        for (Integer teId : teIds) {
            for (String acaYear : acaYears) {
                for (int seme = 1; seme <=2 ; seme++) {
                    CollegeScore sc = new CollegeScore();
                    List<TeScore> classScores = teacherService.getClassScores(teId, acaYear, seme);
                    BigDecimal sum = new BigDecimal("0");
                    BigDecimal i = new BigDecimal("1");
                    BigDecimal j = new BigDecimal("1");
                    for (TeScore classScore : classScores) {
                        sum=sum.add(classScore.getTeScore());
                        i = i.add(j);
                    }
                    BigDecimal collegeScore = sum.divide(i, 2);

                    sc.setCollegeId(classScores.get(0).getCollegeId());
                    sc.setTeScore(collegeScore);
                    sc.setAcaYear(classScores.get(0).getAcaYear());
                    sc.setSeme(classScores.get(0).getSeme());
                    sc.setCsId(classScores.get(0).getTsId());
                    sc.setTeId(classScores.get(0).getTeId());
                    teacherService.insertCollegeScore(sc);
                }
            }
        }
    }

}
