package com.example.score.service;

import com.example.score.dao.FinalScoreMapper;
import com.example.score.entity.TeScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalScoreService {

    @Autowired
    private FinalScoreMapper finalScoreMapper;

    /**
     * 通过班级id 学年 学期 获取 学生对教师的所有评分
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<TeScore> findTeScores(int classId,String acaYear,int seme){
        return finalScoreMapper.selectTeScoreByClassId(classId,acaYear,seme);
    }

    /**
     * 将赋分后的教师评分插入最终总评分表
     * @param ts
     * @return
     */
    public Integer insertScore(TeScore ts){
        return finalScoreMapper.insert(ts);
    }

}
