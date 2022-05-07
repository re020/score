package com.example.score.service.impl;


import com.example.score.dao.FinalScoreMapper;
import com.example.score.entity.TeScore;
import com.example.score.service.ClassService;
import com.example.score.service.CollegeScoreService;
import com.example.score.service.EnDoScoreService;
import com.example.score.service.FinalScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 赋分算法
 */
@Service
public class EnDoScoreServiceImpl implements EnDoScoreService {

    @Autowired
    private FinalScoreService finalScoreService;

    /**
     * 将教师成绩排名赋分
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<TeScore> enDoScore(int classId, String acaYear, int seme) {

        //System.out.println(finalScoreMapper);
        List<TeScore> teScores = finalScoreService.findTeScores(classId, acaYear, seme);

        for (TeScore teScore : teScores) {

            System.out.println(teScore);

        }
        teScores.sort(Comparator.comparing(TeScore::getTeScore).reversed());

        //获取原始分区间
        BigDecimal x1 = new BigDecimal("1");
        BigDecimal x2 = new BigDecimal("1");
        System.out.println(teScores.size()+":size");
        System.out.println(teScores.get(0));
        BigDecimal max = teScores.get(0).getTeScore().add(x1);
        BigDecimal min = teScores.get(teScores.size()-1).getTeScore().subtract(x2);
        System.out.println("max:"+max+"min:"+min);

        //设置赋分区间
        BigDecimal fmax = new BigDecimal("100");
        BigDecimal fmin = new BigDecimal("98");

        //基于赋分制的教学质量评价系统
        //给每个教师赋分
        for (int i = 0; i < teScores.size(); i++) {

            BigDecimal score = teScores.get(i).getTeScore();

            //BigDecimal a = fmax*(score-min)+fmin*(max-score);
            BigDecimal a = fmax.multiply(score.subtract(min)).add(fmin.multiply(max.subtract(score)));
            //BigDecimal b = a/(max-min);
            BigDecimal b = a.divide(max.subtract(min),2);

            BigDecimal finalScore = b.setScale(2, RoundingMode.HALF_DOWN);

            teScores.get(i).setTeScore(finalScore);
        }
        for (TeScore teScore : teScores) {

            System.out.println(teScore);
        }

        return  teScores;
    }


}
