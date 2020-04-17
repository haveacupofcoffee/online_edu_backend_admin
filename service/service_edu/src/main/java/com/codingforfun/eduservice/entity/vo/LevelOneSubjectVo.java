package com.codingforfun.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LevelOneSubjectVo {

    private String id;
    private String title;
    private List<LevelTwoSubjectVo> children = new ArrayList<>();

}
