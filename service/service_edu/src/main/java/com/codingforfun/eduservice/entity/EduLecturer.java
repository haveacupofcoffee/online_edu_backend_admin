package com.codingforfun.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Lecturer
 * </p>
 *
 * @author qdl
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduLecturer对象", description="Lecturer")
public class EduLecturer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Lecturer ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "Lecturer Name")
    private String name;

    @ApiModelProperty(value = "Lecturer introduction")
    private String intro;

    @ApiModelProperty(value = "Lecturer brief summary in one sentence")
    private String career;

    @ApiModelProperty(value = "Lecturer Level, 0 is junior, 1 is senior")
    private Integer level;

    @ApiModelProperty(value = "Lecturer avatar")
    private String avatar;

    @ApiModelProperty(value = "Sort")
    private Integer sort;

    @ApiModelProperty(value = "Logical deletion 1（true）deleted， 0（false）not deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "Row or record creation time ", example = "2020-03-27 21:02:06")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "Row or record modification time", example = "2020-03-27 21:02:06")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
