package com.gustavo.chessplataform.service;


import com.gustavo.chessplataform.domain.model.User;
import com.gustavo.chessplataform.domain.repository.UserRepository;
import com.gustavo.chessplataform.types.user.CreateUserRequest;
import com.gustavo.chessplataform.types.user.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(CreateUserRequest request){
        if (userRepository.existsByEmail(request.username())){
            throw new IllegalArgumentException("username already in use");
        }
        if (userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("email already in use");
        }

        String passwordHash = "fakeHash" + request.password();
        User saved = userRepository.save(new User(request.username(), request.email(), passwordHash));
        return new UserResponse(saved.getId(), saved.getUsername(), saved.getEmail(), saved.getCreatedAt());
    }

    public List<UserResponse> list() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt()))
                .toList();
    }

}
