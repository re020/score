package com.example.score.service;

import com.example.score.dao.TeScoreMapper;
import com.example.score.pojo.dto.ClassScoreDTO;
import com.example.score.entity.AllScore;
import com.example.score.entity.TeScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeScoreService {

    @Autowired
    private TeScoreMapper teScoreMapper;

    /**
     * 将学生对教师的评分插入all_score表
     * @param as
     * @return
     */
    public int insertAllScore(AllScore as){
        return teScoreMapper.insertAllScore(as);
    }

    /**
     * 获取所有学生
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    public List<AllScore> selectScore( int teId, int classId, String acaYear,int seme){
        return teScoreMapper.selectScore(teId,classId,acaYear,seme);
    }

    /**
     *
     * @param teScore
     * @return
     */
    public Integer insertTeScore(TeScore teScore){
        return teScoreMapper.insertTeScore(teScore);
    }

    /**
     *
     * @param teScore
     * @return
     */
    public Integer insertFinalScore(TeScore teScore){
        return teScoreMapper.insertFinalScore(teScore);
    }

    /**
     *
     * @return
     */
    public List<String> getClNames(){
        return teScoreMapper.getClNames();
    }

    /**
     *
     * @param clName
     * @param acaYear
     * @param seme
     * @return
     */
    public List<ClassScoreDTO> getScore(String clName , String acaYear , int seme){
        return teScoreMapper.geScore(clName,acaYear,seme);
    }
}
