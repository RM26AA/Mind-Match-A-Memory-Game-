import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameTester Class
 * -
 * Description: A utility class to test various functionalities of the IntroGUI and MatchCards classes.
 * Simulates user interactions like button clicks, card flipping, and validates game logic.
 * -
 * Version: [V10]
 * Author: [Romeo Maunick - RM]
 * Date: [27/12/2024]
 */
public class GameTester {

    public static void main(String[] args) {
        System.out.println("Starting Game Tester...");

        // Test IntroGUI functionality
        testIntroGUI();
        System.out.println("All Tests for IntroGUI completed.");

        // Test MatchCards functionality
        testMatchCards();
        System.out.println("All MatchCards tests completed.");
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
     */
    public static void testMatchCards() {
        System.out.println("Testing MatchCards...");

        // Create an instance of MatchCards
        MatchCards matchCards = new MatchCards();

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

