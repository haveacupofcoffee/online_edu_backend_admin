package com.codingforfun.eduaws.s3.config;

import com.codingforfun.eduaws.s3.service.S3Service;
import com.codingforfun.eduaws.s3.service.impl.S3ServiceImpl;
import com.codingforfun.eduaws.s3.utils.S3ConstantUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class S3Config  {

    public S3Service getS3ServiceImplInstance() {
        return new S3ServiceImpl();
    }
}
