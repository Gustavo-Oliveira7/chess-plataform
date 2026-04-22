package com.gustavo.chessplataform.domain.model;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "games")
public class Game {
    public static final String INITIAL_FEN =
            "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_turn")
    private String currentTurn;

    @Column(name = "white_player_id", nullable = false)
    private Long whitePlayerId;

    @Column(name = "black_player_id")
    private Long blackPlayerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;

    @Column(name = "board_fen", nullable = false, length = 120)
    private String boardFen;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Setter
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Game(){}

    public Game(Long whitePlayerId) {
        this.whitePlayerId = whitePlayerId;
        this.status = GameStatus.WAITING;
        this.boardFen = INITIAL_FEN;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public void join(Long blackPlayerId) {
        if (this.blackPlayerId != null) {
            throw new IllegalStateException("game already has a black player");
        }
        if (this.whitePlayerId.equals(blackPlayerId)) {
            throw new IllegalArgumentException("white player cannot join as black");
        }
        this.blackPlayerId = blackPlayerId;
        this.status = GameStatus.ACTIVE;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(String currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWhitePlayerId() {
        return whitePlayerId;
    }

    public void setWhitePlayerId(Long whitePlayerId) {
        this.whitePlayerId = whitePlayerId;
    }

    public Long getBlackPlayerId() {
        return blackPlayerId;
    }

    public void setBlackPlayerId(Long blackPlayerId) {
        this.blackPlayerId = blackPlayerId;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public String getBoardFen() {
        return boardFen;
    }

    public void setBoardFen(String boardFen) {
        this.boardFen = boardFen;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
