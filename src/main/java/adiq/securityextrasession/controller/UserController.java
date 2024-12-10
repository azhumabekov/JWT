package adiq.securityextrasession.controller;

import adiq.securityextrasession.dto.request.AuthRequest;
import adiq.securityextrasession.dto.request.LoginRequest;
import adiq.securityextrasession.dto.responce.SimpleResponse;
import adiq.securityextrasession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Метод для входа
    @PostMapping("/signIn")
    public SimpleResponse signIn(@RequestBody LoginRequest loginRequest) {
        SimpleResponse token = userService.signIn(loginRequest);
        if (token == null) {
            return SimpleResponse.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message("Invalid credentials")
                    .build();
        }
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Sign-in successful, token: " + token)
                .build();
    }

    // Метод для регистрации
    @PostMapping("/signup")
    public SimpleResponse signUp(@RequestBody AuthRequest authRequest) {
        userService.signUp(authRequest);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Sign-up successful")
                .build();
    }

    @GetMapping("/getProfile/{id}")
    public SimpleResponse getProfile(@PathVariable String id) {
        userService.findByEmail(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Profile found")
                .build();
    }
}