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

    private Category category;

    private Gender gender;

    private SergeryProgress sergeryProgress;

    private int transplantCount;

    private int satisfactionScore;

    private String title;

    //본문
    private String content;

    private List<String> hashtags = new ArrayList<>();

    //파일 업로드
    private List<MultipartFile> imageFiles = new ArrayList<>();

}
