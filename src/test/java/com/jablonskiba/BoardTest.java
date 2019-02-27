package com.jablonskiba;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.jablonskiba.game.Board;
import com.jablonskiba.models.Piece;
import com.jablonskiba.models.Position;

import org.junit.Test;

public class BoardTest {

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalInstantation() {
    Piece[][] pieces = { { Piece.NONE, Piece.NONE } };
    new Board(pieces);
  }

  @Test
  public void testInitialPieces() {
    Board board = TestHelper.getBoard();
    assertTrue(board.isPiece(new Position(0, 0), Piece.NONE));
    assertFalse(board.isPiece(new Position(0, 0), Piece.WHITE));
    assertTrue(board.isPiece(new Position(6, 3), Piece.BLACK));
  }

  @Test
  public void testDimensions() {
    Board board = TestHelper.getBoard();
    assertFalse(board.isWithin(new Position(-1, 0)));
  }

  @Test
  public void testMutations() {
    Board board = TestHelper.getBoard();
    board.swapPieces(new Position(1, 0), new Position(6, 7));
    assertTrue(board.isPiece(new Position(1, 0), Piece.BLACK));
    board.setPiece(new Position(1, 0), Piece.WHITE);
    assertTrue(board.getPiece(new Position(1, 0)) == Piece.WHITE);
  }

}
