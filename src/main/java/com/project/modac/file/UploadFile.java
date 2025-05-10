package com.project.modac.file;

import lombok.Data;

/**
 * 업로드 파일 정보를 보관하는 클래스
 */
@Data
public class UploadFile {

    /**
     * 사용자 두명이 같은 파일을 업로드했을때 디스크에 같은 파일로 하면 파일이 덮이기때문에
     * storeFileName 은 겹치지 않게 uuid 로 구분한다.
     */


    private String uploadFileName;
    private String storeFileName;
    private String contentType;

    public UploadFile(String uploadFileName, String storeFileName, String contentType) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.contentType = contentType;
    }
}
