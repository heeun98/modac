package com.project.modac.service;

import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import com.project.modac.domain.Comment;
import com.project.modac.domain.Post;
import com.project.modac.domain.User;
import com.project.modac.dto.CommentRequestDto;
import com.project.modac.dto.CommentResponseDto;
import com.project.modac.repository.CommentRepository;
import com.project.modac.repository.PostRepository;
import com.project.modac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDto addComment(String username, Long postId, CommentRequestDto requestDto) {

        Comment comment = new Comment(requestDto.getContent());

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.INVALID_REQUEST));

        post.addComment(comment);

        Comment savedComment = commentRepository.save(comment);

        User findUser = userRepository.findByUsername(username);
        findUser.addComment(savedComment);


        CommentResponseDto commonResponseDto = CommentResponseDto.builder()
                .commentId(savedComment.getId())
                .postId(postId)
                .username(username)
                .createdAt(savedComment.getCreatedDate())
                .content(savedComment.getContent())
                .build();

        return commonResponseDto;
    }
}
