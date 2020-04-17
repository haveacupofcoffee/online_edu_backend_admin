package com.codingforfun.eduservice.controller;


import com.codingforfun.commonutils.ResponseResult;
import com.codingforfun.eduservice.entity.EduSubject;
import com.codingforfun.eduservice.entity.vo.LevelOneSubjectVo;
import com.codingforfun.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * Course Subject Controller
 * </p>
 *
 * @author qdl
 * @since 2020-04-03
 */
@Api("Course Subject Controller")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //save subject
    @ApiOperation(value = "import excel")
    @PostMapping
    public ResponseResult saveSubject(MultipartFile file) {

        subjectService.saveSubject(file, subjectService);
        return ResponseResult.success();
    }

    //find all subjects
    @ApiOperation(value = "GET All Subjects")
    @GetMapping
    public ResponseResult getAllSubjects() {

        List<LevelOneSubjectVo> subjects = subjectService.nestedList();
        return ResponseResult.success().data("subjects", subjects);
    }
}

