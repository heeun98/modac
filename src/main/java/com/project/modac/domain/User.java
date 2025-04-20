package com.project.modac.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String username; //로그인시 사용자 id , 고유값

    @Column
    private String password; // 로그인시 사용자 password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String nickname;

    private String email;

    @Enumerated(EnumType.STRING)
    private HairType hairType;

    private String hairLossLevel;


    @Embedded
    private Address address;

    private int transplantAmount;

    public void addPost(Post post) {
        post.setUser(this);
        this.getPosts().add(post);
    }

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setInUser(this);
    }


    public User(String username, int transplantAmount) {
        this.username = username;
        this.transplantAmount = transplantAmount;
    }

    @Builder
    public User(String username, String password, String nickname, String email, Address address) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
    }
}
