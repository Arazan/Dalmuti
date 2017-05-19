package dalmutimodel;

/**
 *
 * @author Miguel Hernandez
 */
public class Card {
  
    private String cardName;    
    private int cardValue;
    

    public Card(int cardValue) {
        this.cardValue = cardValue;
        this.cardName = DalmutiValues.CARD_NAMES[cardValue-1];
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardValue() {
        return cardValue;
    }

}
