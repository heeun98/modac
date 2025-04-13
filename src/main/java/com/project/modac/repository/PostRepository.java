package com.project.modac.repository;

import com.project.modac.domain.Post;
import com.project.modac.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {



}
