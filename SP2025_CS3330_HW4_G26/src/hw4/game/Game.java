package hw4.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.player.Movement;
import hw4.player.Player;

public class Game {
	private Grid grid;
	private int n;
	
	private static Random random = new Random();

	public Game(Grid grid) {
		this.grid = grid;
	}

	public Game(int n) {
		this.n = n;
	}

	public Grid getGrid() {
		return this.grid;
	}

	public boolean play(Movement movement, Player player) {
		if (player == null || movement == null) {
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
	
	private static Cell createRandomCell() {
	    int baseApertureIndex = random.nextInt(4);
	    CellComponents[] components = new CellComponents[4];

	    for (int i = 0; i < 4; i++) {
	        if (i == baseApertureIndex) {
	            components[i] = CellComponents.APERTURE;
	        } else {
	            components[i] = getRandomNonExitCellComponent();
	        }
	    }

	    return new Cell(components[0], components[1], components[2], components[3]);
	}
	
	private static CellComponents getRandomNonExitCellComponent() {
	    return random.nextBoolean() ? CellComponents.APERTURE : CellComponents.WALL;
	}
	
	private static int[] getValidNextStep(int[] currentStep, int[][] pathGrid) {	
		int[] step = new int[2];
		
		do {		
			step[0] = random.nextInt(Math.min(0, currentStep[0] - 1),  Math.max(currentStep[0] + 1, pathGrid.length));		
			step[1] = random.nextInt(Math.min(0, currentStep[1] - 1),  Math.max(currentStep[1] + 1, pathGrid.length));
		} while(!isValidNextStep(step, pathGrid));
		
		return step;
	}
	
	private static boolean isValidNextStep(int[] step, int[][] pathGrid) {
		if (pathGrid[step[0]][step[1]] == 1) {
			return false;
		}
		
		return true;
	}

	public Grid createRandomGrid(int n) {
		if ( n < 3 || n > 7) {
			return null;
		}
		
		int[][] pathGrid = new int[n][n]; 
		
		int[] currentStep = { random.nextInt(n), n - 1 };
		
		while (currentStep[1] != 0) {
			pathGrid[currentStep[0]][currentStep[1]] = 1;
			
			int[] nextStep = getValidNextStep(currentStep, pathGrid);
			currentStep = nextStep;
		}
		
		pathGrid[currentStep[0]][currentStep[1]] = 2;
		
		ArrayList<Row> rows = new ArrayList<Row>();
		
		for (int rowIndex = 0; rowIndex < pathGrid.length; rowIndex++) {
			ArrayList<Cell> cells = new ArrayList<Cell>();
			
			for (int colIndex = 0; colIndex < pathGrid.length; colIndex++) {
				switch (pathGrid[rowIndex][colIndex]) {
					case 0:
						cells.add(createRandomCell());
						continue;
					case 1:
						cells.add(new Cell(CellComponents.APERTURE, CellComponents.APERTURE, CellComponents.APERTURE, CellComponents.APERTURE));
						continue;
					case 2:
						cells.add(new Cell(CellComponents.EXIT, CellComponents.APERTURE, CellComponents.APERTURE, CellComponents.APERTURE));
				}
			}
			
			rows.add(new Row(cells));
		}
		
		for (int rowIndex = 0; rowIndex < pathGrid.length; rowIndex++) {
			for (int colIndex = 0; colIndex < pathGrid.length; colIndex++) {
				Cell currentCell = rows.get(rowIndex).getCellAt(colIndex);
				Cell rightCell = null;
				
				if (colIndex + 1 < n) {
					rightCell = rows.get(rowIndex).getCellAt(Math.min(n - 1, colIndex + 1));
				}
				
				Cell belowCell = null;
				
				if (rowIndex + 1 < n) {
					belowCell = rows.get(Math.min(n - 1, rowIndex + 1)).getCellAt(colIndex);
				}
				
				boolean isCurrentCellOnPath = pathGrid[rowIndex][colIndex] == 1 || pathGrid[rowIndex][colIndex] == 2;
				
				if (belowCell != null && currentCell.getDown() != belowCell.getUp()) {			
					if (isCurrentCellOnPath) {
						belowCell.setUp(CellComponents.APERTURE);
					}
					
					boolean isBelowCellOnPath = pathGrid[Math.min(n - 1, rowIndex + 1)][colIndex] == 1 || pathGrid[Math.min(n - 1, rowIndex + 1)][colIndex] == 2;
					
					if (isBelowCellOnPath) {
						currentCell.setDown(CellComponents.APERTURE);
					}
					
					if (!isCurrentCellOnPath && !isBelowCellOnPath) {
						boolean randomBool = random.nextBoolean();
						
						if (randomBool) {
							belowCell.setUp(currentCell.getDown());
						} else {
							currentCell.setDown(belowCell.getUp());
						}
					}
				}
				
				if (rightCell != null && currentCell.getRight() != rightCell.getLeft()) {
					if (isCurrentCellOnPath) {
						rightCell.setLeft(CellComponents.APERTURE);
					}
					
					boolean isRightCellOnPath = pathGrid[rowIndex][Math.min(n - 1, colIndex + 1)] == 1 || pathGrid[rowIndex][Math.min(n - 1, colIndex + 1)] == 2;
					
					if (isRightCellOnPath) {
						currentCell.setRight(CellComponents.APERTURE);
					}
					
					if (!isCurrentCellOnPath && !isRightCellOnPath) {
						boolean randomBool = random.nextBoolean();
						
						if (randomBool) {
							rightCell.setLeft(currentCell.getRight());
						} else {
							currentCell.setRight(rightCell.getLeft());
						}
					}
				}
			}
		}
				
		this.setGrid(new Grid(rows));
		
		return this.grid;
	}
	
	@Override
	public String toString() {
		return "Game [grid=" + grid.toString() + "]";
	}
}