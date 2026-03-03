package com.gustavo.chessplataform.controller;


import com.gustavo.chessplataform.service.UserService;
import com.gustavo.chessplataform.types.user.CreateUserRequest;
import com.gustavo.chessplataform.types.user.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody CreateUserRequest request){
        return userService.create(request);
    }

    @GetMapping
    public List<UserResponse> list(){
        return userService.list();
    }
}
