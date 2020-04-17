package com.codingforfun.eduaws.s3.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String uploadObject(String prefix, MultipartFile file);
}
