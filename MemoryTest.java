/**
 * MemoryTest Class
 * -
 * Description: A utility class to test memory usage of the game.
 * -
 * Version: [V11]
 * Author: [Romeo Maunick - RM]
 * Date: [04/02/2025]
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryTest {

    public static void main(String[] args) {
        // Running the performance and memory tests with different card sets
        System.out.println("Running memory and performance tests...");

        // Test memory usage and performance with small and large card sets
        runMemoryTest(4);   // Small set of cards (4 pairs of cards)
        runMemoryTest(6);   // Medium set of cards (6 pairs of cards)
        runMemoryTest(12);  // Large set of cards (12 pairs of cards)
        runMemoryTest(16);  // Bigger set of cards (16 pairs of cards)
        runMemoryTest(20);  // Even bigger set of cards (20 pairs of cards)
        runMemoryTest(24);  // Large set of cards (24 pairs of cards)
        runMemoryTest(28);  // Large set of cards (28 pairs of cards)
        runMemoryTest(30);  // Large set of cards (30 pairs of cards)
        runMemoryTest(50);  // Large set of cards (30 pairs of cards)
        runMemoryTest(100);  // Large set of cards (30 pairs of cards)

        // Additional deep analysis on specific parts of the game
        analyzeCardClassMemory();  // Test memory used by Card class with images
        analyzeGameStateMemory();  // Test memory used by game state and card selection
    }

    /**
     * Run the memory and performance test for a specific number of cards
     *
     * @param numberOfPairs Number of card pairs in the game (total cards = 2 * numberOfPairs)
     */
    public static void runMemoryTest(int numberOfPairs) {
        System.gc();  // Force garbage collection for more accurate memory readings

        long beforeMemory = getMemoryUsage();
        long startTime = System.nanoTime();
        // Test game setup with the specified number of pairs (card objects)
        setupGame(numberOfPairs);

        long afterSetupMemory = getMemoryUsage();
        long setupTime = System.nanoTime() - startTime;

        System.out.println("Setup Game Test with " + numberOfPairs + " pairs:");
        System.out.println("Memory Before Setup (no images): " + beforeMemory / (1024 * 1024) + " MB");
        System.out.println("Memory After Setup (with images): " + afterSetupMemory / (1024 * 1024) + " MB");
        System.out.println("Memory Used by Setup: " + (afterSetupMemory - beforeMemory) / (1024 * 1024) + " MB");
        System.out.println("Setup Time: " + setupTime / 1_000_000 + " ms");
        // Test card click function multiple times
        beforeMemory = getMemoryUsage();
        startTime = System.nanoTime();

        // Simulate clicking cards a few times
        performCardClicks(numberOfPairs);

        long afterClickMemory = getMemoryUsage();
        long clickTime = System.nanoTime() - startTime;

        System.out.println("\nCard Click Test with " + numberOfPairs + " pairs:");
        System.out.println("Memory Before Clicks: " + beforeMemory / (1024 * 1024) + " MB");
        System.out.println("Memory After Clicks: " + afterClickMemory / (1024 * 1024) + " MB");
        System.out.println("Memory Used by Clicks: " + (afterClickMemory - beforeMemory) / (1024 * 1024) + " MB");
        System.out.println("Click Time: " + clickTime / 1_000_000 + " ms");
        // Final memory and performance statistics
        System.out.println("\nTest Complete for " + numberOfPairs + " pairs.\n");
    }

    /**
     * Simulates setting up the game with a specified number of card pairs
     *
     * @param numberOfPairs The number of card pairs to use in the game
     */
    public static void setupGame(int numberOfPairs) {
        // Simulate a game setup (e.g., creating card objects)
        ArrayList<Card> cards = new ArrayList<>();

        // Create the cards with images
        for (int i = 0; i < numberOfPairs; i++) {
            // Load the card image (image loading simulation - replace this with actual images)
            Image cardImg = new ImageIcon("src/img2/" + "goku1" + i + ".jpg").getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(90, 128, Image.SCALE_SMOOTH));

            cards.add(new Card("Card " + i, cardImageIcon)); // Add card with image
            cards.add(new Card("Card " + i, cardImageIcon)); // Add a matching pair
        }

        // Shuffle the cards
        Collections.shuffle(cards);
    }

    /**
     * Simulates performing a number of card clicks during the game
     *
     * @param numberOfPairs The number of card pairs to use in the game
     */
    public static void performCardClicks(int numberOfPairs) {
        // Simulate clicking on cards (you could add functionality to mimic actual gameplay)
        ArrayList<Card> cards = new ArrayList<>();

        // Create the cards with images
        for (int i = 0; i < numberOfPairs; i++) {
            // Load the card image (image loading simulation - replace this with actual images)
            Image cardImg = new ImageIcon("src/img2/" + "goku1" + i + ".jpg").getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(90, 128, Image.SCALE_SMOOTH));

            cards.add(new Card("Card " + i, cardImageIcon)); // Add card with image
            cards.add(new Card("Card " + i, cardImageIcon)); // Add a matching pair
        }

        // Simulate clicking pairs of cards
        for (int i = 0; i < cards.size() - 1; i++) {
            // Simulating selecting two cards
            Card firstCard = cards.get(i);
            Card secondCard = cards.get(i + 1);

            // Simulate checking if the cards match (the logic would normally involve checking images)
            if (firstCard.cardName.equals(secondCard.cardName)) {
                // Cards match (no action needed, just for testing purposes)
            }

            // Introduce a small delay for each "click" to simulate user interaction
            try {
                Thread.sleep(50); // Simulate the delay between card clicks
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the current memory usage in bytes
     *
     * @return The current memory usage in bytes
     */
    public static long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory(); // Returns the memory currently used
    }

    /**
     * Analyzes the memory usage of the Card class with images.
     */
    public static void analyzeCardClassMemory() {
        System.out.println("\n--- Memory Test: Card Class (With Images) ---");

        long beforeMemory = getMemoryUsage();

        // Create and initialize many Card objects with images (simulate a memory load)
        ArrayList<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Image cardImg = new ImageIcon("src/img2/" + "goku1" + i + ".jpg").getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(90, 128, Image.SCALE_SMOOTH));
            cardList.add(new Card("Card " + i, cardImageIcon)); // Creating cards with images for testing memory load
        }

        long afterMemory = getMemoryUsage();
        System.out.println("Memory Before Card Setup (with images): " + beforeMemory / (1024 * 1024) + " MB");
        System.out.println("Memory After Card Setup (with images): " + afterMemory / (1024 * 1024) + " MB");
        System.out.println("Memory Used by Card Setup (with images): " + (afterMemory - beforeMemory) / (1024 * 1024) + " MB");
    }

    /**
     * Analyzes memory usage related to game state management and card selection.
     */
    public static void analyzeGameStateMemory() {
        System.out.println("\n--- Memory Test: Game State Management (With Images) ---");

        long beforeMemory = getMemoryUsage();

        // Simulate game state management (e.g., storing selected cards, tracking score)
        int matchedPairs = 0;
        int score = 0;
        JButton card1Selected = new JButton();
        JButton card2Selected = new JButton();

        // Simulate selecting a few pairs of cards
        for (int i = 0; i < 10; i++) {
            card1Selected = new JButton();
            card2Selected = new JButton();

            // Simulate checking the match and updating game state
            if (card1Selected.getText().equals(card2Selected.getText())) {
                matchedPairs++;
                score += 2; // Assuming +2 score for every match
            }
        }

        long afterMemory = getMemoryUsage();
        System.out.println("Memory Before Game State Setup (with images): " + beforeMemory / (1024 * 1024) + " MB");
        System.out.println("Memory After Game State Setup (with images): " + afterMemory / (1024 * 1024) + " MB");
        System.out.println("Memory Used by Game State (with images): " + (afterMemory - beforeMemory) / (1024 * 1024) + " MB");
    }
}




