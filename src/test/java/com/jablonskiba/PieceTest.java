package com.jablonskiba;

import org.junit.Test;

import static org.junit.Assert.*;

import com.jablonskiba.models.Piece;

public class PieceTest {

  @Test
  public void testOpposite() {
    assertEquals(Piece.BLACK.opposite(), Piece.WHITE);
    assertEquals(Piece.WHITE.opposite(), Piece.BLACK);
    assertEquals(Piece.NONE.opposite(), Piece.NONE);
  }

}
