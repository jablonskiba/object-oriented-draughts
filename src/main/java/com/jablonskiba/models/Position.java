package com.jablonskiba.models;

public class Position {
  private final int row;
  private final int column;

  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public Position add(Position position) {
    return new Position(row + position.getRow(), column + position.getColumn());
  }

  public Position subtract(Position position) {
    return new Position(row - position.getRow(), column - position.getColumn());
  }

  public Position normalise() {
    return new Position(Integer.signum(row), Integer.signum(column));
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    if (!(object instanceof Position)) {
      return false;
    }
    Position position = (Position) object;
    return position.row == row && position.column == column;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + row;
    result = 31 * result + column;
    return result;
  }

  @Override
  public String toString() {
    return String.format("Row: %s, Column: %s", row, column);
  }
}