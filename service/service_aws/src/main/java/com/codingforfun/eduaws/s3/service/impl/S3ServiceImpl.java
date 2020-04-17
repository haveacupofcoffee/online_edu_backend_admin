package com.codingforfun.eduaws.s3.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.codingforfun.commonutils.ResultCode;
import com.codingforfun.eduaws.s3.utils.S3ConstantUtil;
import com.codingforfun.eduaws.s3.service.S3Service;
import com.codingforfun.servicebase.exception.CustomException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.UUID;

@Service
public class S3ServiceImpl implements S3Service {

    private AmazonS3 s3;

    @Override
    public String uploadObject(String prefix, MultipartFile file) {
        String serviceName = S3ConstantUtil.SERVICE_NAME;
        String region = S3ConstantUtil.REGION;
        String endPoint = S3ConstantUtil.END_POINT;
        String bucketName = S3ConstantUtil.BUCKET_NAME;

    /*    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(region).build();

        //check if bucket exits
        if (!s3.doesBucketExistV2(bucketName)) {
            s3.createBucket(bucketName);
        }*/
        if(!createBucket(region, bucketName)) {
            throw new CustomException(ResultCode.ERROR, "Upload File Failed!");
        }
        String objectKey = createObjectOkey(prefix, file);
        try {
            //upload object
            s3.putObject(bucketName, objectKey, file.getInputStream(), new ObjectMetadata());
            setObejctPermission(bucketName,objectKey);
        } catch (AmazonServiceException e) {
            //TODO handle exception here
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO handle exception
        }

        return s3.getUrl(bucketName, objectKey).toString();

    }

    private boolean createBucket(String region, String bucketName) {
        //create a s3 service client
        s3 = AmazonS3ClientBuilder.standard().withRegion(region).build();

        //check if bucket exits
        if (!s3.doesBucketExistV2(bucketName)) {
            s3.createBucket(bucketName);
        }

        return true;

    }

    private String createObjectOkey(MultipartFile file) {
        //get object original name
        String fileName = file.getOriginalFilename();
        //generate a random unique string as a part of the name of the uploaded object
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //organize uploaded object by date
        String datePath = new DateTime().toString("yyyy/MM/dd");
        //final upload object key
        String objectKey = datePath + "/" + uuid + fileName;
        return objectKey;
    }

    private String createObjectOkey(String prefix, MultipartFile file) {
        if (StringUtils.isEmpty(prefix)) {
            return createObjectOkey(file);
        } else {

            return prefix + "/" + createObjectOkey(file);
        }
    }

    private void setObejctPermission(String bucketName,String objectKey) {
        // get the current ACL
        AccessControlList acl = s3.getObjectAcl(bucketName, objectKey);
        // set access for the grantee, allow all users to read
        Grantee grantee = GroupGrantee.AllUsers;
        Permission permission = Permission.Read;
        acl.grantPermission(grantee, permission);
        //set object permission
        s3.setObjectAcl(bucketName, objectKey, acl);
    }


}
