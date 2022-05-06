package com.example.score.dto;

import java.io.Serializable;

public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subject;
    private String teacher;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
