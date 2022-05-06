package com.example.score.service;

import org.springframework.stereotype.Service;

@Service
public interface EnDoService {

    /**
     * 将赋分后的评分插入final_te_score
     */
    void insertEnDoScore();

    /**
     * 将所有学生对教师的平均分插入te_score
     */
    void insetTeScore(String acaYear,int seme);
}
