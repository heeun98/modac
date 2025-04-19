package com.project.modac.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.project.modac.web.dto.QPostRequestDto is a Querydsl Projection type for PostRequestDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostRequestDto extends ConstructorExpression<PostRequestDto> {

    private static final long serialVersionUID = 1818858501L;

    public QPostRequestDto(com.querydsl.core.types.Expression<Long> postId, com.querydsl.core.types.Expression<com.project.modac.domain.Category> category, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<java.time.LocalDateTime> date) {
        super(PostRequestDto.class, new Class<?>[]{long.class, com.project.modac.domain.Category.class, String.class, String.class, java.time.LocalDateTime.class}, postId, category, title, username, date);
    }

}

