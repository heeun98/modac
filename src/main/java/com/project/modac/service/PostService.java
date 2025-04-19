package com.project.modac.service;

import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import com.project.modac.domain.*;
import com.project.modac.dto.PostDetailsRequestDto;
import com.project.modac.repository.HashtagRepository;
import com.project.modac.repository.PostRepository;
import com.project.modac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostService {

    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Transactional
    public Long postDetailService(PostDetailsRequestDto request, String username) {

        User user = userRepository.findOptionByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Post newPost = getPost(request);

        for (String hashtag : request.getHashtags()) {

            HashTag hashTag = hashtagRepository.findByName(hashtag)
                    .orElseGet(() -> {
                        HashTag newhashTag = HashTag.builder()
                                .name(hashtag)
                                .build();
                        return hashtagRepository.save(newhashTag);
                    });

            PostHashTag postHashTage = PostHashTag.createPostHashTage(hashTag);
            newPost.addPostHashtag(postHashTage);
        }
        postRepository.save(newPost);

        user.addPost(newPost);

        Long postid = userRepository.save(user).getId();

        return postid;
    }


    private static Post getPost(PostDetailsRequestDto request) {
        //기본적인 Post 생성 , 연관관계는 설정안함
        Post newPost = Post.createPost(
                request.getCategory(),
                request.getGender(),
                request.getSergeryProgress(),
                request.getTransplantCount(),
                SatisfactionLevel.fromValue(request.getSatisfactionScore()),
                request.getTitle(),
                request.getContent()
        );
        return newPost;
    }
}
