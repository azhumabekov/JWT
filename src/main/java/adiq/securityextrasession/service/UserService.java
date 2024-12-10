package adiq.securityextrasession.service;


import adiq.securityextrasession.dto.request.AuthRequest;
import adiq.securityextrasession.dto.request.LoginRequest;
import adiq.securityextrasession.dto.responce.SimpleResponse;
import adiq.securityextrasession.entity.User;

public interface UserService {


    SimpleResponse signUp(AuthRequest authRequest);

    SimpleResponse signIn(LoginRequest loginRequest);
    String getUserById(Long userId);

    User findByEmail(String email);

}
