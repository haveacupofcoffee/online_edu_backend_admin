package com.codingforfun.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codingforfun.commonutils.ResultCode;
import com.codingforfun.eduservice.entity.EduSubject;
import com.codingforfun.eduservice.entity.excel.ExcelSubjectData;
import com.codingforfun.eduservice.entity.vo.LevelOneSubjectVo;
import com.codingforfun.eduservice.entity.vo.LevelTwoSubjectVo;
import com.codingforfun.eduservice.listener.SubjectExcelListener;
import com.codingforfun.eduservice.mapper.EduSubjectMapper;
import com.codingforfun.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingforfun.servicebase.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Course Subject 服务实现类
 * </p>
 *
 * @author qdl
 * @since 2020-04-03
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //1 get input stream of file
            InputStream inputStream = file.getInputStream();
            //set the mapping entity class with excel, read from the first sheet, automatically
            //close inputstream
            EasyExcel.read(inputStream, ExcelSubjectData.class, new
                    SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultCode.ERROR,"Failed to save subject data, please try later");
        }

    }

    @Override
    public List<LevelOneSubjectVo> nestedList() {
        //final nested list
        List<LevelOneSubjectVo> levelOneSubjectVoList = new ArrayList<>();

        //get all level one subjects
        List<EduSubject> subjects = getAllLevelOneSubjects();
        //get all level two subjects
        List<EduSubject> subSubjects = getAllLevelTwoSubjects();

        //Fill the vo object with needed attributed
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);
            //create level one vo subject
            LevelOneSubjectVo levelOneSubjectVo = new LevelOneSubjectVo();
            BeanUtils.copyProperties(subject, levelOneSubjectVo);
            levelOneSubjectVoList.add(levelOneSubjectVo);

            //create level two vo subject
            List<LevelTwoSubjectVo> levelTwoSubjectVoList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                EduSubject subSubject = subSubjects.get(j);
                if(subject.getId().equals(subSubject.getParentId())){
                    //创建二级类别vo对象
                    LevelTwoSubjectVo levelTwoSubjectVo = new LevelTwoSubjectVo();
                    BeanUtils.copyProperties(subSubject, levelTwoSubjectVo);
                    levelTwoSubjectVoList.add(levelTwoSubjectVo);
                }
            }
            levelOneSubjectVo.setChildren(levelTwoSubjectVoList);
        }
        return levelOneSubjectVoList;
    }

    //get all level one subjects
    private List<EduSubject> getAllLevelOneSubjects() {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);
        return subjects;
    }

    //get all level two subjects
    private List<EduSubject> getAllLevelTwoSubjects() {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);
        return subjects;
    }

}
