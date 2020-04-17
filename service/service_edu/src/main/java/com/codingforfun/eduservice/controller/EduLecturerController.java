package com.codingforfun.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codingforfun.commonutils.ResponseResult;
import com.codingforfun.eduservice.entity.EduLecturer;
import com.codingforfun.eduservice.entity.vo.EduLecturerQuery;
import com.codingforfun.eduservice.service.EduLecturerService;
import com.codingforfun.servicebase.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.swing.StringUIClientPropertyKey;

import java.util.List;

/**
 * <p>
 * Lecturer controller
 * </p>
 *
 * @author qdl
 * @since 2020-03-27
 */

@Api(value = "Lecturer Management")
@RestController
@RequestMapping("/eduservice/lecturer")
@CrossOrigin
public class EduLecturerController {

    @Autowired
    private EduLecturerService eduLecturerService;

    @ApiOperation(value = "Add Lecturer")
    @PostMapping
    public ResponseResult saveLecturer(
            @ApiParam(name="EduLecturer", value = "Lecturer Info", required = true)
            @RequestBody EduLecturer eduLecturer) {
        boolean save = eduLecturerService.save(eduLecturer);
        if(save) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value ="Get All Lecturers")
    @GetMapping
    public ResponseResult findAllLecturers() {
        List<EduLecturer> eduLecturers = eduLecturerService.list(null);
        return ResponseResult.success().data("lecturers", eduLecturers);
    }

    @ApiOperation(value = "Get Lecturer By ID")
    @GetMapping("{id}")
    public ResponseResult findLecturerById(
            @ApiParam(name="id", value = "Lecture ID", required = true)
            @PathVariable String id) {
        EduLecturer lecturer = eduLecturerService.getById(id);
        if(lecturer != null ) {
            return ResponseResult.success().data("lecturer", lecturer);
        }else {
            return ResponseResult.error().message("No lecturer found");
        }
    }


    @ApiOperation(value = "Get Lecturers by page")
    @GetMapping("page/{pageId}/{pageSize}")
    public ResponseResult findLecturerByPage(
            @ApiParam(name="pageId", value="Current Page Number", required = true)
            @PathVariable long pageId,
            @ApiParam(name ="pageSize", value="Lecturers Records Number Per Page ", required = true)
            @PathVariable long pageSize) {

        //create page object
        Page<EduLecturer> pageLecturers = new Page<>(pageId, pageSize);
        //
        eduLecturerService.page(pageLecturers,null);
        long total = pageLecturers.getTotal();
        List<EduLecturer> records = pageLecturers.getRecords();

        return ResponseResult.success().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "Get Lecturers by page and query conditions")
    @PostMapping("page/{pageId}/{pageSize}")
    public ResponseResult findLecturerByPageAndQueryCondition(
            @ApiParam(name="pageId", value="Current Page Number", required = true)
            @PathVariable long pageId,
            @ApiParam(name ="pageSize", value="Lecturers Records Number Per Page ", required = true)
            @PathVariable long pageSize,
            @ApiParam(name="EduLecturerQuery", value = "Lecturers Query Condition", required = false)
            @RequestBody(required = false) EduLecturerQuery eduLecturerQuery) {

        //create page object
        Page<EduLecturer> pageLecturers = new Page<>(pageId, pageSize);

        //create query condition
        QueryWrapper<EduLecturer> queryWrapper = new QueryWrapper<>();

        String name = eduLecturerQuery.getName();
        Integer level = eduLecturerQuery.getLevel();
        String begin = eduLecturerQuery.getBegin();
        String end = eduLecturerQuery.getEnd();

        if(!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if(!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }

        if(!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if(!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        //
        eduLecturerService.page(pageLecturers,queryWrapper);
        long total = pageLecturers.getTotal();
        List<EduLecturer> records = pageLecturers.getRecords();

        return ResponseResult.success().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "Update Lecturer By ID")
    @PutMapping
    public ResponseResult updateLecturerById(
            @ApiParam(name="EduLecturer", value = "Lecturer Info", required = true)
            @RequestBody EduLecturer eduLecturer) {
        boolean update = eduLecturerService.updateById(eduLecturer);
        if(update) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "Delete lecturer by ID")
    @DeleteMapping("{id}")
    public ResponseResult removeLecturerById(
            @ApiParam(name="id", value = "Lecturer ID", required = true)
            @PathVariable String id) {
        boolean removeResult = eduLecturerService.removeById(id);
        if(removeResult) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }


    @GetMapping("test")
    public ResponseResult testException() {
        try {
            int a = 10/0;
        }catch(Exception e) {
            throw new CustomException(20001, "custom exception");
        }

        return ResponseResult.error();
    }

}

