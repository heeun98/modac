package com.project.modac.dto;

import com.project.modac.domain.Comment;
import com.project.modac.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long commentId;

    private Long postId;

    private String username;

    private String content;

    private LocalDateTime createdAt;


}
