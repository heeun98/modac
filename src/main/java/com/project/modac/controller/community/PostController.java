package com.project.modac.controller.community;

import com.project.modac.apiPayload.ApiResponse;
import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import com.project.modac.controller.ResponseDto;
import com.project.modac.controller.Test;
import com.project.modac.domain.*;
import com.project.modac.dto.PostDetailsRequestDto;
import com.project.modac.dto.PostResponseDto;
import com.project.modac.dto.PostSearchCondition;
import com.project.modac.global.login.dto.CustomUserDetails;
import com.project.modac.repository.PostRepository;
import com.project.modac.repository.UserRepository;
import com.project.modac.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

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


}


