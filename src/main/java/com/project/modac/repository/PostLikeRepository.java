package com.project.modac.repository;

import com.project.modac.domain.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLikes, Long> {

    Boolean existsByUser_IdAndPost_Id(Long userId, Long postId);

    Optional<PostLikes> findByUser_IdAndPost_Id(Long userId, Long postId);
}
