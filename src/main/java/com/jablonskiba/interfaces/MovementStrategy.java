package com.jablonskiba.interfaces;

import java.util.List;

import com.jablonskiba.models.Position;

public interface MovementStrategy {
  List<Position> moves(Position start);
}