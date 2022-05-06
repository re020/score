package com.example.score.service;

import com.example.score.entity.TeScore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对教师初始评分进行赋分
 */
@Service
public interface EnDoScoreService {

    /**
     * 赋分算法
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    List<TeScore> enDoScore(int classId, String acaYear, int seme);


}
