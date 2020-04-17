package com.codingforfun.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codingforfun.commonutils.ResultCode;
import com.codingforfun.eduservice.entity.EduChapter;
import com.codingforfun.eduservice.entity.EduSection;
import com.codingforfun.eduservice.entity.vo.ChapterVo;
import com.codingforfun.eduservice.entity.vo.SectionVo;
import com.codingforfun.eduservice.mapper.EduChapterMapper;
import com.codingforfun.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingforfun.eduservice.service.EduSectionService;
import com.codingforfun.servicebase.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Course Chapter
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduSectionService sectionService;

    @Override
    public List<ChapterVo> nestedChaptersList(String courseId) {
        //Final list
        ArrayList<ChapterVo> chapterVoList = new ArrayList<>();
        //Chapter information
        QueryWrapper<EduChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByAsc("sort", "id");
        List<EduChapter> chapters = baseMapper.selectList(queryWrapper1);
        //Section Information
        QueryWrapper<EduSection> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSection> sections = sectionService.list(queryWrapper2);

        int chapterSize = chapters.size();
        for (int i = 0; i < chapterSize; i++) {
            EduChapter chapter = chapters.get(i);

            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoList.add(chapterVo);
            //fill section vo object
            ArrayList<SectionVo> sectionVos = new ArrayList<>();
            int sectionsSize = sections.size();
            for (int j = 0; j < sectionsSize; j++) {
                EduSection section = sections.get(j);
                if(chapter.getId().equals(section.getChapterId())){

                    SectionVo sectionVo = new SectionVo();
                    BeanUtils.copyProperties(section, sectionVo);
                    sectionVos.add(sectionVo);
                }
            }
            chapterVo.setChildren(sectionVos);
        }
        return chapterVoList;
    }

    @Override
    public boolean removeChapterById(String id) {
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        //Check if the chapter has sections, if has, notify user ,can't delete
        if(sectionService.getCountByChapterId(id)){
            throw new CustomException(ResultCode.ERROR,"The chapter has section, can't be deleted");
        }
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }


}
