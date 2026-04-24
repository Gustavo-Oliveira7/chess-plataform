package com.gustavo.chessplataform.domain.chess;

import lombok.Getter;
import lombok.Setter;

public class Postion {

    @Setter
    @Getter
    private int row;
    @Setter
    @Getter
    private int col;

    public Postion(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Postion fromChess(String pos) {
        if(pos == null || pos.length() != 2) {
            throw new IllegalArgumentException("Invalid position format");
        }

        char columnChar = pos.charAt(0);
        char rowChar = pos.charAt(1);

        int col = columnChar - 'a';
        int row = 8 - Character.getNumericValue(rowChar);

        if (col < 0 || col > 7 || row <0 || row > 7) {
            throw new IllegalArgumentException("Position out of board");
        }

        return new Postion(row,col);
    }
}
