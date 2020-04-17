package com.codingforfun.eduservice.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Course Query Conditions Object", description = "Course Query Conditions Object")
@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Course Title")
    private String title;
    @ApiModelProperty(value = "Lecturer ID")
    private String lecturerId;
    @ApiModelProperty(value = "Level One Subject ID")
    private String subjectParentId;
    @ApiModelProperty(value = "Level Two Subject ID")
    private String subjectId;

}
