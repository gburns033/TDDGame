package edu.gz.TDDGame.maze;

/**
 * Represents a single cell in the maze.
 *
 * Each cell has four sides: up, down, left, and right.
 * Each side can be a wall, an aperture, or an exit.
 */
public class Cell {
    private CellComponents up;
    private CellComponents down;
    private CellComponents left;
    private CellComponents right;

    /**
     * Creates a new cell with the specified components on each side.
     *
     * @param left the component on the left side of the cell
     * @param down the component on the bottom side of the cell
     * @param up the component on the top side of the cell
     * @param right the component on the right side of the cell
     */
    public Cell(CellComponents left, CellComponents down, CellComponents up, CellComponents right) {
    public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
        this.left = left;
        this.down = down;
        this.up = up;
        this.right = right;
    }

    /**
     * Returns the component on the left side of the cell.
     *
     * @return the left side component
     */
    public CellComponents getLeft() {
        return left;
    }

    /**
     * Returns the component on the right side of the cell.
     *
     * @return the right side component
     */
    public CellComponents getRight() {
        return right;
    }

    /**
     * Returns the component on the top side of the cell.
     *
     * @return the top side component
     */
    public CellComponents getUp() {
        return up;
    }

    /**
     * Returns the component on the bottom side of the cell.
     *
     * @return the bottom side component
     */
    public CellComponents getDown() {
        return down;
    }

    /**
     * Sets the component on the top side of the cell.
     *
     * @param up the new top side component
     */
    public void setUp(CellComponents up) {
        this.up = up;
    }

    /**
     * Sets the component on the bottom side of the cell.
     *
     * @param down the new bottom side component
     */
    public void setDown(CellComponents down) {
        this.down = down;
    }

    /**
     * Sets the component on the left side of the cell.
     *
     * @param left the new left side component
     */
    public void setLeft(CellComponents left) {
        this.left = left;
    }

    /**
     * Sets the component on the right side of the cell.
     *
     * @param right the new right side component
     */
    public void setRight(CellComponents right) {
        this.right = right;
    }
    
    @Override
    public String toString() {
    	return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
    }
}
