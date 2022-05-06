package com.example.score.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 所有学生打分表
 */

public class AllScore {

    private int asId;

    //班级id
    private int classId;

    //教师id
    private int teId;

    //学生id
    private int stuId;

    //教师的评分
    private BigDecimal teScore;

    //学年
    private String acaYear;

    //学期
    private int seme;

    public int getAsId() {
        return asId;
    }

    public void setAsId(int asId) {
        this.asId = asId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeId() {
        return teId;
    }

    public void setTeId(int teId) {
        this.teId = teId;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public BigDecimal getTeScore() {
        return teScore;
    }

    public void setTeScore(BigDecimal teScore) {
        this.teScore = teScore;
    }

    public String getAcaYear() {
        return acaYear;
    }

    public void setAcaYear(String acaYear) {
        this.acaYear = acaYear;
    }

    public int getSeme() {
        return seme;
    }

    public void setSeme(int seme) {
        this.seme = seme;
    }

    @Override
    public String toString() {
        return "AllScore{" +
                "asId=" + asId +
                ", classId=" + classId +
                ", teId=" + teId +
                ", stuId=" + stuId +
                ", teScore=" + teScore +
                ", acaYear='" + acaYear + '\'' +
                ", seme=" + seme +
                '}';
    }
}
