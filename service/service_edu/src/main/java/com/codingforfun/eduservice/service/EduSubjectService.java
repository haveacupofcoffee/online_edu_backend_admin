package com.codingforfun.eduservice.service;

import com.codingforfun.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codingforfun.eduservice.entity.vo.LevelOneSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * Course Subject 服务类
 * </p>
 *
 * @author qdl
 * @since 2020-04-03
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<LevelOneSubjectVo> nestedList();
}
