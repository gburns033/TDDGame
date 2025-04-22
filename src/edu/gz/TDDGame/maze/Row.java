package edu.gz.TDDGame.maze;

import java.util.ArrayList;

/**
 * Represents a row in the maze grid, containing a list of cells.
 *
 * Each row holds multiple cells, which can be accessed and manipulated individually.
 */
public class Row {
    private ArrayList<Cell> cells;

    /**
     * Creates a new row with the specified list of cells.
     *
     * @param cells the list of cells in the row
     */
    public Row(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    /**
     * Returns the cell at the specified index in the row.
     *
     * @param index the index of the cell to retrieve
     * @return the cell at the specified index
     */
    public Cell getCellAt(int index) {
        return cells.get(index);
    }

    /**
     * Returns the index of the specified cell in the row.
     *
     * @param cell the cell whose index is to be found
     * @return the index of the specified cell, or -1 if the cell is not found
     */
    public int getIndexOfCell(Cell cell) {
        return cells.indexOf(cell);
    }

    /**
     * Returns the number of cells in the row.
     *
     * @return the size of the row (i.e., the number of cells)
     */
    public int getRowSize() {
        return cells.size();
    }

    /**
     * Returns the list of cells in the row.
     *
     * @return the list of cells in the row
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * Sets the list of cells in the row.
     *
     * @param cellList the new list of cells to be set in the row
     */
    public void setCells(ArrayList<Cell> cellList) {
        cells = cellList;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Row [cells=[");
    	
    	for (int i = 0; i < cells.size(); i++) {
    		Cell cell = cells.get(i);
    		sb.append(cell.toString());
    		
    		if (i != cells.size() - 1) {
    			sb.append(", ");
    		} else {
    			sb.append("]]");
    		}
    	}
    	
    	return sb.toString();
    }
}
