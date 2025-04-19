package edu.gz.TDDGame.maze;

import java.util.ArrayList;

public class Grid {
	private ArrayList<Row> rows;
		
	
	public Grid(ArrayList<Row> rows) {
		this.rows = rows;
	}

	public ArrayList<Row> getRows() {
		return rows;
	}

	public void setRows(ArrayList<Row> rows) {
		this.rows = rows;
	}
}
