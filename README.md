# Battleship GUI (Java)

Two-player Battleship game built in Java with a custom Swing GUI. Players configure the board and ships, then take turns firing on each other’s grid with clear hit/miss feedback and a final win screen.

---

## Features

- **Main menu**
  - Configure board size
  - Choose ship sizes and counts
  - View instructions before starting

- **Two-player hot-seat gameplay**
  - Both players share one computer
  - Between-turns screen so players can swap without seeing each other’s boards

- **Visual feedback**
  - Boards and labels rendered with image assets
  - Different images for hits, sunk ship pieces, and win screens

- **Game flow**
  - Setup → Player turns (with swaps) → Game over screen showing the winner

---

## Project Structure

Some of the main classes:

- `LaunchGame.java` – Entry point of the application (contains `main`).
- `GameLogic.java` – Central controller that manages windows, screens, and overall game state.
- `MainMenu.java` – Builds the main menu GUI, handles configuration and instructions.
- `Grid.java` / `SmallGrid.java` – Represent the players’ grids and handle display/interaction.
- `GridCreator.java` – Helps create and initialize grid objects.
- `Ship.java` / `ShipPiece.java` – Represent ships and their segments, track hits and destruction.
- `BetweenTurnsScreen.java` – Screen shown between turns so players can swap.
- `GameOverScreen.java` – Displays the winner and final state.

Image assets (e.g., `mainMenu.png`, `instructions.png`, `gridLabels.png`, `player1.png`, `player2.png`, `player1Hit.png`, `player2Hit.png`, `dead.png`, `nextTurn.png`, `player1Win.png`, `player2Win.png`) are used to render the UI and game state.

---

## How to Run

1. **Install Java**
   - Make sure you have a Java JDK (e.g., Java 8 or later) installed.

2. **Clone the repository**
   ```bash
   git clone https://github.com/RohanBhanot06/BattleshipGUI.git
   cd BattleshipGUI
