package com.project.modac.dto;


import com.project.modac.domain.Category;
import com.project.modac.domain.Gender;
import com.project.modac.domain.SergeryProgress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailsRequestDto {

    private Category category;

    private Gender gender;

    private SergeryProgress sergeryProgress;

    private int transplantCount;

    private int satisfactionScore;

    private String title;

    //본문
    private String content;

    private List<String> hashtags = new ArrayList<>();

}
