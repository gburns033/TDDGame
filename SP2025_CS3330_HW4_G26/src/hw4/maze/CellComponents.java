package hw4.maze;

/**
 * Represents the possible components that can exist on each side of a cell in the maze.
 *
 * Each side of a cell can be one of the following:
 * - WALL: A solid barrier that the player cannot pass through.
 * - APERTURE: An open passage that the player can move through.
 * - EXIT: A passage that leads out of the maze.
 */
public enum CellComponents {
    WALL,
    APERTURE,
    EXIT,
}
