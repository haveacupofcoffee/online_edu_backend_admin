package com.codingforfun.eduservice;


import com.codingforfun.eduservice.entity.EduLecturer;
import com.codingforfun.eduservice.mapper.EduLecturerMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class MyTest {

    @Autowired
    private EduLecturerMapper eduLecturerMapper;

    @Test
    public void addLecturer() {
        EduLecturer eduLecturer;

        for(int i=0; i<20; i++) {
            eduLecturer = new EduLecturer();
            if(i % 2 == 0) {
                eduLecturer.setName("Tom " + i);
                eduLecturer.setCareer("I am a good lecturer " + i);
                eduLecturer.setIntro("I graduated from cat university " + i);
                eduLecturer.setLevel(0);
            }else {
                eduLecturer.setName("Jerry " + i);
                eduLecturer.setCareer("I am a great lecturer " + i);
                eduLecturer.setIntro("I graduated from mouse university " + i);
                eduLecturer.setLevel(1);
            }
            eduLecturer.setGmtCreate(new Date());
            eduLecturer.setGmtModified(new Date());

            int insert = eduLecturerMapper.insert(eduLecturer);
            System.out.println(" insert successfully " + i);

        }


    }

}
