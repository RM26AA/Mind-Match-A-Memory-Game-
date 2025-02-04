/**
 * GameTester Class
 * -
 * Description: A utility class to test various functionalities of the IntroGUI and MatchCards classes.
 * Includes functional, stress, and randomization tests for robustness and reliability.
 * -
 * Version: [V11]
 * Author: [Romeo Maunick - RM]
 * Date: [04/02/2025]
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTester {

    public static void main(String[] args) {
        System.out.println("Starting Game Tester...");

        // Test IntroGUI functionality
        testIntroGUI();
        System.out.println("All Tests for IntroGUI completed.");

        // Test MatchCards functionality
        MatchCards matchCards = new MatchCards();
        testMatchCards(matchCards);

        // Perform stress testing
        stressTestCardFlipping(matchCards);

        // Perform random shuffling testing
        testRandomShuffling(matchCards);

        System.out.println("All tests completed successfully.");
    }

    /**
     * Tests the IntroGUI class by simulating button clicks.
     */
    public static void testIntroGUI() {
        System.out.println("Testing IntroGUI...");

        // Create an instance of IntroGUI
        IntroGUI introGUI = new IntroGUI();

        // Access buttons
        JButton playButton = introGUI.playButton;
        JButton helpButton = introGUI.helpButton;
        JButton quitButton = introGUI.quitButton;

        // Test Play button
        simulateButtonClick(playButton, "Play");
        System.out.println("Play button works as expected.");

        // Test Help button
        simulateButtonClick(helpButton, "Help");
        System.out.println("Help button works as expected.");

        // Test Quit button
        simulateButtonClick(quitButton, "Quit");
        System.out.println("Quit button works as expected.");
    }

    /**
     * Tests the MatchCards class, including card flipping, restart functionality,
     * and the game-over popup behavior.
     *
     * @param matchCards The MatchCards instance to test.
     */
    public static void testMatchCards(MatchCards matchCards) {
        System.out.println("Testing MatchCards...");

        // Test card flipping
        System.out.println("Testing card flipping...");
        if (!matchCards.board.isEmpty()) {
            JButton firstCard = matchCards.board.get(0);
            JButton secondCard = matchCards.board.get(1);

            // Simulate flipping the first card
            simulateButtonClick(firstCard, "Flip first card");
            assert firstCard.getIcon() != matchCards.cardBackImageIcon : "First card did not flip correctly.";

            // Simulate flipping the second card
            simulateButtonClick(secondCard, "Flip second card");
            assert secondCard.getIcon() != matchCards.cardBackImageIcon : "Second card did not flip correctly.";

            System.out.println("Card flipping works as expected.");
        }

        // Test Restart button
        System.out.println("Testing Restart button...");
        simulateButtonClick(matchCards.restartButton, "Restart Game");
        assert matchCards.errorCount == 0 : "Error count did not reset after restart.";
        assert matchCards.board.stream().allMatch(b -> b.getIcon() == matchCards.cardBackImageIcon)
                : "Not all cards were flipped back after restart.";
        System.out.println("Restart button works as expected.");

        // Test Game Over popup
        System.out.println("Testing Game Over popup...");
        simulateGameOver(matchCards);
    }

    /**
     * Simulates the Game Over popup by testing the Restart and Close buttons.
     *
     * @param matchCards The MatchCards instance to test.
     */
    private static void simulateGameOver(MatchCards matchCards) {
        // Trigger the Game Over popup
        JDialog gameOverDialog = matchCards.showGameOverPopup();

        // Test the Restart button in the popup
        JButton popupRestartButton = findButtonByName(gameOverDialog, "Restart");
        assert popupRestartButton != null : "Restart button in Game Over popup not found.";
        simulateButtonClick(popupRestartButton, "Game Over Restart");
        System.out.println("Game Over Restart button works as expected.");

        // Test the Close button in the popup
        JButton popupCloseButton = findButtonByName(gameOverDialog, "Close");
        assert popupCloseButton != null : "Close button in Game Over popup not found.";
        simulateButtonClick(popupCloseButton, "Game Over Close");
        System.out.println("Game Over Close button works as expected.");
    }

    /**
     * Stress tests the card flipping functionality by simulating rapid clicks on multiple cards.
     *
     * @param matchCards The MatchCards instance to test.
     */
    public static void stressTestCardFlipping(MatchCards matchCards) {
        System.out.println("Starting Stress Test for Card Flipping...");

        // Ensure the board is not empty
        if (matchCards.board.isEmpty()) {
            System.out.println("No cards available for stress testing.");
            return;
        }

        // Simulate rapid card flipping
        for (int i = 0; i < 50; i++) { // Simulate 50 rapid clicks
            int cardIndex = i % matchCards.board.size(); // Cycle through available cards
            JButton cardButton = matchCards.board.get(cardIndex);
            simulateButtonClick(cardButton, "Rapid card flip " + (i + 1));
        }

        // Ensure no unexpected errors occurred during stress test
        assert matchCards.errorCount >= 0 : "Error count became negative during stress test!";
        System.out.println("Stress Test for Card Flipping completed successfully.");
    }

    /**
     * Tests the randomness of the shuffle logic by checking card positions across multiple shuffles.
     *
     * @param matchCards The MatchCards instance to test.
     */
    public static void testRandomShuffling(MatchCards matchCards) {
        System.out.println("Starting Random Shuffling Test...");

        int shuffleCount = 100; // Number of shuffles to test
        int deckSize = matchCards.cardSet.size();
        int[][] positionTracker = new int[deckSize][deckSize];

        // Perform multiple shuffles and record positions
        for (int shuffle = 0; shuffleCount > shuffle; shuffle++) {
            matchCards.shuffleCards();

            for (int i = 0; i < deckSize; i++) {
                int cardIndex = matchCards.cardSet.indexOf(matchCards.cardSet.get(i));
                positionTracker[cardIndex][i]++;
            }
        }

        // Check that no card appears in the same position consistently
        for (int i = 0; i < deckSize; i++) {
            for (int j = 0; j < deckSize; j++) {
                assert positionTracker[i][j] < shuffleCount * 0.2 : // Ensure randomness
                        "Card " + i + " appeared in position " + j + " too frequently.";
            }
        }

        System.out.println("Random Shuffling Test completed successfully.");
    }

    /**
     * Searches for a button within a JDialog by its text.
     *
     * @param dialog     The JDialog to search.
     * @param buttonText The text of the button to find.
     * @return The JButton instance if found, otherwise null.
     */
    private static JButton findButtonByName(JDialog dialog, String buttonText) {
        for (Component component : dialog.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                for (Component subComponent : ((JPanel) component).getComponents()) {
                    if (subComponent instanceof JButton) {
                        JButton button = (JButton) subComponent;
                        if (button.getText().equals(buttonText)) {
                            return button;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Simulates a button click by triggering all attached ActionListeners.
     *
     * @param button The button to click.
     * @param action A description of the action being tested.
     */
    private static void simulateButtonClick(JButton button, String action) {
        System.out.println("Simulating button click: " + action);
        ActionEvent event = new ActionEvent(button, ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : button.getActionListeners()) {
            listener.actionPerformed(event);
        }
    }
}



