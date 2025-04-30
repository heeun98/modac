package com.project.modac.controller.community;

import com.project.modac.apiPayload.ApiResponse;
import com.project.modac.controller.ResponseDto;

import com.project.modac.dto.*;
import com.project.modac.file.FileStore;
import com.project.modac.global.login.dto.CustomUserDetails;
import com.project.modac.repository.PostRepository;
import com.project.modac.service.CommentService;
import com.project.modac.service.PostLikeService;
import com.project.modac.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeService postLikeService;
    private final FileStore fileStore;



    //게시글 목록조회
    @GetMapping("api/community/posts")
    public ApiResponse<Page<PostResponseDto>> communityV1(PostSearchCondition condition, Pageable pageable) {

        Page<PostResponseDto> result = postRepository.searchPage2(condition, pageable);

        return ApiResponse.onSuccess(result);

    }


    //게시글 상세보기
    @GetMapping("api/community/posts/{postId}")
    public ApiResponse<PostDetailResponseDto> postDetail(
            @PathVariable("postId") Long postId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        String username = userDetails.getUsername();
        PostDetailResponseDto postDetailResponseDto = postService.postDetailService(postId, username);

        return ApiResponse.onSuccess(postDetailResponseDto);

    }




    @PostMapping("api/v1/community/posts")
    public ApiResponse<ResponseDto> testEx1(
            @RequestBody PostCreateRequestDtoV1 request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        String username = userDetails.getUsername();

        Long postId = postService.postDetailService(request, username);

        return ApiResponse.onSuccess(new ResponseDto(postId));
    }

    //게시글 작성
    @PostMapping(value = "api/v2/community/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<PostCreateResponseDtoV2> testEx2(
            @ModelAttribute PostCreateRequestDtoV2 request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws IOException {

        String username = userDetails.getUsername();

        PostCreateResponseDtoV2 responseDtoV2 = postService.postDetailServiceV2(request, username);


        return ApiResponse.onSuccess(responseDtoV2);

    }


    //게시글 댓글
    @PostMapping("api/community/{postId}/comment")
    public ApiResponse<CommentResponseDto> commentRe (
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("postId") Long postId,
            @RequestBody CommentRequestDto requestDto
    ) {
        return ApiResponse.onSuccess(commentService.addComment(userDetails.getUsername(), postId, requestDto));
    }


    //게시글 좋아요
    @PostMapping("api/community/{postId}/like")
    public ApiResponse<LikeResponseDto> addpostlike(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("postId") Long postId
    ) {
        int likeCount = postLikeService.postLike(postId, userDetails.getUsername());
        return ApiResponse.onSuccess(new LikeResponseDto(likeCount));
    }


}


