package com.codingforfun.eduservice.entity;

import java.math.BigDecimal;

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
 * Course
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduCourse", description="Course Table")
public class EduCourse implements Serializable {

    public static final String COURSE_PUBLISHED = "Published";
    public static final String COURSE_DRAFT = "Draft";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Course ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
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

    @ApiModelProperty(value = "Sell Count")
    private Long sellCount;

    @ApiModelProperty(value = "View Count")
    private Long viewCount;

    @ApiModelProperty(value = "Version")
    private Long version;

    @ApiModelProperty(value = "Course Status Draft not published, Published means published")
    private String status;

    @ApiModelProperty(value = "Logic Deletion 1（true）deleted， 0（false）not deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "Create Time")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "Update Time")
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
