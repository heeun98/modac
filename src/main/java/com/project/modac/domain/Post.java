package com.project.modac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Post extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;


    private String content;


    private Long viewCount;

    @Enumerated(value = EnumType.STRING)
    private SergeryProgress progress;

    public void setUser(User user) {
        this.user = user;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
