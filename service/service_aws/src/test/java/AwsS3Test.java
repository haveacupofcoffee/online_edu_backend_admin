/*
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.codingforfun.eduaws.s3.utils.S3ConstantUtil;
import org.joda.time.DateTime;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class AwsS3Test {
    @Test
    public void testUplodObject() {
*/
/*        String bucketName = "online-education-aws";
        String fileObjKeyName = "testFile";
        S3Client s3 = S3Client.builder()
                .region(Region.CA_CENTRAL_1)
                .build();

        File file = new File ("D:\\get-pip.py");
        s3.putObject(PutObjectRequest.builder().bucket(bucketName).key(file.getName()).grantRead("read")
                        .build(),
                RequestBody.fromFile(file));*//*

        //create a s3 service client
        File file = new File("D:\\get-pip.py");
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.CA_CENTRAL_1).build();
        String bucketName = "online-education-aws";
        //check if bucket exits

        if (!s3.doesBucketExistV2(bucketName)) {
            s3.createBucket(bucketName);
        }

        //Prepare for file uploading

        //get object original name
        String fileName = file.getName();
        //generate a random unique string as a part of the name of the uploaded object
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //organize uploaded object by date
        String datePath = new DateTime().toString("yyyy/MM/dd");
        //final upload object key
        String fileKey = datePath + uuid + fileName;
        try {
            //upload object
            s3.putObject(bucketName, fileKey, file);
            // get the current ACL
            AccessControlList acl = s3.getObjectAcl(bucketName, fileKey);
            // set access for the grantee, allow all users to read
            Grantee grantee = GroupGrantee.AllUsers;
            Permission permission = Permission.Read;
            acl.grantPermission(grantee, permission);

            //set object permission
            s3.setObjectAcl(bucketName, fileKey, acl);

        } catch (AmazonServiceException e) {
            //TODO handle exception here
        }
        System.out.println(s3.getObject(bucketName, fileKey));
    }

}
*/
