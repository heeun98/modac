package com.project.modac.repository;

import com.project.modac.domain.*;


import com.project.modac.dto.PostResponseDto;
import com.project.modac.dto.PostSearchCondition;
import com.project.modac.dto.QPostResponseDto;
import com.project.modac.repository.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.project.modac.domain.QComment.comment;
import static com.project.modac.domain.QHashTag.*;
import static com.project.modac.domain.QPost.*;
import static com.project.modac.domain.QPost.post;
import static com.project.modac.domain.QPostHashTag.*;
import static com.project.modac.domain.QPostImage.*;
import static com.project.modac.domain.QUser.user;


public class PostRepositoryImpl extends Querydsl4RepositorySupport implements PostRepositoryCustom{


    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Post.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public Page<PostResponseDto> searchPage2(PostSearchCondition condition, Pageable pageable) {

        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .select(
                                new QPostResponseDto(
                                        post.id,
                                        post.category,
                                        post.title,
                                        user.username,
                                        post.createdDate,
                                        post.likesCount
                                )
                        )
                        .from(post)
                        .join(post.user, user)
                        .where(
                                categoryEq(condition.getCategory()),
                                genderEq(condition.getGender()),
                                countGoe(condition.getGoe()),
                                countLeo(condition.getLoe())
                        ), countQuery -> countQuery
                        .selectFrom(post)
                        .join(post.user, user)
                        .where(
                                categoryEq(condition.getCategory()),
                                genderEq(condition.getGender()),
                                countGoe(condition.getGoe()),
                                countLeo(condition.getLoe())
                        )
        );


    }







    @Override
    public Page<PostResponseDto> searchPage(PostSearchCondition condition, Pageable pageable) {

        List<PostResponseDto> content = queryFactory
                .select(new QPostResponseDto(
                                post.id,
                                post.category,
                                post.title,
                                user.username,
                                post.createdDate,
                                post.postLikes.size()
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
        return gender == null ? null : post.gender.eq(gender);
    }

    private BooleanExpression categoryEq(Category category) {
        return (category == null) ? null : post.category.eq(category);
    }



    //게시글 상세보기 쿼리
    @Override
    public Post getPostDetail(Long postId) {

        return queryFactory.
                select(post)
                .from(post)
                .leftJoin(post.hashTags, postHashTag).fetchJoin()
                .leftJoin(postHashTag.hashTag, hashTag).fetchJoin()
                .where(post.id.eq(postId))
                .fetchOne();

    }





}
