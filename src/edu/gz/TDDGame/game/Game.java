package edu.gz.TDDGame.game;

import edu.gz.TDDGame.maze.Grid;
import edu.gz.TDDGame.maze.Row;
import edu.gz.TDDGame.maze.Cell;
import edu.gz.TDDGame.maze.CellComponents;
import edu.gz.TDDGame.player.Movement;
import edu.gz.TDDGame.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
		Cell cell = player.getCurrentCell();
		int colIndex = row.getIndexOfCell(player.getCurrentCell());
		Integer rowIndex = grid.getIndexOfRow(row);
		
		Row targetRow;
		Cell targetCell;
		
		switch (movement) {
			case UP:		
				targetRow = grid.getRowAt(rowIndex - 1);
				targetCell = targetRow.getCellAt(colIndex);
				
				if (rowIndex != null && rowIndex > 0 && cell.getUp() != CellComponents.WALL && targetCell.getDown() != CellComponents.WALL) {
					player.setCurrentRow(targetRow);
					player.setCurrentCell(null);
					
					return true;
				}
				
				return false;
			case DOWN:
				targetRow = grid.getRowAt(rowIndex + 1);
				targetCell = targetRow.getCellAt(colIndex);
				
				if (rowIndex != null && rowIndex < row.getRowSize() && cell.getDown() != CellComponents.WALL && targetCell.getUp() != CellComponents.WALL) {
					player.setCurrentRow(targetRow);
					player.setCurrentCell(null);
					
					return true;
				}
				
				return false;
			case LEFT:
				targetCell = row.getCellAt(colIndex - 1);
				
				if (colIndex > 0 && cell.getLeft() != CellComponents.WALL && targetCell.getRight() != CellComponents.WALL) {
					player.setCurrentCell(targetCell);

					return true;
				}
				
				return false;
			case RIGHT:		
				targetCell = row.getCellAt(colIndex + 1);
				
				if (colIndex < row.getCells().size() && cell.getRight() != CellComponents.WALL && targetCell.getLeft() != CellComponents.WALL) {
					player.setCurrentCell(targetCell);

					return true;
				}
				
				return false;
			default:
				return false;
		}
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Grid createRandomGrid(int i) {
		return grid;
		
	}
}