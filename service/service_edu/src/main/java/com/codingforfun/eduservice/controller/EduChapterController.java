package com.codingforfun.eduservice.controller;


import com.codingforfun.commonutils.ResponseResult;
import com.codingforfun.eduservice.entity.EduChapter;
import com.codingforfun.eduservice.entity.vo.ChapterVo;
import com.codingforfun.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * <p>
 * Course Chapter Controller
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
@Api("Chapter Management")
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "Get nested chapter list")
    @GetMapping("nestedlist/{courseId}")
    public ResponseResult nestedChaptersListByCourseId(
            @ApiParam(name = "courseId", value = "Course ID", required = true)
            @PathVariable String courseId){
        List<ChapterVo> chapterVoList = chapterService.nestedChaptersList(courseId);
        return ResponseResult.success().data("items", chapterVoList);
    }


    @ApiOperation(value = "Save Chapter")
    @PostMapping
    public ResponseResult saveChapter(
            @ApiParam(name = "chapterVo", value = "Chapter Object", required = true)
            @RequestBody EduChapter chapter){
        chapterService.save(chapter);
        return ResponseResult.success().message("Chapter Saved");
    }

    @ApiOperation(value = "Get chapter by ID")
    @GetMapping("{id}")
    public ResponseResult getChapterById(
            @ApiParam(name = "id", value = "Chapter ID", required = true)
            @PathVariable String id){
        EduChapter chapter = chapterService.getById(id);
        return ResponseResult.success().data("item", chapter);
    }

    @ApiOperation(value = "Update Chapter By ID")
    @PutMapping("{id}")
    public ResponseResult updateChapterById(
            @ApiParam(name = "id", value = "Chapter ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "chapter", value = "Chapter object", required = true)
            @RequestBody EduChapter chapter) {
        chapter.setId(id);
        chapterService.updateById(chapter);
        return ResponseResult.success().message("Chapter updated");
    }

    @ApiOperation(value = "Remove Chapter by ID")
    @DeleteMapping("{id}")
    public ResponseResult removeChapterById(
            @ApiParam(name = "id", value = "Chapter ID", required = true)
            @PathVariable String id){
        boolean result = chapterService.removeChapterById(id);
        if(result){
            return ResponseResult.success().message("Chapter deleted");
        }else{
            return ResponseResult.error().message("Chapter delete failed");
        }
    }
}

