package com.gustavo.chessplataform.domain.repository;

import com.gustavo.chessplataform.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {}
