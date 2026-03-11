CREATE TABLE games (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    white_player_id BIGINT NOT NULL,
    black_player_id BIGINT NULL,

    status VARCHAR(20) NOT NULL,

    board_fen VARCHAR(120) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_games_white_player FOREIGN KEY (white_player_id) REFERENCES users(id),
    CONSTRAINT fk_games_black_player FOREIGN KEY (black_player_id) REFERENCES users(id)
);