package com.gustavo.chessplataform.types.user;

import java.time.Instant;

public record UserResponse(
        Long id,
        String username,
        String email,
        Instant createdAt
) {
}
