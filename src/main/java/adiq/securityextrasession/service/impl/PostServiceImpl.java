package adiq.securityextrasession.service.impl;

import adiq.securityextrasession.dto.request.CreatePostRequest;
import adiq.securityextrasession.dto.responce.SimpleResponse;
import adiq.securityextrasession.entity.Post;
import adiq.securityextrasession.repo.PostRepo;
import adiq.securityextrasession.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;


    @Override
    public SimpleResponse createPost(Long userId, CreatePostRequest createPostResponse) {
           postRepo.findById(userId).orElseThrow(()->
                   new RuntimeException("User ID: " + userId + " not found!"));

        Post post = new Post();
        post.setDescription(createPostResponse.getDescription());
        post.setImages(createPostResponse.getImages());
        postRepo.save(post);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Post created successfully")
                .build();

    }
}
