package com.jablonskiba.strategies;

import java.util.List;
import java.util.Arrays;

import com.jablonskiba.interfaces.MovementStrategy;
import com.jablonskiba.models.Position;

public class ForwardDiagonalMovement implements MovementStrategy {

  @Override
  public List<Position> moves(Position position) {
    return Arrays.asList(new Position(position.getRow() + 1, position.getColumn() + 1),
        new Position(position.getRow() + 1, position.getColumn() - 1));
  }

}