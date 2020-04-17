package com.codingforfun.eduservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.codingforfun.commonutils.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduUserController {

    @PostMapping
    public ResponseResult login() {
        return ResponseResult.success().data("token", "admin");
    }

    @GetMapping("/info")
    public ResponseResult info() {
        return ResponseResult.success().
                data("roles", "[admin]").
                data("name","admin").
                data("avatar","https://online-education-aws.s3.ca-central-1.amazonaws.com/2020/04/03/c4a68ed3113b4ccf87d24ee7bd13735cfile.png");
    }

}
