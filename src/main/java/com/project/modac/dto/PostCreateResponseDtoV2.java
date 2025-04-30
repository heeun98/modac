package com.project.modac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateResponseDtoV2 {


    private Long post_id;

    private List<ImageResponseDto> postImages = new ArrayList<>();

}
