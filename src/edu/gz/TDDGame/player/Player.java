package edu.gz.TDDGame.player;

import edu.gz.TDDGame.maze.Cell;
import edu.gz.TDDGame.maze.Row;

/**
 * Represents a player in the maze.
 * The player has a current position defined by a specific row and cell.
 */
public class Player {
	private Row row;
	private Cell cell;

	/**
	 * Constructs a new Player with a given starting row and cell.
	 *
	 * @param row  the row where the player starts
	 * @param cell the cell within the row where the player starts
	 */
	public Player(Row row, Cell cell) {
		this.row = row;
		this.cell = cell;
	}

	/**
	 * Gets the current row of the player.
	 *
	 * @return the current row
	 */
	public Row getCurrentRow() {
		return this.row;
	}

	/**
	 * Gets the current cell of the player.
	 *
	 * @return the current cell
	 */
	public Cell getCurrentCell() {
		return this.cell;
	}
	
	/**
	 * Sets the current row of the player.
	 *
	 * @param row the new row to set
	 */
	public void setCurrentRow(Row row) {
		this.row = row;
	}
	
	/**
	 * Sets the current cell of the player.
	 *
	 * @param cell the new cell to set
	 */
	public void setCurrentCell(Cell cell) {
		this.cell = cell;
	}
}
