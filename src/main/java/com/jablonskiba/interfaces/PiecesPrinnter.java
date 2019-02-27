package com.jablonskiba.interfaces;

import com.jablonskiba.models.Piece;

@FunctionalInterface
public interface PiecesPrinnter {
  void print(Piece[][] pieces);
}