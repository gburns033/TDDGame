package edu.gz.TDDGame.maze;

public class Cell {
    private CellComponents up;
    private CellComponents down;
    private CellComponents left;
    private CellComponents right;

    public Cell(CellComponents left, CellComponents down, CellComponents up, CellComponents right) {
        this.left = left;
        this.down = down;
        this.up = up;
        this.right = right;
    }

    public CellComponents getLeft() {
        return left;
    }

    public CellComponents getRight() {
        return right;
    }

    public CellComponents getUp() {
        return up;
    }

    public CellComponents getDown() {
        return down;
    }

    public void setUp(CellComponents up) {
        this.up = up;
    }

    public void setDown(CellComponents down) {
        this.down = down;
    }

    public void setLeft(CellComponents left) {
        this.left = left;
    }

    public void setRight(CellComponents right) {
        this.right = right;
    }
}
