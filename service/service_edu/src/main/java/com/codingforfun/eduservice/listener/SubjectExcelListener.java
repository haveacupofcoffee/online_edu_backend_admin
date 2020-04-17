package com.codingforfun.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codingforfun.commonutils.ResultCode;
import com.codingforfun.eduservice.entity.EduSubject;
import com.codingforfun.eduservice.entity.excel.ExcelSubjectData;
import com.codingforfun.eduservice.service.EduSubjectService;
import com.codingforfun.servicebase.exception.CustomException;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {


    //Can't managed by Spring, since not using database
    public EduSubjectService subjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //Read from excel, line by line
    @Override
    public void invoke(ExcelSubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new CustomException(ResultCode.ERROR, "No Data In file!");
        }

        //read line by line, the first data is level one subject name, the second one is leven two subject name
        //check if levelOneSubject name is already in database by query the name in database first
        EduSubject levelOneSubject = this.findLevelOneSubject(subjectService, subjectData.getLevelOneSubjectName());
        // if not in database, create a new level one subject instance
        if (levelOneSubject == null) { //
            levelOneSubject = new EduSubject();
            levelOneSubject.setParentId("0");  // all level one subjects has parent ID with "0"
            levelOneSubject.setTitle(subjectData.getLevelOneSubjectName()); // set level one subject name
            subjectService.save(levelOneSubject);
        }

        //if level one subject is already in the database, get it's id and start processing level two subject \
        String pid = levelOneSubject.getId();
        EduSubject levelTwoSubject =
                this.findLevelTwoSubject(subjectService, subjectData.getLevelTwoSubjectName(), pid);
        if (levelTwoSubject == null) {
            levelTwoSubject = new EduSubject();
            levelTwoSubject.setTitle(subjectData.getLevelTwoSubjectName());
            levelTwoSubject.setParentId(pid);
            subjectService.save(levelTwoSubject);

        }
    }

    //query level one subject from database and return
    private EduSubject findLevelOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject levelOne = subjectService.getOne(wrapper);

        return levelOne;
    }



    //query level two subject from database and return
    private EduSubject findLevelTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject levelTwo = subjectService.getOne(wrapper);

        return levelTwo;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
