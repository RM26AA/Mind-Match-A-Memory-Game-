/**
 * MatchCards Class
 * -
 * Description: The main game logic for the memory-matching card game. This class is responsible
 * for setting up the game board, shuffling and displaying the cards, and handling user interactions.
 * The game tracks and displays the player's error count and provides a restart option.
 * -
 * Version: [V8]
 * Author: [Romeo Maunick - RM]
 * Date: [23/12/2024]       //change to current date
 */

import java.awt.*;      // GUI components
import java.awt.event.*; // Event handling
import java.util.ArrayList; // Store cards
import javax.swing.*;    // GUI components

public class MatchCards {

    // Array of card names used to generate the deck
    String[] cardList = {
            "beerus1", "broly1", "cell-max1", "frieza1", "gohan1",
            "goku1", "jiren1", "marjinBuu1", "piccolo1", "vegeta1"
    };

    // Game settings for grid layout and card dimensions
    int rows = 4;
    int columns = 5;
    int cardWidth = 180;
    int cardHeight = 220;

    // Deck of cards for the game and back image for face-down cards
    ArrayList<Card> cardSet;
    ImageIcon cardBackImageIcon;

    // Calculated board dimensions based on grid settings
    int boardWidth = columns * cardWidth;
    int boardHeight = rows * cardHeight;

    // GUI components
    JFrame frame = new JFrame("LizardBallZ Match Cards");
    JLabel textLabel = new JLabel();
    JPanel textpanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel restartGamePanel = new JPanel();
    JButton restartButton = new JButton();

    // Game state variables
    int errorCount = 0;
    ArrayList<JButton> board;     // Stores buttons for each card
    Timer hideCardTimer;          // Timer to delay hiding unmatched cards
    boolean gameReady = false;    // Tracks if the game is ready for player interaction
    JButton card1Selected;        // First selected card
    JButton card2Selected;        // Second selected card

    /**
     * Constructor for MatchCards. Initializes the game setup, shuffles the deck,
     * sets up the GUI layout, and prepares game components.
     */
    public MatchCards() {
        setupCards();  // Initialize the card deck
        shuffleCards(); // Shuffle the cards for random order

        // Configure frame settings
        frame.setLayout(new BorderLayout());
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);  // Center the window on screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup error display at the top
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Errors: " + errorCount);
        textpanel.setPreferredSize(new Dimension(boardWidth, 30));
        textpanel.add(textLabel);
        frame.add(textpanel, BorderLayout.NORTH);

        // Initialize the card game board
        board = new ArrayList<>();
        boardPanel.setLayout(new GridLayout(rows, columns));
        for (int i = 0; i < cardSet.size(); i++) {  // Create button for each card
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(cardWidth, cardHeight));
            tile.setOpaque(true);
            tile.setIcon(cardSet.get(i).cardImageIcon); // Set card image icon
            tile.setFocusable(false);

            // Add ActionListener for each card button
            tile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCardClick((JButton) e.getSource());
                }
            });
            board.add(tile);
            boardPanel.add(tile);
        }
        frame.add(boardPanel);

        // Restart button setup
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        restartButton.setText("Restart Game");
        restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        restartButton.setFocusable(false);
        restartButton.setEnabled(false);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        restartGamePanel.add(restartButton);
        frame.add(restartGamePanel, BorderLayout.SOUTH);

        frame.pack(); // Recalculate window size
        frame.setVisible(true);

        // Initialize timer for hiding unmatched cards after a delay
        hideCardTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideCards();
            }
        });
        hideCardTimer.setRepeats(false);
        hideCardTimer.start();
    }

    /**
     * Initializes the deck by creating Card objects with a name and image for each type of card.
     */
    void setupCards() {
        cardSet = new ArrayList<>();
        for (String cardName : cardList) {
            // Load card image and scale it to specified dimensions
            Image cardImg = new ImageIcon("E:/FINAL YR PROJECT V2/CardMatch V8/MatchCards8/src/img2/" + cardName + ".jpg").getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));

            // Create and add the card to the cardSet
            Card card = new Card(cardName, cardImageIcon);
            cardSet.add(card);
        }
        cardSet.addAll(cardSet); // Duplicate cards to create pairs

        // Load and set the back image for face-down cards
        Image cardBackImg = new ImageIcon("E:/FINAL YR PROJECT V2/CardMatch V8/MatchCards8/src/img2/backCard2.jpg").getImage();
        cardBackImageIcon = new ImageIcon(cardBackImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
    }

    /**
     * Shuffles the cardSet to randomize card positions on the board.
     */
    void shuffleCards() {
        for (int i = 0; i < cardSet.size(); i++) {
            int j = (int) (Math.random() * cardSet.size());
            // Swap cards
            Card temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
    }

    /**
     * Handles the click event for each card, checking if two cards match or not.
     * @param tile The button representing the selected card.
     */
    void handleCardClick(JButton tile) {
        if (!gameReady || tile.getIcon() != cardBackImageIcon) return;

        if (card1Selected == null) {
            // Select first card
            card1Selected = tile;
            int index = board.indexOf(card1Selected);
            card1Selected.setIcon(cardSet.get(index).cardImageIcon);
        } else if (card2Selected == null) {
            // Select second card
            card2Selected = tile;
            int index = board.indexOf(card2Selected);
            card2Selected.setIcon(cardSet.get(index).cardImageIcon);

            // Check for match
            if (!card1Selected.getIcon().equals(card2Selected.getIcon())) {
                errorCount++;
                textLabel.setText("Errors: " + errorCount);
                hideCardTimer.start(); // Hide cards after delay
            } else {
                // Cards match, reset selections
                card1Selected = null;
                card2Selected = null;
            }
        }
    }

    /**
     * Hides unmatched cards by flipping them back to the back image.
     */
    void hideCards() {
        if (gameReady && card1Selected != null && card2Selected != null) {
            card1Selected.setIcon(cardBackImageIcon);
            card2Selected.setIcon(cardBackImageIcon);
            card1Selected = null;
            card2Selected = null;
        } else {
            for (JButton button : board) {
                button.setIcon(cardBackImageIcon);
            }
            gameReady = true;
            restartButton.setEnabled(true);
        }
    }

    /**
     * Resets the game by shuffling the cards and resetting all game variables.
     */
    void restartGame() {
        gameReady = false;
        restartButton.setEnabled(false);
        card1Selected = null;
        card2Selected = null;
        shuffleCards();
        errorCount = 0;
        textLabel.setText("Errors: " + errorCount);

        // Re-assign icons to match the new shuffled order
        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(cardSet.get(i).cardImageIcon);
        }

        hideCardTimer.start(); // Start timer to reset all cards face-down
    }
}

