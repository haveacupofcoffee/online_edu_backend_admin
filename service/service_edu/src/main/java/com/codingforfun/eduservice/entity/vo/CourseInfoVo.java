package com.codingforfun.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {
    @ApiModelProperty(value = "Course ID")
    private String id;

    @ApiModelProperty(value = "Course Lecturer ID")
    private String lecturerId;

    @ApiModelProperty(value = "Course Subject ID")
    private String subjectId;

    @ApiModelProperty(value = "Course Subject Parent ID")
    private String subjectParentId;

    @ApiModelProperty(value = "Course Title")
    private String title;

    @ApiModelProperty(value = "Course Price, 0 is free")
    private BigDecimal price;

    @ApiModelProperty(value = "Total Lessons Number")
    private Integer lessonNum;

    @ApiModelProperty(value = "Course Cover Path")
    private String cover;

    @ApiModelProperty(value = "Course Description")
    private String description;


}
