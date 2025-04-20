package com.project.modac.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseDto {

    private Long commentId;

    private Long postId;

    private String username;

    private String content;

    private LocalDateTime createdAt;
}
