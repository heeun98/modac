package com.project.modac.web.controller;

import com.project.modac.apiPayload.ApiResponse;
import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import com.project.modac.domain.Post;
import com.project.modac.domain.User;
import com.project.modac.global.login.dto.CustomUserDetails;
import com.project.modac.repository.PostRepository;
import com.project.modac.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

@RestController
public class Test {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Test(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    //세션에서 가져와서 사용가능하다.
    @PostMapping("modac/post/test")
    public ApiResponse<ResponseDto> testEx1(
            @RequestBody PostRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        Post post = new Post(request.getTitle(), request.getContent());
        String username = userDetails.getUsername();
        User user = userRepository.findOptionByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        post.setUser(user);

        Post save = postRepository.save(post);

        return ApiResponse.onSuccess(new ResponseDto(save.getId()));

    }

    @GetMapping("modac/community/post")
    public ApiResponse<Page<PageDto>> post(Pageable pageable) {

        Page<Post> page = postRepository.findAll(pageable);
        Page<PageDto> result = page.map(post -> new PageDto(post));

        return ApiResponse.onSuccess(result);

    }

    @PostConstruct
    public void init() {

        for (int i = 0; i < 50; i++) {
            Post post = new Post(i + " : 제목 ", i + "본문");
            Post save = postRepository.save(post);
        }
    }


    @Data
    static class PostRequestDto {

        private String title;

        private String content;
    }

    @Data
    static class PageDto {

        private String title;
        private String content;

        public PageDto(Post post) {

            this.title = post.getTitle();
            this.content = post.getContent();
        }
    }

}
