package com.jablonskiba;

import org.junit.Test;

import static org.junit.Assert.*;

import com.jablonskiba.models.Position;
import com.jablonskiba.strategies.BackwardDiagonalMovement;
import com.jablonskiba.strategies.ForwardDiagonalMovement;
import com.jablonskiba.strategies.NoneMovement;

public class StrategyTest {

  @Test
  public void testStrategies() {
    Position position = new Position(4, 4);
    assertTrue(new NoneMovement().moves(position).isEmpty());
    assertTrue(new BackwardDiagonalMovement().moves(position).contains(new Position(3, 5)));
    assertTrue(new ForwardDiagonalMovement().moves(position).contains(new Position(5, 5)));
  }

}
