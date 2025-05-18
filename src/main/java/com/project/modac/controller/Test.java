package com.project.modac.controller;

import com.project.modac.apiPayload.ApiResponse;
import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import com.project.modac.domain.Category;
import com.project.modac.domain.Post;
import com.project.modac.domain.User;
import com.project.modac.global.login.dto.CustomUserDetails;
import com.project.modac.repository.PostRepository;
import com.project.modac.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Test(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    @GetMapping("modac/community/post")
    public ApiResponse<Page<PageDto>> post(Pageable pageable) {

        Page<Post> page = postRepository.findAll(pageable);
        Page<PageDto> result = page.map(post -> new PageDto(post));

        return ApiResponse.onSuccess(result);

    }
/*

    @PostConstruct
    public void init() {

        if (postRepository.count() == 0) {

            User user1 = new User("heeun98", 1000);
            User user2 = new User("sanga9801", 2000);
            User user3 = new User("adsfasd", 3000);
            User user4 = new User("bsbsr", 4000);
            User user5 = new User("42g2gdgd", 5000);


            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);

            for (int i = 0; i < 50; i++) {

                if (i < 10) {
                    Post post1 = new Post(i + "모 리얼 후기 입니다~~~ ",
                            i + "모를 심은 후기입니다.~~~~~ ",
                            Category.FREE_TALK);
                    user1.addPost(post1);
                    Post save = postRepository.save(post1);
                } else if (i < 20) {
                    Post post2 = new Post(i + "모 리얼 후기 입니다~~~ ",
                            i + "모를 심은 후기입니다.~~~~~ ",
                            Category.HAIR_MEDICINE);
                    user2.addPost(post2);
                    Post save = postRepository.save(post2);
                } else if (i < 30) {
                    Post post3 = new Post(i + "모 리얼 후기 입니다~~~ ",
                            i + "모를 심은 후기입니다.~~~~~ ",
                            Category.HOSPITAL_QUESTION);
                    user3.addPost(post3);
                    Post save = postRepository.save(post3);
                } else if (i < 40) {
                    Post post4 = new Post(i + "모 리얼 후기 입니다~~~ ",
                            i + "모를 심은 후기입니다.~~~~~ ",
                            Category.ALOPECIA_AREATA);
                    user4.addPost(post4);
                    Post save = postRepository.save(post4);
                } else if (i < 50) {
                    Post post5 = new Post(i + "모 리얼 후기 입니다~~~ ",
                            i + "모를 심은 후기입니다.~~~~~ ",
                            Category.ALOPECIA_AREATA);
                    user5.addPost(post5);
                    Post save = postRepository.save(post5);
                }
            }
        }

    }

*/

    @Data
    static class PostRequestDtoV1 {

        private String title;

        private String content;

        private Category category;
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
