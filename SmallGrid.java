/* Name: Rohan Bhanot 
 * Starting Date: Friday, April 26, 2024
 * Ending Date: Wednesday, June 12, 2024
 * Teacher: Elena Kapustina
 * Course Code: ICS4U1-2
 * Program Name: SmallGrid
 * Description: Creating the SmallGrid class for my Battleship Culminating Project.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SmallGrid extends JPanel {
	private static final long serialVersionUID = 1L; // Serial version UID for serialization
	private Object[][] array; // 2D array to hold the grid data
	private BufferedImage gridImage; // Image of the grid
	public static final int X_ORIGIN = 23; // X coordinate of the top left
	public static final int Y_ORIGIN = 39; // Y coordinate of the top left
	public static final int TILE_SIZE = 20; // Size of the tile spaces
	public static final int BORDER_SIZE = 3; // Size of the border between tiles
	public static final int PIECE_SIZE = 18; // Size of the pieces that appear

	/*
	 * Purpose: Constructor that initializes the SmallGrid with a 2D array and loads the grid image.
	 * PRE: Object[][] array
	 * POST: None
	 */
	public SmallGrid(Object[][] array) {
		this.array = array; // Initialize the array with the provided 2D array

		// Set the background color to white
		setBackground(Color.WHITE);
		
		// Set the preferred size of the panel based on the grid size
		setPreferredSize(new Dimension((X_ORIGIN + 2 + (TILE_SIZE + BORDER_SIZE) * array.length),
				Y_ORIGIN + 2 + ((TILE_SIZE + BORDER_SIZE) * array.length)));
		
		// Set the actual size of the panel to the preferred size
		setSize(getPreferredSize());

		// Load the grid image file
		try {
			gridImage = ImageIO.read(new File("gridSmallLabels.png"));
		} catch (IOException e) {
			System.out.println("Failed to load image"); // Print an error message if the image fails to load
		}
	}

	/*
	 * Purpose: Paints the grid and the ship pieces on the panel.
	 * PRE: Graphics g
	 * POST: None
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call the superclass method to ensure proper painting
		Graphics2D g2 = (Graphics2D) g; // Cast the Graphics object to Graphics2D for 2D drawing
		g2.drawImage(gridImage, 0, 15, this); // Draw the grid image on the panel

		// Loop through the array to draw ship pieces
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				// If there is a ship piece at the location
				if ((array[i][j]).getClass().getName().equals("ShipPiece")) {
					// Draw the image of the ship piece at the proper grid location
					g2.drawImage(((ShipPiece) array[i][j]).getShipImage(),
							X_ORIGIN + 2 + ((TILE_SIZE + BORDER_SIZE) * i) + BORDER_SIZE / 2,
							Y_ORIGIN + 2 + ((TILE_SIZE + BORDER_SIZE) * j) + BORDER_SIZE / 2, PIECE_SIZE, PIECE_SIZE,
							this);
				}
			}
		}
	}

	/*
	 * Purpose: Returns the 2D array of the grid.
	 * PRE: None
	 * POST: array
	 */
	public Object[][] getArray() {
		return array; // Return the 2D array of the grid
	}

	/*
	 * Purpose: Sets the 2D array of the grid.
	 * PRE: Object[][] array
	 * POST: None
	 */
	public void setArray(Object[][] array) {
		this.array = array; // Set the 2D array of the grid
	}
}
