package com.gustavo.chessplataform.types.game;

import jakarta.validation.constraints.NotNull;

public record JoinGameRequest(@NotNull Long blackPlayerId) {
}
