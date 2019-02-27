package com.jablonskiba.strategies;

import java.util.Collections;
import java.util.List;

import com.jablonskiba.interfaces.MovementStrategy;
import com.jablonskiba.models.Position;

public class NoneMovement implements MovementStrategy {

  @Override
  public List<Position> moves(Position position) {
    return Collections.emptyList();
  }

}