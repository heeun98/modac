package com.project.modac.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 파일 저장과 관련되 업무 처리 클래스
 */
@Component
public class FileStore {


    //application.yml 에 경로 지정해 놓았음
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }

        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        //사진명.확장자 : 문자열 형태로 반환
        String originalFilename = multipartFile.getOriginalFilename();

        //해당 파일 content-type 을 반환
        String contentType = multipartFile.getContentType();

        //uuid 로 변환한 문자열을 반환
        String storeFileName = createStoreFileName(originalFilename);

        //로컬에 저장해 준다.
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName, contentType);


    }

    private String createStoreFileName(String originalFilename) {
        //서버에 저장하는 파일명
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);//확장자
    }

}
