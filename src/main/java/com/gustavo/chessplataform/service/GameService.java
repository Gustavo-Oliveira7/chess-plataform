package com.gustavo.chessplataform.service;


import com.gustavo.chessplataform.domain.model.Game;
import com.gustavo.chessplataform.domain.repository.GameRepository;
import com.gustavo.chessplataform.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public Game createGame(Long whitePlayerId) {
        if(!userRepository.existsById(whitePlayerId)) {
            throw new RuntimeException("White player not found");
        }
        Game game = new Game(whitePlayerId);
        return gameRepository.save(game);

    }

    public Game joinGame(Long gameId, Long blackPlayerId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        if (!userRepository.existsById(blackPlayerId)){
            throw new RuntimeException("Black Player not found");
        }

        game.join(blackPlayerId);
        return gameRepository.save(game);
    }

    public Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public List<Game> listGames(){
        return gameRepository.findAll();
    }
}
