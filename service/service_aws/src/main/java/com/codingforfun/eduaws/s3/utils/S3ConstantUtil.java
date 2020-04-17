package com.codingforfun.eduaws.s3.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3ConstantUtil implements InitializingBean {

    @Value("${aws.s3.service.name}")
    private String serviceName;

    @Value("${aws.s3.service.region}")
    private String region;

    @Value("${aws.endpoint}")
    private String endPoint;

    @Value("${aws.s3.bucketname}")
    private String bucketName;

    //service name
    public static String SERVICE_NAME;
    //region
    public static String REGION;
    //endpoint
    public static String END_POINT;
    //bucket name
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        SERVICE_NAME = this.serviceName;
        REGION = this.region;
        END_POINT = this.endPoint;
        BUCKET_NAME = this.bucketName;
    }
}
