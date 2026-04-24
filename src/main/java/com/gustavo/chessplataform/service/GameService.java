package com.gustavo.chessplataform.service;


import com.gustavo.chessplataform.domain.chess.Postion;
import com.gustavo.chessplataform.domain.model.Game;
import com.gustavo.chessplataform.domain.model.Move;
import com.gustavo.chessplataform.domain.repository.GameRepository;
import com.gustavo.chessplataform.domain.repository.MoveRepository;
import com.gustavo.chessplataform.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final MoveRepository moveRepository;

    public GameService(GameRepository gameRepository, UserRepository userRepository, MoveRepository moveRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.moveRepository = moveRepository;
    }

    public Game createGame(Long whitePlayerId) {
        if(!userRepository.existsById(whitePlayerId)) {
            throw new RuntimeException("White player not found");
        }
        Game game = new Game(whitePlayerId);
        game.setCurrentTurn("WHITE");
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

    ///isso aqui vai sair daqui logo menos, vamos ter que criar o moveService em algum momento
    public Move makeMove(Long gameId, String from, String to){
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        Postion fromPos = Postion.fromChess(from);
        Postion toPos = Postion.fromChess(to);

        if (fromPos.getRow() == toPos.getRow() &&
            fromPos.getCol() == toPos.getCol()){
            throw new RuntimeException("Invalid move: same position");
        }

        Move move = new Move();
        move.setFromPosition(from);
        move.setToPosition(to);
        move.setGame(game);

        if (game.getCurrentTurn().equals("WHITE")) {
            game.setCurrentTurn("BLACK");
        } else {
            game.setCurrentTurn("WHITE");
        }

        gameRepository.save(game);

        return moveRepository.save(move);
    }


    public Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public List<Game> listGames(){
        return gameRepository.findAll();
    }
}
