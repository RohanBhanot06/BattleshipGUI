/* Name: Rohan Bhanot 
 * Starting Date: Friday, April 26, 2024
 * Ending Date: Wednesday, June 12, 2024
 * Teacher: Elena Kapustina
 * Course Code: ICS4U1-2
 * Program Name: GameOverScreen
 * Description: Creating the GameOverScreen class for my Battleship Culminating Project.
 */

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverScreen implements MouseListener {

	private JPanel window; // JPanel for the window
	private ImageIcon backgroundImageIcon; // ImageIcon for the background image
	private JLabel bkgImageContainer; // JLabel for displaying the background image

	/*
	 * Purpose: Constructor for initializing the GameOverScreen with a JFrame and win status.
	 * PRE: JFrame app, boolean playerOneWin
	 * POST: None
	 */
	public GameOverScreen(JFrame app, boolean playerOneWin) {
		window = (JPanel) app.getContentPane(); // Get the content pane of the JFrame and cast it to JPanel
		if (playerOneWin) { // If player one wins
			backgroundImageIcon = new ImageIcon("player1Win.png"); // Load the player one win image icon
		} else { // If player two wins
			backgroundImageIcon = new ImageIcon("player2Win.png"); // Load the player two win image icon
		}
		Image bkgImage = backgroundImageIcon.getImage(); // Get the image from the icon
		Image scaledBkgImage = bkgImage.getScaledInstance(window.getWidth(), window.getHeight(),
				BufferedImage.SCALE_FAST); // Scale the image to fit the window size
		ImageIcon scaledBkgImageIcon = new ImageIcon(scaledBkgImage); // Create a new ImageIcon from the scaled image
		bkgImageContainer = new JLabel(scaledBkgImageIcon); // Create a JLabel with the scaled image icon
		bkgImageContainer.setSize(window.getWidth(), window.getHeight()); // Set the size of the JLabel to the window size
		bkgImageContainer.setLocation(0, 0); // Set the location of the JLabel to (0, 0)
		bkgImageContainer.setBackground(new Color(0, 0, 0, 0)); // Set the background color of the JLabel to transparent
		bkgImageContainer.addMouseListener(this); // Add a mouse listener to the background image container
	}

	/*
	 * Purpose: Loads and displays the end screen with the background image.
	 * PRE: None
	 * POST: None
	 */
	public void loadEndScreen() {
		window.add(bkgImageContainer); // Add the background image container to the window
		window.setComponentZOrder(bkgImageContainer, 0); // Set the Z-order of the background image container to the lowest value
		window.setVisible(true); // Make the window visible
		window.revalidate(); // Revalidate the window to update the layout
		window.repaint(); // Repaint the window to update the display
	}

	/*
	 * Purpose: Handles the mouseReleased event to exit the application.
	 * PRE: MouseEvent e
	 * POST: None
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		System.exit(0); // Exit the application
	}

	// Empty method for the mouseClicked event, required by the MouseListener interface
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	// Empty method for the mouseEntered event, required by the MouseListener interface
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	// Empty method for the mouseExited event, required by the MouseListener interface
	@Override
	public void mouseExited(MouseEvent e) {
	}

	// Empty method for the mousePressed event, required by the MouseListener interface
	@Override
	public void mousePressed(MouseEvent e) {
	}
}
