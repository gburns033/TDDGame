package edu.gz.TDDGame.game;

import edu.gz.TDDGame.maze.Grid;
import edu.gz.TDDGame.maze.Row;
import edu.gz.TDDGame.maze.CellComponents;
import edu.gz.TDDGame.player.Movement;
import edu.gz.TDDGame.player.Player;

import java.util.ArrayList;

public class Game {
	private Grid grid;
	private int num;

	public Game(Grid grid) {
		this.grid = grid;
	}

	public Game(int num) {
		this.num = num;
	}

	public Grid getGrid() {
		return this.grid;
	}

	public boolean play(Movement movement, Player player) {
		Row row = player.getCurrentRow();
		int colIndex = row.getIndexOfCell(player.getCurrentCell());
		Integer rowIndex = grid.getIndexOfRow(row);
		
		switch (movement) {
			case UP:				
				if (rowIndex != null && rowIndex > 0) {
					player.setCurrentRow(grid.getRowAt(rowIndex - 1));
					player.setCurrentCell(null);
					
					return true;
				}
				
				return false;
			case DOWN:
				if (rowIndex != null && rowIndex < row.getRowSize()) {
					player.setCurrentRow(grid.getRowAt(rowIndex + 1));
					player.setCurrentCell(null);
					
					return true;
				}
				
				return false;
			case LEFT:
				if (colIndex > 0) {
					player.setCurrentCell(row.getCellAt(colIndex - 1));

					return true;
				}
				
				return false;
			case RIGHT:				
				if (colIndex < row.getCells().size()) {
					player.setCurrentCell(row.getCellAt(colIndex + 1));

					return true;
				}
				
				return false;
			default:
				return false;
		}
	}

	public void setGrid(Object object) {
		
	}

	public Grid createRandomGrid(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}