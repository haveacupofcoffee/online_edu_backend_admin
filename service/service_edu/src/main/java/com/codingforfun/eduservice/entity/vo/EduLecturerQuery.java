package com.codingforfun.eduservice.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "Lecturer Query", description = "Lecturer Query condition object")
@Data
public class EduLecturerQuery {

    @ApiModelProperty(value = "Lectuer Name, ")
    private String name;

    @ApiModelProperty(value = "Lecturer Level, 0 is junior, 1 is senior")
    private Integer level;

    @ApiModelProperty(value = "Registration Time", example = "2020-01-01 10:10:10")
    private String begin;//String type, don't need to change type

    @ApiModelProperty(value = "Registration Time", example = "2020-12-01 10:10:10")
    private String end;

}
