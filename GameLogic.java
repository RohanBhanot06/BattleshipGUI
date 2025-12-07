/* Name: Rohan Bhanot 
 * Starting Date: Friday, April 26, 2024
 * Ending Date: Wednesday, June 12, 2024
 * Teacher: Elena Kapustina
 * Course Code: ICS4U1-2
 * Program Name: GameLogic
 * Description: Creating the GameLogic class for my Battleship Culminating Project.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameLogic {

	// Defining the board size as 10x10
	public static int boardSize = 10;

	// Defining the sizes of different types of ships
	public static int battleshipSize = 4;
	public static int cruiserSize = 3;
	public static int destroyerSize = 2;
	public static int submarineSize = 1;

	// Defining the count of different types of ships
	public static int battleshipCount = 1;
	public static int cruiserCount = 2;
	public static int destroyerCount = 3;
	public static int submarineCount = 4;

	private JFrame frame; // JFrame for the main window
	private boolean gameRunning; // Boolean to check if the game is running

	/*
	 * Purpose: Sets up the main window for the game.
	 * PRE: None
	 * POST: None
	 */
	public void setUpWindow() {
		frame = new JFrame(); // Creating a new JFrame instance

		frame.getContentPane().setLayout(null); // Setting layout to null
		frame.getContentPane().setBackground(Color.WHITE); // Setting background color to white
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting default close operation
		frame.setPreferredSize(new Dimension(900, 615)); // Setting preferred size of the window
		frame.setMinimumSize(new Dimension(900, 615)); // Setting minimum size of the window
		frame.setResizable(false); // Disabling window resizing
		frame.pack(); // Packing the frame to fit the components
		startGame(); // Starting the game
	}

	/*
	 * Purpose: Starts the game and sets up the initial game components.
	 * PRE: None
	 * POST: None
	 */
	public void startGame() {
		gameRunning = true; // Setting gameRunning to true

		MainMenu startMenu = new MainMenu(frame); // Creating MainMenu instance
		startMenu.loadTitleScreen(); // Loading the title screen
		while (startMenu.isImageVisible()) {
		} // Waiting until the title screen is visible

		Ship[] p1Ships = initializeShipCreation(true); // Initializing ships for player 1
		Ship[] p2Ships = initializeShipCreation(false); // Initializing ships for player 2

		Grid grid = new Grid(chooseShipPositions(p1Ships)); // Creating grid for player 1
		SmallGrid small = new SmallGrid(chooseShipPositions(p2Ships)); // Creating small grid for player 2
		small.setLocation(grid.getWidth() + 10, grid.getY()); // Setting location of the small grid

		// panel.setLayout(null); // Commented out unused code

		int windowWidth = small.getX() + small.getWidth() + 10; // Calculating window width
		frame.setPreferredSize(new Dimension(windowWidth, frame.getHeight())); // Setting preferred size
		frame.setSize(frame.getPreferredSize()); // Setting size of the frame
		frame.pack(); // Packing the frame

		frame.getContentPane().add(grid); // Adding grid to the window
		frame.getContentPane().add(small); // Adding small grid to the window
		frame.addMouseListener(grid); // Adding mouse listener to the frame
		frame.setVisible(true); // Making the frame visible

		gameLoop(p1Ships, p2Ships, grid, small); // Starting the game loop
	}

	/*
	 * Purpose: Initializes ship creation for the players.
	 * PRE: boolean isPlayerOne
	 * POST: Ship[] (array of ships)
	 */
	private Ship[] initializeShipCreation(boolean isPlayerOne) {
		Ship[] battleships = createShips(battleshipSize, battleshipCount, isPlayerOne); // Creating battleships
		Ship[] cruisers = createShips(cruiserSize, cruiserCount, isPlayerOne); // Creating cruisers
		Ship[] destroyers = createShips(destroyerSize, destroyerCount, isPlayerOne); // Creating destroyers
		Ship[] submarines = createShips(submarineSize, submarineCount, isPlayerOne); // Creating submarines

		Ship[] ships = concatShipArray(battleships, cruisers); // Concatenating battleships and cruisers
		ships = concatShipArray(ships, destroyers); // Concatenating with destroyers
		ships = concatShipArray(ships, submarines); // Concatenating with submarines

		return ships; // Returning the array of ships
	}

	/*
	 * Purpose: Creates ships of a specific size and count.
	 * PRE: int shipSize, int numOfShips, boolean isPlayerOne
	 * POST: Ship[] (array of ships)
	 */
	private Ship[] createShips(int shipSize, int numOfShips, boolean isPlayerOne) {
		Ship[] listOfShips = new Ship[numOfShips]; // Creating an array to hold the ships
		for (int i = 0; i < numOfShips; i++) {
			ShipPiece[] shipArray = new ShipPiece[shipSize]; // Creating an array to hold ship pieces
			for (int j = 0; j < shipSize; j++) {
				ShipPiece p = new ShipPiece(isPlayerOne); // Creating a ship piece
				shipArray[j] = p; // Adding the piece to the array
			}
			listOfShips[i] = new Ship(shipArray); // Creating a ship with the ship pieces
		}

		return listOfShips; // Returning the array of ships
	}

	/*
	 * Purpose: Concatenates two arrays of ships into one.
	 * PRE: Ship[] a, Ship[] b
	 * POST: Ship[] (concatenated array of ships)
	 */
	private Ship[] concatShipArray(Ship[] a, Ship[] b) {
		int aLen = a.length; // Length of first array
		int bLen = b.length; // Length of second array
		Ship[] c = new Ship[aLen + bLen]; // Creating a new array to hold both arrays
		System.arraycopy(a, 0, c, 0, aLen); // Copying first array into new array
		System.arraycopy(b, 0, c, aLen, bLen); // Copying second array into new array
		return c; // Returning the concatenated array
	}

	/*
	 * Purpose: Chooses positions for the ships on the grid.
	 * PRE: Ship[] ships
	 * POST: Object[][] (grid with ship positions)
	 */
	private Object[][] chooseShipPositions(Ship[] ships) {
		GridCreator creator = new GridCreator(ships, boardSize, frame); // Creating a GridCreator instance
		creator.setup(); // Setting up the grid
		frame.getContentPane().add(creator); // Adding creator to the frame
		frame.getContentPane().repaint(); // Repainting the frame
		frame.setVisible(true); // Making the frame visible
		while (!creator.isSetupOver()) {
		} // Waiting until setup is over
		frame.getContentPane().removeAll(); // Removing all components from the frame
		frame.getContentPane().revalidate(); // Revalidating the frame
		frame.getContentPane().repaint(); // Repainting the frame

		return creator.getGridArray(); // Returning the grid array
	}

	/*
	 * Purpose: Handles actions between turns in the game.
	 * PRE: Grid grid, SmallGrid small
	 * POST: None
	 */
	private void betweenTurns(Grid grid, SmallGrid small) {
		frame.addMouseListener(new MouseAdapter() { // Adding a mouse listener
			@Override
			public void mousePressed(MouseEvent e) {
				BetweenTurnsScreen betweenTurns = new BetweenTurnsScreen((JPanel) frame.getContentPane(), grid, small); // Creating BetweenTurnsScreen instance
				final Object[][] grid1Temp = grid.getArray(); // Storing player 1's grid
				final Object[][] grid2Temp = small.getArray(); // Storing player 2's grid
				if (!grid.isTurn() && gameRunning) { // If it's not player 1's turn and the game is running
					grid.setVisible(false); // Hide grid
					small.setVisible(false); // Hide small grid
					grid.setArray(grid2Temp); // Swap grids
					small.setArray(grid1Temp); // Swap small grids
					betweenTurns.loadTurnScreen(); // Load turn screen
				}
			}
		});
	}

	/*
	 * Purpose: Main game loop that runs until the game is over.
	 * PRE: Ship[] p1Ships, Ship[] p2Ships, Grid grid, SmallGrid small
	 * POST: None
	 */
	private void gameLoop(Ship[] p1Ships, Ship[] p2Ships, Grid grid, SmallGrid small) {
		betweenTurns(grid, small); // Set up actions between turns

		while (gameRunning) { // Run while the game is running

			boolean p1AllShipsDead = true; // Assume all player 1 ships are dead

			for (int i = 0; i < p1Ships.length; i++) { // Check each player 1 ship
				if (p1Ships[i].checkIfDead()) { // If the ship is dead
					for (int j = 0; j < p1Ships[i].getShipPieces().length; j++) // Update ship pieces
						p1Ships[i].getShipPieces()[j].setShipImage("dead.png");
				} else {
					p1AllShipsDead = false; // If at least one ship is not dead
				}
			}

			boolean p2AllShipsDead = true; // Assume all player 2 ships are dead

			grid.repaint(); // Repaint grid
			small.repaint(); // Repaint small grid

			for (int i = 0; i < p2Ships.length; i++) { // Check each player 2 ship
				if (p2Ships[i].checkIfDead()) { // If the ship is dead
					for (int j = 0; j < p2Ships[i].getShipPieces().length; j++) // Update ship pieces
						p2Ships[i].getShipPieces()[j].setShipImage("dead.png");
				} else {
					p2AllShipsDead = false; // If at least one ship is not dead
				}
			}

			grid.repaint(); // Repaint grid
			small.repaint(); // Repaint small grid

			if (p1AllShipsDead || p2AllShipsDead) { // If all ships of either player are dead
				gameRunning = false; // Stop the game
				for (int i = 0; i < grid.getArray().length; i++) {
					for (int j = 0; j < grid.getArray()[i].length; j++) {
						if ((grid.getArray()[i][j].equals((Object) 1))) { // Reset grid state
							grid.getArray()[i][j] = (Object) 0;
						}
					}
				}

				GameOverScreen gameOver = new GameOverScreen(frame, !p1AllShipsDead); // Create GameOverScreen instance
				gameOver.loadEndScreen(); // Load end screen
			}
		}

	}
}
