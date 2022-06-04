package com.example.score.pojo.DO;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int classId;
    private int classTeId;
    private int teId;
    private String teName;
    private String subject;
    private String acaYear;
    private int seme;
    private String clName;


}
