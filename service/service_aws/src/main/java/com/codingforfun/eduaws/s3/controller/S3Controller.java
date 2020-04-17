package com.codingforfun.eduaws.s3.controller;


import com.codingforfun.commonutils.ResponseResult;
import com.codingforfun.eduaws.s3.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;

@Api("AWS S3 Service Controller")
@CrossOrigin
@RestController
@RequestMapping("/eduaws")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @ApiOperation(value = "Upload Object(User Avatar) To AWS S3")
    @PostMapping("user/avatar")
    public ResponseResult uploadUserAvatar(MultipartFile file) {
        String prefix = "user/avatars";
        String url = s3Service.uploadObject(prefix, file);
        return ResponseResult.success().message("Uplode Completed").data("url", url);
    }

    @ApiOperation(value = "Upload Object(Course Cover) To AWS S3")
    @PostMapping("course/cover")
    public ResponseResult uploadCourseCover(MultipartFile file) {
        String prefix = "course/covers";
        String url = s3Service.uploadObject(prefix, file);

        return ResponseResult.success().message("Uplode Completed").data("url", url);
    }


/*    @ApiOperation(value = "Get Default Course Cover From  AWS S3")
    @GetMapping("course/cover")
    public ResponseResult getDefaultCourseCover() {
        String url = s3Service.uploadObject(prefix, file);
        return ResponseResult.success().message("Uplode Completed").data("url", url);
    }*/






}
