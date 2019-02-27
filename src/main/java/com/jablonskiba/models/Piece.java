package com.jablonskiba.models;

import java.util.List;

import com.jablonskiba.interfaces.MovementStrategy;
import com.jablonskiba.strategies.*;

public enum Piece {
  BLACK(" X ", new BackwardDiagonalMovement()) {
    @Override
    public Piece opposite() {
      return WHITE;
    }

    @Override
    List<Position> moves(Position position) {
      return movement.moves(position);
    }
  },
  WHITE(" O ", new ForwardDiagonalMovement()) {
    @Override
    public Piece opposite() {
      return BLACK;
    }

    @Override
    List<Position> moves(Position position) {
      return movement.moves(position);
    }
  },
  NONE("   ", new NoneMovement()) {
    @Override
    public Piece opposite() {
      return NONE;
    }

    @Override
    List<Position> moves(Position position) {
      return movement.moves(position);
    }
  };

  public final String symbol;
  final MovementStrategy movement;

  abstract List<Position> moves(Position position);

  public abstract Piece opposite();

  private Piece(String symbol, MovementStrategy movement) {
    this.symbol = symbol;
    this.movement = movement;
  }
}