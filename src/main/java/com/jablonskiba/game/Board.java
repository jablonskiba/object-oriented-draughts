package com.jablonskiba.game;

import com.jablonskiba.interfaces.PiecesPrinnter;
import com.jablonskiba.models.Piece;
import com.jablonskiba.models.Position;

public class Board {

  private final Piece[][] pieces;

  Board(Piece[][] pieces) {
    if (pieces.length != pieces[0].length) {
      throw new IllegalArgumentException(
          String.format("Array (%d, %d) must be square", pieces.length, pieces[0].length));
    }
    this.pieces = pieces;
  }

  public Piece getPiece(Position position) {
    return pieces[position.getRow()][position.getColumn()];
  }

  public void setPiece(Position position, Piece piece) {
    pieces[position.getRow()][position.getColumn()] = piece;
  }

  public boolean isPiece(Position position, Piece piece) {
    return getPiece(position) == piece;
  }

  public boolean isWithin(Position position) {
    return position.getRow() >= 0 && position.getColumn() >= 0 && position.getRow() < pieces.length
        && position.getColumn() < pieces.length;
  }

  public void swapPieces(Position first, Position second) {
    Piece temp = getPiece(first);
    setPiece(first, getPiece(second));
    setPiece(second, temp);
  }

  public void print(PiecesPrinnter piecesPrinnter) {
    piecesPrinnter.print(pieces);
  }

}