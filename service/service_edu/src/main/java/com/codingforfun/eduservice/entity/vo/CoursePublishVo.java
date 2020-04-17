package com.codingforfun.eduservice.entity.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "Course Publish Information VO object")
@Data
public class CoursePublishVo {
    private static final long serialVersionUID = 1L;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String levelOneSubject;
    private String levelTwoSubject;
    private String lecturerName;
    private String price;//Only to show
}
