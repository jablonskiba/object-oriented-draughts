package com.jablonskiba.models;

import com.jablonskiba.game.Board;

public class Move {
  private final Board board;
  private final Piece currentPiece;

  private final Position start;
  private final Position end;
  private final Position middle;

  public Move(Board board, Piece currentPiece, Position start, Position end) {
    this.board = board;
    this.currentPiece = currentPiece;
    this.start = start;
    this.end = end;
    middle = start.add(end.subtract(start).normalise());
  }

  private boolean isLegal(Position start, Position end) {
    return board.isWithin(start) && board.isWithin(end) && board.isPiece(start, currentPiece)
        && board.isPiece(end, Piece.NONE);
  }

  public boolean isJump(Position start, Position end) {
    return board.getPiece(start).moves(start).contains(end);
  }

  public boolean isCapture(Position position) {
    return board.isPiece(position, currentPiece.opposite());
  }

  public boolean isValid() {
    return isLegal(start, end) && (isJump(start, end) || isCapture(middle));
  }

  public boolean make() {
    if (isValid()) {
      board.setPiece(middle, Piece.NONE);
      board.swapPieces(start, end);

      return true;
    }
    return false;
  }
}
