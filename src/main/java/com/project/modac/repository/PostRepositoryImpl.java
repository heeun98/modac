package com.project.modac.repository;

import com.project.modac.domain.Category;
import com.project.modac.domain.Gender;


import com.project.modac.domain.Post;

import com.project.modac.dto.PostResponseDto;
import com.project.modac.dto.PostSearchCondition;
import com.project.modac.dto.QPostResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.project.modac.domain.QPost.post;
import static com.project.modac.domain.QUser.user;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{


    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostResponseDto> searchPage(PostSearchCondition condition, Pageable pageable) {

        List<PostResponseDto> content = queryFactory
                .select(new QPostResponseDto(
                                post.id,
                                post.category,
                                post.title,
                                user.username,
                                post.createdDate
                        )
                )
                .from(post)
                .leftJoin(post.user, user)
                .where(
                        categoryEq(condition.getCategory()),
                        genderEq(condition.getGender()),
                        countGoe(condition.getGoe()),
                        countLeo(condition.getLoe())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Post> countQuery = queryFactory
                .selectFrom(post)
                .join(post.user, user)
                .where(
                        categoryEq(condition.getCategory()),
                        genderEq(condition.getGender()),
                        countGoe(condition.getGoe()),
                        countLeo(condition.getLoe())
                );

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());

    }

    private BooleanExpression countLeo(Integer loe) {
        return (loe == null) ? null : post.transplantAmount.loe(loe);
    }

    private BooleanExpression countGoe(Integer goe) {
        return (goe == null) ? null : post.transplantAmount.goe(goe);
    }

    private BooleanExpression genderEq(Gender gender) {
        return gender == null ? null : user.gender.eq(gender);
    }

    private BooleanExpression categoryEq(Category category) {
        return (category == null) ? null : post.category.eq(category);
    }
}
