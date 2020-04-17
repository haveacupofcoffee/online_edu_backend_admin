package com.codingforfun.eduservice.controller;


import com.codingforfun.commonutils.ResponseResult;
import com.codingforfun.eduservice.entity.vo.SectionInfoFormVo;
import com.codingforfun.eduservice.service.EduSectionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Course Section with Video Information, controller
 * </p>
 *
 * @author qdl
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/eduservice/section")
@CrossOrigin
public class EduSectionController {
    @Autowired
    private EduSectionService sectionService;

    @ApiOperation(value = "Save Course Section")
    @PostMapping
    public ResponseResult saveSection(
            @ApiParam(name = "SectionInfoFormVo", value = "Section Basic Info VO object", required = true)
            @RequestBody SectionInfoFormVo sectionInfoFormVo){
        boolean result = sectionService.saveSection(sectionInfoFormVo);
        if(result) {
            return ResponseResult.success().message("Section Saved");
        }else {
            return ResponseResult.success().message("Save Section Failed");
        }

    }

    @ApiOperation(value = "Update Section")
    @PutMapping("{id}")
    public ResponseResult updateSectionInfoById(
            @ApiParam(name = "SectionInfoFormVo", value = "Section Basic Info VO object", required = true)
            @RequestBody SectionInfoFormVo sectionInfoFormVo,
            @ApiParam(name = "id", value = "Section ID", required = true)
            @PathVariable String id){
        boolean result = sectionService.updateSectionInfoById(sectionInfoFormVo);
        if(result) {
            return ResponseResult.success().message("Section Updated");
        }else {
            return ResponseResult.success().message("Update Section Failed");
        }
}

    @ApiOperation(value = "Get Section Information By ID")
    @GetMapping("{id}")
    public ResponseResult getSectionInfoById(
            @ApiParam(name = "id", value = "Section ID", required = true)
            @PathVariable String id){
        SectionInfoFormVo sectionInfoFormVo = sectionService.getSectionInfoById(id);
        return ResponseResult.success().data("item", sectionInfoFormVo);
    }

    @ApiOperation(value = "Delete Section By ID")
    @DeleteMapping("{id}")
    public ResponseResult removeSectionById(
            @ApiParam(name = "id", value = "Section ID", required = true)
            @PathVariable String id){
        boolean result = sectionService.removeSectionById(id);
        if(result){
            return ResponseResult.success().message("Section Deleted!");
        }else{
            return ResponseResult.error().message("Delete Section Failed");
        }
    }

}

