package com.project.modac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponseDto {


    private String uuidFilename;  // 저장된 파일 이름 (UUID)
    private String originalFilename;  // 사용자가 업로드한 원본 이름

}
