package com.jablonskiba;

import com.jablonskiba.game.Game;

public final class Checker {

  private Checker() {
    throw new AssertionError("Prevent reflection");
  }

  public static void main(String[] args) {
    Game mainGame = new Game();
    mainGame.start();
  }

}