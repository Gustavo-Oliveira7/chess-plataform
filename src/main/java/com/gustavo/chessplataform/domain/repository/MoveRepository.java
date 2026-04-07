package com.gustavo.chessplataform.domain.repository;

import com.gustavo.chessplataform.domain.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move, Long> {
}
