package adiq.securityextrasession.service;


import adiq.securityextrasession.dto.request.CreatePostRequest;
import adiq.securityextrasession.dto.responce.SimpleResponse;

public interface PostService {
     SimpleResponse createPost(Long userId, CreatePostRequest createPostRequest);

}
