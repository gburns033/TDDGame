package hw4.game;

import java.util.ArrayList;
import java.util.Scanner;

import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.player.Movement;
import hw4.player.Player;

public class Play {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;

        do {
            System.out.println("Enter size of the grid: ");
            size = scanner.nextInt();
            if (size < 3 || size > 7) {
                System.out.println("The size you entered was invalid.");
            }
        } while (size < 3 || size > 7);

        Game game = new Game(size);
        Player player = new Player(
            game.getGrid().getRowAt(size - 1),
            game.getGrid().getRowAt(size - 1).getCellAt(size - 1)
        );

        Row exitRow = null;
        Cell exitCell = null;
        boolean playing = true;

        while (playing) {
            printGame(game, player, exitRow, exitCell);

            System.out.println("Enter your next move (1 for up, 2 for down, 3 for left, 4 for right): ");
            int move = scanner.nextInt();

            boolean isMoveValid = moveCheck(move, game, player);

            if (!isMoveValid) {
                System.out.println("You cannot move there.");
            } else if (player.getCurrentCell().getLeft() == CellComponents.EXIT) {
                printGame(game, player, exitRow, exitCell);

                System.out.println("Enter your next move (1 for up, 2 for down, 3 for left, 4 for right): ");
                move = scanner.nextInt();

                isMoveValid = moveCheck(move, game, player);

                if (move == 3) { // 3 = left
                    System.out.println("You escaped the maze!");
                    playing = false;
                }
            }
        }

        scanner.close();
    }

    public static boolean moveCheck(int move, Game game, Player player) {
        switch (move) {
            case 1:
                return game.play(Movement.UP, player);
            case 2:
                return game.play(Movement.DOWN, player);
            case 3:
                return game.play(Movement.LEFT, player);
            case 4:
                return game.play(Movement.RIGHT, player);
            default:
                return false;
        }
    }

    public static void printGame(Game game, Player player, Row exitRow, Cell exitCell) {
        Grid grid = game.getGrid();
        ArrayList<Row> rows = grid.getRows();

        for (int i = 0; i < rows.size(); i++) {
            boolean startsWithExit = checkForExit(grid.getRowAt(i).getCells().get(0));

            if (startsWithExit) {
                exitRow = grid.getRowAt(i);
                exitCell = grid.getRowAt(i).getCells().get(0);
                System.out.print("E ");
            }

            int startJ = startsWithExit ? 1 : 0;

            for (int j = startJ; j < grid.getRowAt(i).getRowSize(); j++) {
                if (rows.get(i).equals(player.getCurrentRow())
                        && grid.getRowAt(i).getCellAt(j).equals(player.getCurrentCell())) {
                    System.out.print("A ");
                } else {
                    System.out.print("S ");
                }
            }

            System.out.println();
        }
    }

    public static boolean checkForExit(Cell cell) {
        return cell.getUp() == CellComponents.EXIT
            || cell.getDown() == CellComponents.EXIT
            || cell.getLeft() == CellComponents.EXIT
            || cell.getRight() == CellComponents.EXIT;
    }
}
