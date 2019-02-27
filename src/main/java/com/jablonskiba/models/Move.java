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

  private boolean isLegal() {
    return board.isWithin(start) && board.isWithin(end) && board.isPiece(start, currentPiece)
        && board.isPiece(end, Piece.NONE);
  }

  public boolean isJump() {
    return board.getPiece(start).moves(start).contains(end);
  }

  public boolean isCapture() {
    return board.isPiece(middle, currentPiece.opposite());
  }

  public boolean isValid() {
    return isLegal() && (isJump() || isCapture());
  }

  public void make() {
    if (!isValid()) {
      throw new IllegalStateException("This move is invalid");
    }
    board.setPiece(middle, Piece.NONE);
    board.swapPieces(start, end);
  }
}
