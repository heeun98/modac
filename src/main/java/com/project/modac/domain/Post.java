package com.project.modac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Post extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public void setUser(User user) {
        this.user = user;
    }

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private SergeryProgress sergeryProgress;

    private int transplantAmount;

    private SatisfactionLevel satisfactionLevel;


    private String title;

    private String content;

    private int likesCount;

    public int addLikes() {
        likesCount++;
        return likesCount;
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostHashTag> hashTags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLikes> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostImage> postImages = new ArrayList<>();

    public int postlikesCount() {
        return postLikes.size();
    }


    // 연관관계 편의 메서드

    public void addPostImages(PostImage postImages) {
        this.getPostImages().add(postImages);
        postImages.setPost(this);
    }


    public void addPostLikes(PostLikes postLikes) {
        this.postLikes.add(postLikes);
        postLikes.setPost(this);
    }
    public void addPostHashtag(PostHashTag posthashTag) {
        this.hashTags.add(posthashTag);
        posthashTag.setPost(this);

    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setInPost(this);
    }


    public Post(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    @Builder
    public Post(Category category, Gender gender, SergeryProgress sergeryProgress, int transplantAmount, SatisfactionLevel satisfactionLevel, String title, String content) {
        this.category = category;
        this.gender = gender;
        this.sergeryProgress = sergeryProgress;
        this.transplantAmount = transplantAmount;
        this.satisfactionLevel = satisfactionLevel;
        this.title = title;
        this.content = content;
    }


    //Post 생성
    public static Post createPost(
            Category category, Gender gender,
            SergeryProgress sergeryProgress, int transplantAmount,
            SatisfactionLevel satisfactionLevel,
            String title, String content
    ) {

        return Post.builder()
                .category(category)
                .gender(gender)
                .sergeryProgress(sergeryProgress)
                .transplantAmount(transplantAmount)
                .satisfactionLevel(satisfactionLevel)
                .title(title)
                .content(content)
                .build();

    }





}
