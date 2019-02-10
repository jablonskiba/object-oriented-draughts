/*
  Forward and backward diaglonal captures are allowed.
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/* Strategy Design Pattern */
interface MovementStrategy {
  List<Position> moves(Position start);
}

class ForwardDiagonalMovement implements MovementStrategy {

  @Override
  public List<Position> moves(Position position) {
    return Arrays.asList(new Position(position.getRow() + 1, position.getColumn() + 1),
        new Position(position.getRow() + 1, position.getColumn() - 1));
  }

}

class BackwardDiagonalMovement implements MovementStrategy {

  @Override
  public List<Position> moves(Position position) {
    return Arrays.asList(new Position(position.getRow() - 1, position.getColumn() + 1),
        new Position(position.getRow() - 1, position.getColumn() - 1));
  }

}

class NoneMovement implements MovementStrategy {

  @Override
  public List<Position> moves(Position position) {
    return Collections.emptyList();
  }

}

enum Piece {
  BLACK(" X ", new BackwardDiagonalMovement()) {
    @Override
    Piece opposite() {
      return WHITE;
    }

    @Override
    List<Position> moves(Position position) {
      return movement.moves(position);
    }
  },
  WHITE(" O ", new ForwardDiagonalMovement()) {
    @Override
    Piece opposite() {
      return BLACK;
    }

    @Override
    List<Position> moves(Position position) {
      return movement.moves(position);
    }
  },
  NONE("   ", new NoneMovement()) {
    @Override
    Piece opposite() {
      return NONE;
    }

    @Override
    List<Position> moves(Position position) {
      return movement.moves(position);
    }
  };

  final String symbol;
  final MovementStrategy movement;

  abstract List<Position> moves(Position position);

  abstract Piece opposite();

  private Piece(String symbol, MovementStrategy movement) {
    this.symbol = symbol;
    this.movement = movement;
  }
}

class Position {
  private final int row;
  private final int column;

  Position(int row, int column) {
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

@FunctionalInterface
interface PiecesPrinnter {
  void print(Piece[][] pieces);
}

class Board {

  private final Piece[][] pieces;

  Board(Piece[][] pieces) {
    this.pieces = pieces;
  }

  public Piece getPiece(Position position) {
    return pieces[position.getRow()][position.getColumn()];
  }

  public void setPiece(Position position, Piece piece) {
    pieces[position.getRow()][position.getColumn()] = piece;
  }

  public boolean isPiece(Position position, Piece piece) {
    return getPiece(position) == piece;
  }

  public boolean isWithin(Position position) {
    return position.getRow() >= 0 && position.getColumn() >= 0 && position.getRow() < pieces.length
        && position.getColumn() < pieces.length;
  }

  public void swapPieces(Position first, Position second) {
    Piece temp = getPiece(first);
    setPiece(first, getPiece(second));
    setPiece(second, temp);
  }

  public void print(PiecesPrinnter piecesPrinnter) {
    piecesPrinnter.print(pieces);
  }

}

class Move {
  private final Board board;
  private final Piece currentPiece;

  private final Position start;
  private final Position end;
  private final Position middle;

  Move(Board board, Piece currentPiece, Position start, Position end) {
    this.board = board;
    this.currentPiece = currentPiece;
    this.start = start;
    this.end = end;
    middle = start.add(end.subtract(start).normalise());
  }

  private boolean isLegal(Position start, Position end) {
    return board.isWithin(start) && board.isWithin(end) && board.isPiece(start, currentPiece)
        && board.isPiece(end, Piece.NONE);
  }

  public boolean isJump(Position start, Position end) {
    return board.getPiece(start).moves(start).contains(end);
  }

  public boolean isCapture(Position position) {
    return board.isPiece(position, currentPiece.opposite());
  }

  public boolean isValid() {
    return isLegal(start, end) && (isJump(start, end) || isCapture(middle));
  }

  public boolean make() {
    if (isValid()) {
      board.setPiece(middle, Piece.NONE);
      board.swapPieces(start, end);

      return true;
    }
    return false;
  }
}

class Game {

  private static final Scanner SCANNER = new Scanner(System.in);
  private final Board board;
  private Piece currentPiece = Piece.BLACK;

  Game() {
    Piece[][] pieces = {
        { Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE },
        { Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE },
        { Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE },
        { Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE },
        { Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE },
        { Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE },
        { Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK },
        { Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE } };

    board = new Board(pieces);
  }

  private final PiecesPrinnter piecesPrinnter = (pieces) -> {
    System.out.print("  ");
    IntStream.range(0, pieces.length).forEach(row -> {
      System.out.print(String.format("  %d", row));
    });
    System.out.print("\n  +");
    IntStream.range(0, pieces.length).forEach(row -> {
      System.out.print("---");
    });
    System.out.println("+");

    IntStream.range(0, pieces.length).forEach(row -> {
      System.out.print(String.format("%d |", row));
      IntStream.range(0, pieces.length).forEach(column -> System.out.print(pieces[row][column].symbol));
      System.out.println("|");
    });

    System.out.print("  +");
    IntStream.range(0, pieces.length).forEach(row -> {
      System.out.print("---");
    });
    System.out.println("+");
  };

  public void start() {
    while (true) {
      board.print(piecesPrinnter);
      System.out.println("Turn of player:" + currentPiece.symbol);
      processUserInput();
    }
  }

  private void switchCurrentPiece() {
    currentPiece = currentPiece.opposite();
  }

  private int getCoordinate(String prompt) {
    System.out.print(prompt);
    while (!SCANNER.hasNextInt()) {
      SCANNER.nextLine();
    }
    return SCANNER.nextInt();
  }

  private Position getPosition(String prompt) {
    System.out.println(prompt);
    return new Position(getCoordinate("\tEnter row: "), getCoordinate("\tEnter column: "));
  }

  private void processUserInput() {
    Move move = new Move(board, currentPiece, getPosition("Coordinate of piece to move:"),
        getPosition("Coordinate of new position:"));

    if (move.make()) {
      System.out.println("Piece moved!");
      switchCurrentPiece();
    } else {
      System.out.println("Invalid move!");
    }
  }

}

public final class Checker {

  private Checker() {
    throw new AssertionError("Prevent reflection");
  }

  public static void main(String[] args) {
    Game mainGame = new Game();
    mainGame.start();
  }

}