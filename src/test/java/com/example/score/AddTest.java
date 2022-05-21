package com.example.score;

import com.example.score.service.ClassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ScoreApplication.class)
public class AddTest {

    @Autowired
    private ClassService classService;


    @Test
    public void test1(){
        classService.classAdd("22计本",1);

    }
    @Test
    public void test2(){
        classService.classDelete(57);
        classService.stuClassDelete(57);
    }

}
