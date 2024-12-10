package adiq.securityextrasession.service.impl;

import adiq.securityextrasession.Enum.Role;
import adiq.securityextrasession.dto.request.AuthRequest;
import adiq.securityextrasession.dto.request.LoginRequest;
import adiq.securityextrasession.dto.responce.SimpleResponse;
import adiq.securityextrasession.entity.User;
import adiq.securityextrasession.repo.UserRepo;
import adiq.securityextrasession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SimpleResponse signUp(AuthRequest authRequest) {
        boolean exists = userRepo.existsByEmail(authRequest.getEmail());
        if (!exists) {
            return SimpleResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .message("Email already exists")
                    .build();
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setUsername(authRequest.getUsername());
        user.setRole(Role.USER);
        userRepo.save(user);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Success")
                .build();

    }

    @Override
    public SimpleResponse signIn(LoginRequest loginRequest) {
        // Ищем пользователя по email
        Optional<User> foundUser = userRepo.findByEmail(loginRequest.getEmail());

        if (foundUser.isEmpty()) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Email does not exist")
                    .build();

        }

        // Сравниваем пароли
        if (foundUser.get().getPassword().equals(loginRequest.getPassword())) {
            // Получаем имя пользователя из найденного объекта
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Success")
                    .build();
        }

        return SimpleResponse.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message("Incorrect password")
                .build();
    }

    @Override
    public String getUserById(Long userId) {
        return userRepo.findById(userId).get().getEmail();
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).get();
    }
}