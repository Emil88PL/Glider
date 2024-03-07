package com.gameoflife.game;

public class BoardConfig {
  private final int rows;
  private final int cols;

  public BoardConfig(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }
}
