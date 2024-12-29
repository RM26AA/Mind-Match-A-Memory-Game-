# Lizard Ball Z Match Cards Game

## Developer
R.Maunick
- 29/11/2024

## Description
Lizard Ball Z Match Cards is a simple memory-matching game developed in Java using the Swing library. The objective of the game is to match pairs of cards while minimizing the number of errors. It features an intuitive graphical interface with an introductory menu, a grid-based game board, and a game-over popup with options to restart or return to the main menu.

## Features
- **Introductory Menu**: 
  - Play the game
  - View help/instructions
  - Quit the application
- **Memory-Matching Gameplay**:
  - Click cards to reveal their images.
  - Match all pairs to win the game.
  - Tracks the number of errors.
- **Game Over Popup**:
  - Displays the number of errors and a "Well Done!" message.
  - Includes options to restart or return to the main menu.
- **Restart Button**:
  - Resets the game with shuffled cards and a fresh board.
- **Help Section**:
  - Provides instructions for how to play the game.
- **Customizable UI**:
  - Displays character images from the "Lizard Ball Z" theme.

## Technologies Used
- **Java**: Core programming language for the game logic and GUI.
- **Swing**: For creating the graphical user interface.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher.
- A Java IDE such as IntelliJ IDEA, Eclipse, or NetBeans (optional).

### Installation
1. Clone or download this repository.
2. Open the project in your preferred Java IDE.
3. Ensure the `img2` folder (containing card images) is in the correct relative path specified in the code.

### Running the Game
1. Locate the `App` class in the `src` directory.
2. Run the `App` class to start the game.


## How to Play
1. Launch the game.
2. Use the **Play** button to start the memory-matching game.
3. Click on cards to reveal their images.
4. Match pairs of cards until all pairs are found.
5. Avoid errors and try to complete the game with the fewest mismatches.
6. At the end, view your errors and score in the game-over popup.

## Testing
### GameTester Class
- Simulates user actions like button clicks, card flips, and game-over scenarios.
- Includes stress testing by simulating rapid clicks.
- Validates randomization by verifying shuffled card positions.
- To run the tests, execute the `GameTester` class.

## Known Issues
- If card images are missing or paths are incorrect, the game will not display the cards properly. Ensure the `img2` folder is correctly configured.

## Future Enhancements
- Add difficulty levels with varying grid sizes (e.g., 4x4, 6x6).
- Include customizable themes or card sets.
- Introduce a timer to track how quickly the game is completed.
- Add sound effects and background music.
- Add accessibility options like changing the font style/size.
- Add a scoreboard.

## Author
**Romeo Maunick**  
Version: V10.1  
Date: 29/12/2024

## License
This project is licensed under the MIT License. Feel free to use, modify, and distribute this code for educational purposes.

---

Happy Playing! 🎮

