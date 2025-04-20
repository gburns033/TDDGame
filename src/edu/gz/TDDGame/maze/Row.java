package edu.gz.TDDGame.maze;

import java.util.ArrayList;

public class Row {
	private ArrayList<Cell> cells;
	
	
	public Row(ArrayList<Cell> cells) {
		this.cells = cells;
	}
	
    public Cell getCellAt(int index) {
        return cells.get(index);
    }

    public int getIndexOfCell(Cell cell) {
        return cells.indexOf(cell);
    }

    public int getRowSize() {
    	return cells.size();
    }
    
	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cellList) {
		cells = cellList;
	}
}
