package hw4.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.player.Movement;
import hw4.player.Player;

/**
 * The {@code Game} class represents the logic and state of a maze game. It
 * manages the game grid, supports movement of the player, and allows creation
 * of random maze configurations with a valid path from right to left.
 */
public class Game {
	private Grid grid;

	private static Random random = new Random();

	/**
	 * Constructs a {@code Game} instance with a predefined {@code Grid}.
	 *
	 * @param grid the game grid
	 */
	public Game(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Constructs a {@code Game} instance with a specified grid size.
	 *
	 * @param n the dimension of the grid (n x n)
	 */
	public Game(int n) {
		this.grid = createRandomGrid(n);
	}

	/**
	 * Returns the current game grid.
	 *
	 * @return the grid
	 */
	public Grid getGrid() {
		return this.grid;
	}

	/**
	 * Executes a movement command for the given player.
	 *
	 * @param movement the direction to move (UP, DOWN, LEFT, RIGHT)
	 * @param player   the player object to move
	 * @return {@code true} if the move was successful; {@code false} otherwise
	 */
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

			if (rowIndex != null && rowIndex > 0 && cell.getUp() != CellComponents.WALL
					&& targetCell.getDown() != CellComponents.WALL) {
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

			if (rowIndex != null && rowIndex < row.getRowSize() && cell.getDown() != CellComponents.WALL
					&& targetCell.getUp() != CellComponents.WALL) {
				player.setCurrentRow(targetRow);
				player.setCurrentCell(targetCell);

				return true;
			}

			return false;
		case LEFT:
			if (colIndex == 0 && cell.getLeft() == CellComponents.EXIT) {
				return true;
			}

			if (colIndex - 1 < 0) {
				return false;
			}

			targetCell = row.getCellAt(colIndex - 1);

			if (colIndex >= 0 && cell.getLeft() != CellComponents.WALL
					&& targetCell.getRight() != CellComponents.WALL) {
				player.setCurrentCell(targetCell);

				return true;
			}

			return false;
		case RIGHT:
			if (colIndex + 1 >= row.getRowSize()) {
				return false;
			}

			targetCell = row.getCellAt(colIndex + 1);

			if (colIndex < row.getCells().size() && cell.getRight() != CellComponents.WALL
					&& targetCell.getLeft() != CellComponents.WALL) {
				player.setCurrentCell(targetCell);

				return true;
			}

			return false;
		default:
			return false;
		}
	}

	/**
	 * Sets the current grid.
	 *
	 * @param grid the new grid to set
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Creates a random {@code Cell} with one guaranteed aperture and the remaining
	 * sides randomly assigned as either WALL or APERTURE.
	 *
	 * @return a randomly generated {@code Cell}
	 */
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

	/**
	 * Randomly selects a non-exit {@code CellComponent}, either WALL or APERTURE.
	 *
	 * @return a {@code CellComponent} that is not EXIT
	 */
	private static CellComponents getRandomNonExitCellComponent() {
		return random.nextBoolean() ? CellComponents.APERTURE : CellComponents.WALL;
	}

	/**
	 * Returns a valid neighboring step from the current position in the path grid,
	 * ensuring that the new step has not been visited before and is a valid step in
	 * the grid.
	 *
	 * @param currentStep the current position as an int[2] array {row, col}
	 * @param pathGrid    the grid used to track path positions
	 * @param visited     a boolean 2D array indicating which cells have already
	 *                    been visited.
	 * @return an int[2] array representing the next valid step {row, col}. If no
	 *         valid step is found, it returns the current step as the default to
	 *         indicate no progress made.
	 */
	private static int[] getValidNextStep(int[] currentStep, int[][] pathGrid, boolean[][] visited) {
		int[] step = new int[2];
		boolean foundValidStep = false;

		for (int i = 0; i < 20; i++) {
			step[0] = random.nextInt(Math.max(0, currentStep[0] - 1), Math.min(pathGrid.length, currentStep[0] + 2));
			step[1] = random.nextInt(Math.max(0, currentStep[1] - 1), Math.min(pathGrid[0].length, currentStep[1] + 2));

			if (visited[step[0]][step[1]]) {
				continue;
			}

			if (isValidNextStep(step, pathGrid)) {
				visited[step[0]][step[1]] = true;
				foundValidStep = true;
				break;
			}
		}

		if (!foundValidStep) {
			return currentStep;
		}

		return step;
	}

	/**
	 * Checks whether a given step is valid in the path grid.
	 *
	 * @param step     the position to check
	 * @param pathGrid the path grid
	 * @return {@code true} if the step is valid; {@code false} otherwise
	 */
	private static boolean isValidNextStep(int[] step, int[][] pathGrid) {
		if (pathGrid[step[0]][step[1]] == 1) {
			return false;
		}

		return true;
	}

	/**
	 * Generates a new random maze {@code Grid} with a guaranteed path from the
	 * right side of the grid to the left side. Each cell in the path is an
	 * aperture, and the exit is placed on the leftmost side.
	 *
	 * @param n the size of the grid (must be between 3 and 7 inclusive)
	 * @return a {@code Grid} with a valid path, or {@code null} if input is invalid
	 */
	public Grid createRandomGrid(int n) {
		if (n < 3 || n > 7) {
			return null;
		}

		int[][] pathGrid = new int[n][n];

		int[] currentStep = { random.nextInt(n), n - 1 };

		boolean[][] visited = new boolean[n][n];
		visited[currentStep[0]][currentStep[1]] = true;

		Stack<int[]> pathStack = new Stack<>();
		pathStack.push(currentStep);

		while (!pathStack.isEmpty()) {
			currentStep = pathStack.peek();
			pathGrid[currentStep[0]][currentStep[1]] = 1;

			if (currentStep[1] == 0) {
				pathGrid[currentStep[0]][currentStep[1]] = 2;
				break;
			}

			int[] nextStep = getValidNextStep(currentStep, pathGrid, visited);

			if (nextStep[0] == currentStep[0] && nextStep[1] == currentStep[1]) {
				pathStack.pop();
			} else {
				pathStack.push(nextStep);
				visited[nextStep[0]][nextStep[1]] = true;
			}
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
					cells.add(new Cell(CellComponents.APERTURE, CellComponents.APERTURE, CellComponents.APERTURE,
							CellComponents.APERTURE));
					continue;
				case 2:
					cells.add(new Cell(CellComponents.EXIT, CellComponents.APERTURE, CellComponents.APERTURE,
							CellComponents.APERTURE));
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

					boolean isBelowCellOnPath = pathGrid[Math.min(n - 1, rowIndex + 1)][colIndex] == 1
							|| pathGrid[Math.min(n - 1, rowIndex + 1)][colIndex] == 2;

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

					boolean isRightCellOnPath = pathGrid[rowIndex][Math.min(n - 1, colIndex + 1)] == 1
							|| pathGrid[rowIndex][Math.min(n - 1, colIndex + 1)] == 2;

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

	/**
	 * Returns a string representation of the game.
	 *
	 * @return a string containing the grid representation
	 */
	@Override
	public String toString() {
		return "Game [grid=" + grid.toString() + "]";
	}
}