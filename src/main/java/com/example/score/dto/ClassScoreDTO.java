package com.example.score.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClassScoreDTO {
    private String teName;
    private BigDecimal teScore;
    private String clName;
}
