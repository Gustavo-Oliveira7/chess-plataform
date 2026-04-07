package com.gustavo.chessplataform.controller;

import com.gustavo.chessplataform.domain.model.Game;
import com.gustavo.chessplataform.domain.model.Move;
import com.gustavo.chessplataform.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestParam Long whitePlayerID) {
        return gameService.createGame(whitePlayerID);
    }

    @PostMapping("/{gameId}/join")
    public Game joinGame(
            @PathVariable Long gameId,
            @RequestParam Long blackPlayerId
    ) {
        return gameService.joinGame(gameId, blackPlayerId);
    }

    @PostMapping("/{gameId}/moves")
    public Move makeMove(
            @PathVariable Long gameId,
            @RequestParam String from,
            @RequestParam String to
    ) {
        return gameService.makeMove(gameId, from, to);
    }


    @GetMapping("/{id}")
    public Game getGame(@PathVariable Long id) {
        return gameService.getGame(id);
    }

    @GetMapping
    public List<Game> listGames() {
        return gameService.listGames();
    }
}
