package com.project.modac.service;

import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import com.project.modac.domain.Post;
import com.project.modac.domain.PostLikes;
import com.project.modac.domain.User;
import com.project.modac.repository.PostLikeRepository;
import com.project.modac.repository.PostRepository;
import com.project.modac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostLikeService {

    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;


    @Transactional
    public int postLike(Long postId, String username) {

        User userId = userRepository.findByUsername(username);

        postLikeRepository.findByUser_IdAndPost_Id(userId.getId(), postId)
                .ifPresent(like -> {
                    throw new GeneralException(ErrorStatus.EXIST_LIKES);
                });

        PostLikes postLikes = new PostLikes(userId);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NO_EXISTS));

        post.addPostLikes(postLikes);
        postRepository.save(post);

        return post.postlikesCount();
    }
}
