package com.project.modac.dto;

import com.project.modac.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowCommentForPostDto {


    private String username;

    private String content;

    private LocalDateTime createdDate;


    public ShowCommentForPostDto(Comment comment, String username) {
        this.username = username;
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
    }

}
