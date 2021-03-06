package com.codingforfun.eduservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Course Section with Video Information
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduSection", description="Course Section with Video Information")
public class EduSection implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "Video Original Name")
    private String videoOriginalName;

    @ApiModelProperty(value = "Sort")
    private Integer sort;

    @ApiModelProperty(value = "Times of play")
    private Long playCount;

    @ApiModelProperty(value = "Is Free? 0 is free 1 is not free")
    private Boolean isFree;

    @ApiModelProperty(value = "Video Length")
    private Float videoDuration;

    @ApiModelProperty(value = "Three Values: Empty, Transcoding, Normal; Empty means not upload")
    private String videoStatus;

    @ApiModelProperty(value = "Video Size")
    private Long videoSize;

    @ApiModelProperty(value = "Version")
    private Long version;

    @ApiModelProperty(value = "Create Time")
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "Update Time")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
