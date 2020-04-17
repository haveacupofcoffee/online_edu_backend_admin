package com.codingforfun.eduservice.service;

import com.codingforfun.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codingforfun.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * Course Chapter 服务类
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
public interface EduChapterService extends IService<EduChapter> {
    List<ChapterVo> nestedChaptersList(String courseId);

    boolean removeChapterById(String id);
}
