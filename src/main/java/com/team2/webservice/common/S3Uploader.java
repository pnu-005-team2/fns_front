package com.team2.webservice.common;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.util.Optional;

/*
  뻘짓 회고 :
    1. The bucket is in this region: null , 이 error에서 상당한 시간을 뺏김
    원인은 버킷을 만들고 버킷인 null인 상태에서 코드 상에서 호출할려고 발생한 error, 해결방법은 버킷에서 초기화를 위한 파일하나를 올려준다.
    영어를 항상 자세히 보고, 에러가 발생 할 수 있는 원인을 코드에서만 찾을려고 하지 말자!
    2. amazonS3Client가 주입이 안되는 error, 첫번 째 원인은 예제 코드는 spring-aws-cloud 라이브러리를 사용하는데, 현재 내 프로젝트에서는 메이븐을
    빌드툴로 사용해서인지, 제대로 import가 되지 않았다. 저 라이브러리를 사용할 경우 config를 건드려줄 필요가 없지만, 나는 amazonaws의 의존성을 주입했기
    때문에 config를 직접 설정해줘야 한다. 해당 설정 파일은 AWSConnfig에서 볼수 있다. 여기서도 1번 에러를 비롯하여,amazonS3Client가 이제는 사용이 권고되지 않는데
    이 부분에서 많은 시행착오를 겪었다.
    3. 접근권한에 대한 문제.... 최고 뻘짓이다 S3에서 업로드 권한이 차단되어있었는데 이걸 놓치고 있었다.....
 */

@Slf4j
@RequiredArgsConstructor // @RequiredArgsConstructor: final 프로퍼티만 생성자로 받는다.
@Component
//@Service
public class S3Uploader {

    private static final Logger logger =LoggerFactory.getLogger(S3Uploader.class);
    private final AmazonS3 amazonS3Client;
    // @Value: 공통으로 사용하는 값을 별도로 관리, 이런 값들을 쉽게 가져다쓰기 위해 사용
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        // orElseThrow(): 주어진 Optional이 null 경우 throw을 던진다.
        File uploadFile = convert(multipartFile).get();
//                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    //------------ S3서버에 파일을 올리고 url을 리턴합니다. --------------------
    private String putS3(File uploadFile, String fileName){
        logger.info(amazonS3Client.getRegion().toString());
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead)); // public 읽기 권한으로 put, 외부에서 읽을 수 있음
        return amazonS3Client.getUrl(bucket,fileName).toString();
    }

    //------------ 로컬(서버)에 생성된 File 삭제 --------------------
    private void removeNewFile(File targetFile) throws NoSuchFileException, DirectoryNotEmptyException,IOException{
        targetFile.delete();
        /*if(targetFile.delete()){
            log.info("파일이 삭제되었습니다.");
        } else{
            log.info("파일이 삭제되지 못했습니다.");
        }*/
    }

    // S3에는 MultipartFile 타입은 전송이 안됨, 따라서 변환 필요
    //------------ MultipartFile을 File 객체로 변환합니다.--------------------
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        logger.info(convertFile.toString());
        if(convertFile.createNewFile()){ // createNewFile(): 지정된 경로로 빈 파일 생성, 같은 이름이 존재하면 실패
            FileOutputStream fos = new FileOutputStream(convertFile);
            try{
                fos.write(file.getBytes()); // 파일 내용을 저장
            }catch(Exception e){

                Writer writer= new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                String s = writer.toString();
                logger.info(s);
            }finally {
                fos.close();
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

}
