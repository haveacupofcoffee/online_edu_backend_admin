package com.codingforfun.eduservice.mapper;

import com.codingforfun.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codingforfun.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * Course Table Mapper
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getCoursePublishVoById(String id);
}
