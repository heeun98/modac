package com.project.modac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class PostImage {


    @Builder
    public PostImage(String uuidFilename, String originFilename, String contentType) {
        this.uuidFilename = uuidFilename;
        this.originFilename = originFilename;
        this.contentType = contentType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //local 디스크에 저장된 상대경로 : (ex: /uploads/posts/2024/04/1234abcd.png)
    //1234abcd 는 uuid
    private String uploadPath;

    private String uuidFilename;

    private String originFilename;

    private String contentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

}
