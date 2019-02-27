package com.jablonskiba;

import static org.junit.Assert.assertFalse;
import java.util.stream.IntStream;

import com.jablonskiba.game.Board;
import com.jablonskiba.models.Move;
import com.jablonskiba.models.Piece;
import com.jablonskiba.models.Position;

import org.junit.Test;

public class GameTest {

  private Piece makeMove(Board board, Piece currentPiece, int sourceRow, int sourceColumn, int targetRow,
      int targetColumn) {
    new Move(board, currentPiece, new Position(sourceRow, sourceColumn), new Position(targetRow, targetColumn)).make();
    return currentPiece.opposite();
  }

  @Test
  public void playWholeGame() {
    Board board = TestHelper.getBoard();
    Piece currentPiece = Piece.BLACK;

    currentPiece = makeMove(board, currentPiece, 5, 0, 4, 1);
    currentPiece = makeMove(board, currentPiece, 2, 1, 3, 2);
    currentPiece = makeMove(board, currentPiece, 5, 2, 4, 3);
    currentPiece = makeMove(board, currentPiece, 3, 2, 5, 0);
    currentPiece = makeMove(board, currentPiece, 6, 3, 5, 2);
    currentPiece = makeMove(board, currentPiece, 2, 3, 3, 2);
    currentPiece = makeMove(board, currentPiece, 5, 4, 4, 5);
    currentPiece = makeMove(board, currentPiece, 3, 2, 5, 4);
    currentPiece = makeMove(board, currentPiece, 7, 2, 6, 3);
    currentPiece = makeMove(board, currentPiece, 5, 0, 7, 2);
    currentPiece = makeMove(board, currentPiece, 7, 0, 6, 1);
    currentPiece = makeMove(board, currentPiece, 7, 2, 5, 0);
    currentPiece = makeMove(board, currentPiece, 5, 2, 4, 1);
    currentPiece = makeMove(board, currentPiece, 5, 0, 3, 2);
    currentPiece = makeMove(board, currentPiece, 4, 5, 3, 4);
    currentPiece = makeMove(board, currentPiece, 2, 5, 4, 3);
    currentPiece = makeMove(board, currentPiece, 5, 6, 4, 5);
    currentPiece = makeMove(board, currentPiece, 5, 4, 3, 6);
    currentPiece = makeMove(board, currentPiece, 6, 5, 5, 4);
    currentPiece = makeMove(board, currentPiece, 4, 3, 6, 5);
    currentPiece = makeMove(board, currentPiece, 6, 7, 5, 6);
    currentPiece = makeMove(board, currentPiece, 6, 5, 4, 7);
    currentPiece = makeMove(board, currentPiece, 6, 3, 5, 2);
    currentPiece = makeMove(board, currentPiece, 3, 2, 4, 1);
    currentPiece = makeMove(board, currentPiece, 7, 4, 6, 5);
    currentPiece = makeMove(board, currentPiece, 4, 1, 6, 3);
    currentPiece = makeMove(board, currentPiece, 6, 5, 5, 4);
    currentPiece = makeMove(board, currentPiece, 6, 3, 4, 5);
    currentPiece = makeMove(board, currentPiece, 7, 6, 6, 5);
    currentPiece = makeMove(board, currentPiece, 4, 7, 5, 6);
    currentPiece = makeMove(board, currentPiece, 6, 5, 5, 4);
    currentPiece = makeMove(board, currentPiece, 4, 5, 6, 3);

    IntStream.range(0, 8).forEach(row -> {
      IntStream.range(0, 8).forEach(column -> {
        assertFalse(board.isPiece(new Position(row, column), Piece.BLACK));
      });
    });
  }

  @Test
  public void testValidMove() {
    Board board = TestHelper.getBoard();
    new Move(board, Piece.WHITE, new Position(2, 1), new Position(3, 0)).make();
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidPieceMove() {
    Board board = TestHelper.getBoard();
    new Move(board, Piece.BLACK, new Position(2, 1), new Position(3, 0)).make();
  }

  @Test(expected = IllegalStateException.class)
  public void testInalidPositionMove() {
    Board board = TestHelper.getBoard();
    new Move(board, Piece.WHITE, new Position(2, 1), new Position(1, 2)).make();
  }

}
