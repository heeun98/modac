package com.project.modac.dto;


import com.project.modac.domain.Category;
import com.project.modac.domain.Gender;
import com.project.modac.domain.SergeryProgress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequestDtoV2 {

    /**
     * 요청에서 name="category"로 파라미터가 들어옵니다.
     * Spring은 이 값을 자동으로 PostCreateRequestDtoV2의 category 필드로 매핑하려고 시도합니다.
     * category 필드는 Category라는 enum 타입이므로, Spring은 Category.valueOf("받은값") 방식으로 변환을 시도합니다.
     * 만약 category가 Category라는 enum이라면, 요청에서 "category=CATEGORY_1"와 같은 값이 오면 자동으로 Category.CATEGORY_1로 매핑됩니다.
     */

    private Category category;

    private Gender gender;

    private SergeryProgress surgeryProgress;

    private int transplantCount;

    private int satisfactionScore;

    private String title;

    //본문
    private String content;

    private List<String> hashtags = new ArrayList<>();

    //파일 업로드
    private List<MultipartFile> imageFiles = new ArrayList<>();

}
