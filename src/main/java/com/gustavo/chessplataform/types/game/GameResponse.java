package com.gustavo.chessplataform.types.game;

import com.gustavo.chessplataform.domain.model.GameStatus;

import java.time.Instant;

public record GameResponse(
        Long id,
        Long whitePlayerId,
        Long blackPlayerId,
        GameStatus status,
        String boardFen,
        Instant createdAt,
        Instant updatedAt
) {
}
