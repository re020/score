package com.example.score.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllScoreDTO {
    private String stuNum;
    private String teName;
    private BigDecimal teScore;
    private String acaYear;
    private int seme;
}
