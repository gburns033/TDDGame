package edu.gz.TDDGame.maze;

import java.util.ArrayList;

/**
 * Represents the grid of the maze, containing a list of rows.
 *
 * The grid is a two-dimensional structure made up of rows, where each row contains multiple cells.
 */
public class Grid {
    private ArrayList<Row> rows;

    /**
     * Creates a new grid with the specified list of rows.
     *
     * @param rows the list of rows that make up the grid
     */
    public Grid(ArrayList<Row> rows) {
        this.rows = rows;
    }

    /**
     * Returns the index of the specified row in the grid.
     *
     * @param row the row whose index is to be found
     * @return the index of the specified row, or -1 if the row is not found
     */
    public Integer getIndexOfRow(Row row) {
        return getRows().indexOf(row);
    }

    /**
     * Returns the row at the specified index in the grid.
     *
     * @param index the index of the row to retrieve
     * @return the row at the specified index
     */
    public Row getRowAt(int index) {
        return getRows().get(index);
    }

    /**
     * Returns the list of rows in the grid.
     *
     * @return the list of rows in the grid
     */
    public ArrayList<Row> getRows() {
        return rows;
    }

    /**
     * Sets the list of rows in the grid.
     *
     * @param rows the new list of rows to be set in the grid
     */
    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Grid [rows=[");
    	
    	for (int i = 0; i < rows.size(); i++) {
    		Row row = rows.get(i);
    		sb.append(row.toString());
    		
    		if (i != rows.size() - 1) {
    			sb.append(", ");
    		} else {
    			sb.append("]]");
    		}
    	}
    	
    	return sb.toString();
    }
}
