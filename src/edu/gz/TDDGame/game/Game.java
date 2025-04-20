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
		switch (movement) {
			case UP:
				for (int i = 0; i < grid.getRows().size(); i++) {
					if (player.getCurrentRow().equals(grid.getRows().get(i))) {
						if (grid.getRows().get(i + 1) != null || player.getCurrentCell().getUp() != CellComponents.WALL) {
							player.setCurrentRow(grid.getRows().get(i));
							
							return true;
						} else {
							return false;
						}
					}
				}
				break;
			case DOWN:
				for (int i = 0; i < grid.getRows().size(); i++) {
					if (player.getCurrentRow().equals(grid.getRows().get(i))) {
						if (grid.getRows().get(i - 1) != null || player.getCurrentCell().getDown() != CellComponents.WALL) {
							player.setCurrentRow(grid.getRows().get(i));
							
							return true;
						} else {
							return false;
						}
					}
				}
				break;
			case LEFT:
				int gridRowSize = grid.getRows().get(0).getCells().size();
				int playerColumn = grid.getRows()
				
				for (int i = 0; i < grid.getRows().size(); i++) {
					if (player.getCurrentRow().equals(grid.getRows().get(i))) {
						if (grid.getRows().get(i - 1) == null || player.getCurrentCell().getDown() != CellComponents.WALL) {
							return false;
						} else {
							return true;
						}
					}
				}
		}
	}

	public void setGrid(Object object) {
		// TODO Auto-generated method stub

	}

	public Grid createRandomGrid(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}