package com.example.score.service;

import com.example.score.dao.MarkMapper;
import com.example.score.dto.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {

    @Autowired
    private MarkMapper markMapper;


    public List<Subject> getSubjects(int classId, String year, int seme) {
        return markMapper.getSubjects(classId, year, seme);
    }
}
