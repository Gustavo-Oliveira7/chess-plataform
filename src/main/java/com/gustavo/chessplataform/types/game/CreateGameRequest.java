package com.gustavo.chessplataform.types.game;

import jakarta.validation.constraints.NotNull;

public record CreateGameRequest(@NotNull Long whitePlayerId) {
}
