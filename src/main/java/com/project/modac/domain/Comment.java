package com.project.modac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private int likeCount;


    public String getContent() {
        return content;
    }

    public Comment(String content) {
        this.content = content;
    }

    public void setInPost(Post post) {
        this.post = post;
    }

    public void setInUser(User user) {
        this.user = user;
    }
}
