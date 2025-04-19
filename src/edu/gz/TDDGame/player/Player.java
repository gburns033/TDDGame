package edu.gz.TDDGame.player;

import edu.gz.TDDGame.maze.Cell;
import edu.gz.TDDGame.maze.Row;

public class Player {
	private Row row;
	private Cell cell;

	public Player(Row row, Cell cell) {
		this.row = row;
		this.cell = cell;
	}

	public Row getCurrentRow() {
		return this.row;
	}

	public Cell getCurrentCell() {
		return this.cell;
	}
}
