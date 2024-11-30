import javax.swing.*;

public class Card {

    String cardName;

    ImageIcon cardImageIcon;

    public Card(String cardName, ImageIcon cardImageIcon) {
        this.cardName = cardName;
        this.cardImageIcon = cardImageIcon;
    }
    
    @Override
    public String toString() {
        return cardName;
    }
}


