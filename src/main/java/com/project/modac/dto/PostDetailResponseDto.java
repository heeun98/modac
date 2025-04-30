package com.project.modac.dto;


import com.project.modac.domain.Comment;
import com.project.modac.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailResponseDto {

    /**
     * 게시글 상세보기 응답 DTO
     */

    private String title;

    private String category;

    private String sergeryProgress;

    private int transplantAmount;

    private int satisfactionLevel;

    private String content;

    private List<String> fileNames = new ArrayList<>();

    private int likeCount;

    private List<ShowCommentForPostDto> comments = new ArrayList<>();





    public static PostDetailResponseDto of(Post post, String username) {


        return PostDetailResponseDto.builder()
                .title(post.getTitle())
                .category(post.getCategory().getDisplayName())
                .sergeryProgress(post.getSergeryProgress().getProgress())
                .transplantAmount(post.getTransplantAmount())
                .satisfactionLevel(post.getSatisfactionLevel().getValue())
                .content(post.getContent())
                .fileNames(post.getPostImages().stream()
                        .map( postImage -> postImage.getUuidFilename())
                        .collect(Collectors.toList()))
                .likeCount(post.getLikesCount())
                .comments(post.getComments().stream()
                        .map(comment -> new ShowCommentForPostDto(comment, username))
                        .collect(Collectors.toList()))
                .build();


    }







}
