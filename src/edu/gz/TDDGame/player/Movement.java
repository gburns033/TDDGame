package edu.gz.TDDGame.player;

/**
 * Represents movement directions for the player in the maze.
 */
public enum Movement {
	/** Move upward in the maze (toward a higher row index). */
	UP, 

	/** Move to the right in the maze (toward a higher column index). */
	RIGHT, 

	/** Move downward in the maze (toward a lower row index). */
	DOWN, 

	/** Move to the left in the maze (toward a lower column index). */
	LEFT;
}
