/**
 * IntroGUI Class
 * -
 * Description: This class displays the introductory screen for the game with options to
 * start the game, view help, or quit the application. Each button has a dedicated action:
 * - "Play" launches the game (MatchCards).
 * - "Help" opens a help dialog with instructions.
 * - "Quit" prompts the user to exit the game.
 * -
 * Version: [V3]
 * Author: [Romeo Maunick - RM]
 * Date: [04/12/2024]   //change to current date
 */

import javax.swing.*;
import java.awt.*;


public class IntroGUI {

    // Main window for the intro screen
    JFrame frame = new JFrame("Mind Match: A Memory Game");
    JButton playButton = new JButton("Play");

    /**
     * Constructor for IntroGUI. Sets up the main window, configures buttons,
     * and displays an image. Each button is assigned an ActionListener.
     */
    public IntroGUI() {
        // Frame settings
        frame.setSize(350, 460);
        frame.setLocationRelativeTo(null);  // Center the window on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title label setup
        JLabel titleLabel = new JLabel("Lizard Ball Z Edition", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Center panel for buttons and image
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Play button setup - launches the game
        playButton.setFont(new Font("Arial", Font.PLAIN, 18));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons and spacing to center panel
        centerPanel.add(playButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Space between buttons

        // Add center panel to the frame and set visibility
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}





