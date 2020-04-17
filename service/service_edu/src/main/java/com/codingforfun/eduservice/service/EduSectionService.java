package com.codingforfun.eduservice.service;

import com.codingforfun.eduservice.entity.EduSection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codingforfun.eduservice.entity.vo.SectionInfoFormVo;

/**
 * <p>
 * Course Section with Video Information 服务类
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
public interface EduSectionService extends IService<EduSection> {

    boolean getCountByChapterId(String id);

    boolean saveSection(SectionInfoFormVo sectionInfoFormVo);

    boolean updateSectionInfoById(SectionInfoFormVo sectionInfoFormVo);

    SectionInfoFormVo getSectionInfoById(String id);

    boolean removeSectionById(String id);
}
