package com.project.modac.dto;

import com.project.modac.domain.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String category;
    private String title;
    private String username;
    private LocalDateTime date;
    private int likes;

    @QueryProjection
    public PostResponseDto(Long postId, Category category, String title, String username, LocalDateTime date, int likes) {
        this.postId = postId;
        this.category = category.getDisplayName();
        this.title = title;
        this.username = username;
        this.date = date;
        this.likes = likes;
    }
}
