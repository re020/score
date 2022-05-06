package com.example.score.service;

import org.springframework.stereotype.Service;

@Service
public interface CollegeScoreService {

    /**
     * 处理final_te_score中的教师评分
     */
    public void insertCollegeScores();

}
