import javax.swing.*;
import java.awt.*;

public class IntroGUI {

    // Main window for the intro screen
    JFrame frame = new JFrame("Mind Match: A Memory Game");

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

        // Add center panel to the frame and set visibility
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}





