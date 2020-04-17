package com.codingforfun.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codingforfun.commonutils.ResponseResult;
import com.codingforfun.eduservice.entity.EduCourse;
import com.codingforfun.eduservice.entity.vo.CourseInfoVo;
import com.codingforfun.eduservice.entity.vo.CoursePublishVo;
import com.codingforfun.eduservice.entity.vo.CourseQuery;
import com.codingforfun.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Course Controller
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    /**
     * save course, CourseInfoVo is mapping with the form when add new course
     *
     * @param courseInfoVo
     * @return
     */
    @ApiOperation(value = "save Course")
    @PostMapping
    public ResponseResult saveCourseInfo(
            @ApiParam(name = "CourseInfoVo", value = "Course Basic Information, mapping with the form when add new course", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        String courseId = courseService.saveCourseInfo(courseInfoVo);
        if (!StringUtils.isEmpty(courseId)) {
            return ResponseResult.success().message("Save Course Complete!").data("courseId", courseId);
        } else {
            return ResponseResult.error().message("Save Course Failed!");
        }
    }


    /**
     * Get course info by ID
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Get Course Basic Info By ID")
    @GetMapping("{id}")
    public ResponseResult getCourseById(
            @ApiParam(name = "id", value = "Course ID", required = true)
            @PathVariable String id) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfoFormById(id);
        return ResponseResult.success().data("courseInfo", courseInfoVo);

    }

    @ApiOperation(value = "Update Course")
    @PutMapping
    public ResponseResult updateCourseById(
            @ApiParam(name = "CourseInfoVo", value = "Course Basic Information", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseById(courseInfoVo);
        return ResponseResult.success();
    }

    @ApiOperation(value = "Get Course Publish Information By ID")
    @GetMapping("course-publish-info/{id}")
    public ResponseResult getCoursePublishVoById(
            @ApiParam(name = "id", value = "Course ID", required = true)
            @PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        if(coursePublishVo == null) {
            return ResponseResult.error().message("Get Course Information failed").data("item", coursePublishVo);
        }else {
            return ResponseResult.success().data("item", coursePublishVo);
        }

    }

    @ApiOperation(value = "Publish Course By ID")
    @PutMapping("publish-course/{id}")
    public ResponseResult publishCourseById(
            @ApiParam(name = "id", value = "Course ID", required = true)
            @PathVariable String id){
        boolean result = courseService.publishCourseById(id);
        if(result) {
            return ResponseResult.success().message("Course Published");
        }else {
            return ResponseResult.error().message("Course Publish Failed");
        }

    }

    @ApiOperation(value = "Course List")
    @GetMapping("{page}/{limit}")
    public ResponseResult getCoursesByPageAndQuery(
            @ApiParam(name = "page", value = "current page number", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "page size", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "query condition", required = false)
                    CourseQuery courseQuery) {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return ResponseResult.success().data("total", total).data("rows", records);
    }
}

