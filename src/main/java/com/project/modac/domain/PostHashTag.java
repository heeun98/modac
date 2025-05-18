package com.project.modac.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHashTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private HashTag hashTag;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    public void addHashTag(HashTag hashTag) {
        this.hashTag = hashTag;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    public static PostHashTag createPostHashTage(HashTag hashTag) {
        PostHashTag postHashTag = new PostHashTag();
        postHashTag.addHashTag(hashTag);
        return postHashTag;
    }
}
