package com.codingforfun.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codingforfun.commonutils.ResultCode;
import com.codingforfun.eduservice.entity.EduSection;
import com.codingforfun.eduservice.entity.vo.SectionInfoFormVo;
import com.codingforfun.eduservice.mapper.EduSectionMapper;
import com.codingforfun.eduservice.service.EduSectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingforfun.servicebase.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Course Section with Video Information service implementation class
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
@Service
public class EduSectionServiceImpl extends ServiceImpl<EduSectionMapper, EduSection> implements EduSectionService {

    @Override
    public boolean getCountByChapterId(String id) {
        QueryWrapper<EduSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return null != count && count > 0;
    }

    @Override
    public boolean saveSection(SectionInfoFormVo sectionInfoFormVo) {
        EduSection section = new EduSection();
        BeanUtils.copyProperties(sectionInfoFormVo, section);
        boolean result = this.save(section);
        return result;
    }

    @Override
    public boolean updateSectionInfoById(SectionInfoFormVo sectionInfoFormVo) {
        //update section basic information
        EduSection section = new EduSection();
        BeanUtils.copyProperties(sectionInfoFormVo, section);
        boolean result = this.updateById(section);
        return  result;
    }

    @Override
    public SectionInfoFormVo getSectionInfoById(String id) {
        //从video表中取数据
        EduSection section = this.getById(id);
        if(section == null){
            throw new CustomException(ResultCode.ERROR, "Not Section Found!");
        }
        //创建videoInfoForm对象
        SectionInfoFormVo sectionInfoFormVo = new SectionInfoFormVo();
        BeanUtils.copyProperties(section, sectionInfoFormVo);
        return sectionInfoFormVo;
    }

    @Override
    public boolean removeSectionById(String id) {
        //删除视频资源 TODO
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }
}
