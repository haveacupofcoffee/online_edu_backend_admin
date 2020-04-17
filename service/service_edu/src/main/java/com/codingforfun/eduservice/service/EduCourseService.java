package com.codingforfun.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codingforfun.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codingforfun.eduservice.entity.vo.CourseInfoVo;
import com.codingforfun.eduservice.entity.vo.CoursePublishVo;
import com.codingforfun.eduservice.entity.vo.CourseQuery;

/**
 * <p>
 * Course service
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     *
     * @param courseInfoVo
     * @return courseId, after the course is saved
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * get course basic info
     * @param id courese ID
     * @return course Info vo object
     */
    CourseInfoVo getCourseInfoFormById(String id);

    void updateCourseById(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

}
