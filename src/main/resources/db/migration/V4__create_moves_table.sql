CREATE TABLE moves (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_position VARCHAR(5) NOT NULL,
    to_position VARCHAR(5) NOT NULL,
    fen_after_move TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    game_id BIGINT,
    CONSTRAINT fk_game
       FOREIGN KEY (game_id)
           REFERENCES games(id)
);