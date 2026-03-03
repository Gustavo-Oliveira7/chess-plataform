package com.gustavo.chessplataform.types.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank @Size(min = 3, max = 30) String username,
        @NotBlank @Email @Size(max = 120) String email,
        @NotBlank @Size(min = 6, max = 120) String password
) {
}
