package com.jablonskiba.game;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.jablonskiba.interfaces.PiecesPrinnter;
import com.jablonskiba.models.Move;
import com.jablonskiba.models.Piece;
import com.jablonskiba.models.Position;

public class Game {

  private static final Scanner SCANNER = new Scanner(System.in);
  private final Board board;
  private Piece currentPiece = Piece.BLACK;

  public Game() {
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

  private static final PiecesPrinnter PRINNTER = (pieces) -> {
    int squareLength = 3;
    StringBuilder builder = new StringBuilder();

    builder.append("   ");
    IntStream.range(0, pieces.length).forEach(row -> {
      builder //
          .insert(squareLength + row * squareLength, String.format("  %d", row)) //
          .insert(2 * (squareLength + row * squareLength), "---") //
          .append(String.format(" %d |", row)) //
          .append(IntStream.range(0, pieces.length) //
              .mapToObj(column -> pieces[row][column].symbol).collect(Collectors.joining())) //
          .append("|").append("\n");
    });
    builder //
        .insert(squareLength + pieces.length * squareLength, "   +") //
        .insert(2 * (squareLength + pieces.length * squareLength) + 1, "+ ") //
        .insert(squareLength + pieces.length * squareLength, '\n') //
        .insert(2 * (squareLength + pieces.length * squareLength) + squareLength + 1, '\n') //
        .append(builder.subSequence(squareLength + pieces.length * squareLength + 1,
            2 * (squareLength + pieces.length * squareLength) + 3)) //
        .append('\n');

    System.out.print(builder.toString());
  };

  public void start() {
    while (true) {
      board.print(PRINNTER);
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