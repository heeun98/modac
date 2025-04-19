package com.project.modac.repository;


import com.project.modac.dto.PostResponseDto;
import com.project.modac.dto.PostSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<PostResponseDto> searchPage(PostSearchCondition condition, Pageable page);
}
