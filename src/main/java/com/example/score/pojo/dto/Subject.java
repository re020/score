package com.example.score.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    private int teId;
    private String subject;
    private String teacher;
    private BigDecimal teScore;

}
