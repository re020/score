package com.example.score.pojo.DO;

import lombok.Data;

@Data
public class UserTeDO {
    //教师id
    private int teId;

    //工号
    private String teNum;

    //教师姓名
    private String teName;

    //密码
    private String passWd;

    //学院id
    private String collegeName;

    //教师权限
    private String roleName;
}
