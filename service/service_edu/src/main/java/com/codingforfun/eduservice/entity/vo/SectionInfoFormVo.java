package com.codingforfun.eduservice.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Section Basic Info", description = "Section Basic information VO object" +
        "mapping with the form of editing section")
@Data
public class SectionInfoFormVo {
    @ApiModelProperty(value = "Section ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "Course ID")
    private String courseId;

    @ApiModelProperty(value = "Chapter ID")
    private String chapterId;

    @ApiModelProperty(value = "Section Title")
    private String title;

    @ApiModelProperty(value = "Video Cloud Source ID")
    private String videoSourceId;

    @ApiModelProperty(value = "Sort")
    private Integer sort;

    @ApiModelProperty(value = "Is Free? 0 is free 1 is not free")
    private Boolean isFree;
}
