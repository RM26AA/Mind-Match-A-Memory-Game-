/**
 * Card Class
 * -
 * Description: Represents an individual card in the memory-matching game.
 * Each card has a name and an image associated with it.
 * -
 * Version: [V3]
 * Author: [Romeo Maunick - RM]
 * Date: [04/12/2024]   //change to current date
 */

import javax.swing.*;

public class Card {
    // Name of the card, used to identify each unique card (e.g., "Goku", "Vegeta")
    String cardName;

    // Icon image for the card, displayed when the card is face-up
    ImageIcon cardImageIcon;

    /**
     * Constructor to initialize a card with its name and image.
     *
     * @param cardName      The name of the card (e.g., "fire").
     * @param cardImageIcon The image representing the card.
     */
    public Card(String cardName, ImageIcon cardImageIcon) {
        this.cardName = cardName;
        this.cardImageIcon = cardImageIcon;
    }

    /**
     * Returns a string representation of the card, specifically the card's name.
     * Useful for debugging or displaying the card name.
     *
     * @return The name of the card as a string.
     */
    @Override
    public String toString() {
        return cardName;
    }
}


