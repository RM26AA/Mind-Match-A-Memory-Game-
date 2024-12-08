/**
 * IntroGUI Class
 * -
 * Description: This class displays the introductory screen for the game with options to
 * start the game, view help, or quit the application. Each button has a dedicated action:
 * - "Play" launches the game (MatchCards).
 * - "Help" opens a help dialog with instructions.
 * - "Quit" prompts the user to exit the game.
 * -
 * Version: [V4]
 * Author: [Romeo Maunick - RM]
 * Date: [08/12/2024]       //change to current date
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroGUI {

    // Main window for the intro screen
    JFrame frame = new JFrame("Mind Match: A Memory Game");
    JButton playButton = new JButton("Play");
    JButton helpButton = new JButton("Help");

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

        // Help button setup - displays help dialog
        helpButton.setFont(new Font("Arial", Font.PLAIN, 18));
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelpDialog();  // Call method to show instructions
            }
        });

        // Add buttons and spacing to center panel
        centerPanel.add(playButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Space between buttons
        centerPanel.add(helpButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Space between buttons

        // Add center panel to the frame and set visibility
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Displays a help dialog with instructions for the game.
     * Activated when the user clicks the "Help" button.
     */
    private void showHelpDialog() {
        // Help dialog window setup
        JFrame helpFrame = new JFrame("Help");
        helpFrame.setSize(610, 300);
        helpFrame.setLocationRelativeTo(frame);  // Center relative to the main frame
        //helpFrame.setLocationRelativeTo(helpFrame);
        helpFrame.setLayout(new BorderLayout());

        // Help title setup
        JLabel helpTitleLabel = new JLabel("Need some assistance?", JLabel.CENTER);
        helpTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        helpFrame.add(helpTitleLabel, BorderLayout.NORTH);

        // Help content text area with instructions
        JTextArea helpText = new JTextArea(
                "Instructions for Mind Match:" +
                        "\n1. To play this game, click the 'Play' button." +
                        "\n2. A grid of cards will display, initially showing only the back of each card." +
                        "\n3. To reveal a card, click on it." +
                        "\n4. Find and match all pairs by remembering each card's location." +
                        "\n5. The number of errors is displayed at the top of the screen." +
                        "\nFor further assistance, please contact askherts.ac.uk." +
                        "\nThank you!"
        );
        helpText.setEditable(false);
        helpText.setFont(new Font("Arial", Font.PLAIN, 16));
        helpFrame.add(new JScrollPane(helpText), BorderLayout.CENTER);

        helpFrame.setVisible(true);
    }
}





