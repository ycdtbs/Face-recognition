package utils;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.VoidResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OssUtil {
    public static String upFileTooss(byte[] imagebyte, String endpoint, String accessKeyId, String accessKeySecret, String bucketname, String filename) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        PutObjectResult putObjectResult = ossClient.putObject(bucketname, filename, new ByteArrayInputStream(imagebyte));

        String s = JSON.toJSONString(putObjectResult);
        return s;
    }

    public static String downloadFile(String endpoint, String accessKeyId, String accessKeySecret, String bucketname, String filename) throws IOException {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        OSSObject ossObject = ossClient.getObject(bucketname, filename);

        System.out.println("开始下载:");
        InputStream objectContent1 = ossObject.getObjectContent();
        byte[] bytes = Base64Util.toByteArray(objectContent1);
        String encode = Base64Util.encode(bytes);

        ossClient.shutdown();
        return encode;
    }

    public static String delFile(String endpoint, String accessKeyId, String accessKeySecret, String bucketname, String filename) throws IOException {

        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            VoidResult voidResult = ossClient.deleteObject(bucketname, filename);
            return JSON.toJSONString(voidResult);
        } catch (Exception e) {
            return null;
        } finally {

            ossClient.shutdown();
        }
    }

    public static InputStream downloadFiletoStream(String endpoint, String accessKeyId, String accessKeySecret, String bucketname, String filename) throws IOException {


        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (filename.indexOf(".jpeg") == -1){
            filename = filename + ".jpeg";
        }
        OSSObject ossObject = ossClient.getObject(bucketname, filename);
        System.out.println("开始下载:" + filename);
        InputStream objectContent1 = ossObject.getObjectContent();
        System.out.println("下载完成:" + filename);

        return objectContent1;

    }
}
