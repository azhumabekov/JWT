package adiq.securityextrasession.controller;

import adiq.securityextrasession.dto.request.CreatePostRequest;
import adiq.securityextrasession.dto.responce.CreatePost;
import adiq.securityextrasession.dto.responce.SimpleResponse;
import adiq.securityextrasession.entity.Post;
import adiq.securityextrasession.service.PostService;
import adiq.securityextrasession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final UserService userService;
    private final PostService postService;

    // Получить пользователя по ID
    @GetMapping("/user/{userId}")
    public SimpleResponse getUserPosts(@PathVariable Long userId) {
         userService.getUserById(userId);
         return SimpleResponse.builder()
                 .status(HttpStatus.OK)
                 .message("Get user posts successfully")
                 .build();
    }

    // Создать новый пост
    @PostMapping("/create/{id}")
    public SimpleResponse createPost(@PathVariable("id") Long id, @RequestBody CreatePostRequest createPostRequest) {
        postService.createPost(id, createPostRequest);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("CreatedAd post successfully")
                .build();
    }
}
