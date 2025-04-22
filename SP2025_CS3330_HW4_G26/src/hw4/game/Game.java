package hw4.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.player.Movement;
import hw4.player.Player;

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
		if (player == null) {
			return false;
		}
		
		Row row = player.getCurrentRow();
		Cell cell = player.getCurrentCell();
		int colIndex = row.getIndexOfCell(player.getCurrentCell());
		Integer rowIndex = grid.getIndexOfRow(row);
		
		Row targetRow;
		Cell targetCell;
		
		switch (movement) {
			case UP:	
				if (rowIndex - 1 < 0) {
					return false;
				}
				
				targetRow = grid.getRowAt(rowIndex - 1);
				targetCell = targetRow.getCellAt(colIndex);
				
				if (rowIndex != null && rowIndex > 0 && cell.getUp() != CellComponents.WALL && targetCell.getDown() != CellComponents.WALL) {
					player.setCurrentRow(targetRow);
					player.setCurrentCell(targetCell);
					
					return true;
				}
				
				return false;
			case DOWN:
				if (rowIndex + 1 >= grid.getRows().size()) {
					return false;
				}
				
				targetRow = grid.getRowAt(rowIndex + 1);
				targetCell = targetRow.getCellAt(colIndex);
				
				if (rowIndex != null && rowIndex < row.getRowSize() && cell.getDown() != CellComponents.WALL && targetCell.getUp() != CellComponents.WALL) {
					player.setCurrentRow(targetRow);
					player.setCurrentCell(targetCell);
					
					return true;
				}
				
				return false;
			case LEFT:
				if (colIndex == 0 && cell.getLeft() == CellComponents.EXIT) {
					return true;
				}
				
				targetCell = row.getCellAt(colIndex - 1);
				
				if (colIndex >= 0 && cell.getLeft() != CellComponents.WALL && targetCell.getRight() != CellComponents.WALL) {
					player.setCurrentCell(targetCell);

					return true;
				}
				
				return false;
			case RIGHT:						
				if (colIndex + 1 >= row.getRowSize()) {
					return false;
				}
				
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
	
	@Override
	public String toString() {
		return "Game [grid=" + grid.toString() + "]";
	}
}