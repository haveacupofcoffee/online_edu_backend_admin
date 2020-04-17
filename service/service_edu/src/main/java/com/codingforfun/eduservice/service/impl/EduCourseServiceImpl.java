package com.codingforfun.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codingforfun.commonutils.ResultCode;
import com.codingforfun.eduservice.entity.EduCourse;
import com.codingforfun.eduservice.entity.EduCourseDescription;
import com.codingforfun.eduservice.entity.vo.CourseInfoVo;
import com.codingforfun.eduservice.entity.vo.CoursePublishVo;
import com.codingforfun.eduservice.entity.vo.CourseQuery;
import com.codingforfun.eduservice.mapper.EduCourseMapper;
import com.codingforfun.eduservice.service.EduCourseDescriptionService;
import com.codingforfun.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingforfun.servicebase.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * Course service implementation
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //Change vo object to entity object, mapping with table
        EduCourse course = new EduCourse();
        //course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoVo, course);
        boolean resultCourseInfo = this.save(course);
        if(!resultCourseInfo){
            throw new CustomException(ResultCode.ERROR, "Save Course Failed");
        }
        //Save course description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = courseDescriptionService.save(courseDescription);
        if(!resultDescription){
            throw new CustomException(ResultCode.ERROR, "Save Course Failed");
        }
        return course.getId();
    }

    @Override
    public CourseInfoVo getCourseInfoFormById(String id) {
        EduCourse course = this.getById(id);
        if(course == null){
            throw new CustomException(ResultCode.ERROR, "No Course Found");
        }
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        EduCourseDescription courseDescription = courseDescriptionService.getById(id);
        if(course != null){
            courseInfoVo.setDescription(courseDescription.getDescription());
        }
        return courseInfoVo;
    }

    @Override
    @Transactional
    public void updateCourseById(CourseInfoVo courseInfoVo) {
        //Save course basic information
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, course);
        boolean resultCourseInfo = this.updateById(course);
        if(!resultCourseInfo){
            throw new CustomException(ResultCode.ERROR, "Save Course Failed");
        }
        //save course description information
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription =
                courseDescriptionService.updateById(courseDescription);
        if(!resultDescription){
            throw new CustomException(ResultCode.ERROR, "Save Course Description Failed");
        }
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.getCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus(EduCourse.COURSE_PUBLISHED);
        Integer count = baseMapper.updateById(course);
        return null != count && count > 0;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getLecturerId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("lecturer_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
