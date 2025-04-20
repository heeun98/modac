package com.project.modac.controller.community;

import com.project.modac.apiPayload.ApiResponse;
import com.project.modac.controller.ResponseDto;
import com.project.modac.dto.*;
import com.project.modac.global.login.dto.CustomUserDetails;
import com.project.modac.repository.PostRepository;
import com.project.modac.service.CommentService;
import com.project.modac.service.PostLikeService;
import com.project.modac.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeService postLikeService;

    @GetMapping("api/community/posts")
    public ApiResponse<Page<PostResponseDto>> communityV1(PostSearchCondition condition, Pageable pageable) {

        Page<PostResponseDto> result = postRepository.searchPage(condition, pageable);

        return ApiResponse.onSuccess(result);

    }


    //세션에서 가져와서 사용가능하다.
    @PostMapping("api/community/posts")
    public ApiResponse<ResponseDto> testEx1(
            @RequestBody PostDetailsRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        String username = userDetails.getUsername();

        Long postId = postService.postDetailService(request, username);

        return ApiResponse.onSuccess(new ResponseDto(postId));
    }

    @PostMapping("api/community/{postId}")
    public ApiResponse<CommentResponseDto> commentRe (
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("postId") Long postId,
            @RequestBody CommentRequestDto requestDto
    ) {
        return ApiResponse.onSuccess(commentService.addComment(userDetails.getUsername(), postId, requestDto));
    }


    @PostMapping("api/community/{postId}/like")
    public ApiResponse<LikeResponseDto> addpostlike(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("postId") Long postId
    ) {
        int likeCount = postLikeService.postLike(postId, userDetails.getUsername());
        return ApiResponse.onSuccess(new LikeResponseDto(likeCount));
    }


}


