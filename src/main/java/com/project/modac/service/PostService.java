package com.project.modac.service;

import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import com.project.modac.domain.*;
import com.project.modac.dto.*;
import com.project.modac.file.FileStore;
import com.project.modac.file.UploadFile;
import com.project.modac.repository.HashtagRepository;
import com.project.modac.repository.PostImageRepository;
import com.project.modac.repository.PostRepository;
import com.project.modac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostService {

    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final FileStore fileStore;


    @Transactional
    public Long postDetailService(PostCreateRequestDtoV1 request, String username) {

        User user = userRepository.findOptionByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Post newPost = getPost(request);

        for (String hashtag : request.getHashtags()) {

            HashTag hashTag = hashtagRepository.findByName(hashtag)
                    .orElseGet(() -> {
                        HashTag newhashTag = HashTag.builder()
                                .name(hashtag)
                                .build();
                        return hashtagRepository.save(newhashTag);
                    });

            PostHashTag postHashTage = PostHashTag.createPostHashTage(hashTag);
            newPost.addPostHashtag(postHashTage);
        }

        Post save = postRepository.save(newPost);

        user.addPost(newPost);



        return save.getId();
    }

    @Transactional
    public PostCreateResponseDtoV2 postDetailServiceV2(PostCreateRequestDtoV2 request, String username) throws IOException {

        User user = userRepository.findOptionByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Post newPost = getPostV2(request);

        for (String hashtag : request.getHashtags()) {

            HashTag hashTag = hashtagRepository.findByName(hashtag)
                    .orElseGet(() -> {
                        HashTag newhashTag = HashTag.builder()
                                .name(hashtag)
                                .build();
                        return hashtagRepository.save(newhashTag);
                    });

            PostHashTag postHashTage = PostHashTag.createPostHashTage(hashTag);
            newPost.addPostHashtag(postHashTage);
        }

        List<MultipartFile> imageFiles = request.getImageFiles();

        List<UploadFile> uploadFiles = fileStore.storeFiles(imageFiles);

        PostCreateResponseDtoV2 responseDtoV2 = new PostCreateResponseDtoV2();

        //데이터베이스에 저장
        for (UploadFile uploadFile : uploadFiles) {
            PostImage image = PostImage.builder()
                    .uuidFilename(uploadFile.getStoreFileName())
                    .originFilename(uploadFile.getUploadFileName())
                    .contentType(uploadFile.getContentType())
                    .build();

            newPost.addPostImages(image);


            ImageResponseDto imageResponseDto = ImageResponseDto.builder()
                    .originalFilename(uploadFile.getUploadFileName())
                    .uuidFilename(uploadFile.getStoreFileName())
                    .build();


            responseDtoV2.getPostImages().add(imageResponseDto);
        }

        Post post = postRepository.save(newPost);

        user.addPost(newPost);



        responseDtoV2.setPost_id(post.getId());

        return responseDtoV2;

    }




    @Transactional
    public PostCreateResponseDtoV2 postDetailServiceV3(PostCreateRequestDtoV2 request, String username) throws IOException {

        User user = userRepository.findOptionByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Post newPost = getPostV2(request);

        createHashTag(request, newPost);

        List<UploadFile> uploadFiles = fileStore.storeFiles(request.getImageFiles());

        PostCreateResponseDtoV2 responseDtoV2 = new PostCreateResponseDtoV2();

        //데이터베이스에 저장
        for (UploadFile uploadFile : uploadFiles) {

            PostImage postImage = PostImage.createPostImage(
                    uploadFile.getStoreFileName(), uploadFile.getUploadFileName(), uploadFile.getContentType()
            );

            newPost.addPostImages(postImage);


            ImageResponseDto imageResponseDto = ImageResponseDto.builder()
                    .originalFilename(uploadFile.getUploadFileName())
                    .uuidFilename(uploadFile.getStoreFileName())
                    .build();


            responseDtoV2.getPostImages().add(imageResponseDto);
        }

        Post post = postRepository.save(newPost);

        user.addPost(newPost);



        responseDtoV2.setPost_id(post.getId());

        return responseDtoV2;

    }

    private void createHashTag(PostCreateRequestDtoV2 request, Post newPost) {
        for (String stringHashTag : request.getHashtags()) {

            HashTag hashTag = hashtagRepository.findByName(stringHashTag)
                    .orElseGet(() -> {
                        HashTag newHashTag = HashTag.createHashTag(stringHashTag);
                        return hashtagRepository.save(newHashTag);
                    });

            PostHashTag postHashTage = PostHashTag.createPostHashTage(hashTag);
            newPost.addPostHashtag(postHashTage);
        }
    }


    private static Post getPost(PostCreateRequestDtoV1 request) {
        //기본적인 Post 생성 , 연관관계는 설정안함
        Post newPost = Post.createPost(
                request.getCategory(),
                request.getGender(),
                request.getSergeryProgress(),
                request.getTransplantCount(),
                SatisfactionLevel.fromValue(request.getSatisfactionScore()),
                request.getTitle(),
                request.getContent()
        );
        return newPost;
    }


    private static Post getPostV2(PostCreateRequestDtoV2 request) {
        //기본적인 Post 생성 , 연관관계는 설정안함
        Post newPost = Post.createPost(
                request.getCategory(),
                request.getGender(),
                request.getSergeryProgress(),
                request.getTransplantCount(),
                SatisfactionLevel.fromValue(request.getSatisfactionScore()),
                request.getTitle(),
                request.getContent()
        );
        return newPost;
    }

    public PostDetailResponseDto postDetailService(Long postId, String username) {

        Post postDetail = postRepository.getPostDetail(postId);

        if (postDetail == null) {
            throw new GeneralException(ErrorStatus.INVALID_REQUEST);
        }

        PostDetailResponseDto postDetailResponseDto = PostDetailResponseDto.of(postDetail, username);
        return postDetailResponseDto;

    }
}
