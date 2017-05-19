package dalmutimodel;

/**
 *
 * @author Miguel Hernandez
 */
public class Card {

    private final String[] CARD_NAMES = new String[]
    {
        "Dalmuti","Archbishop","Earl Marshal","Baroness","Abbess","Knight",
        "Seamstress","Mason","Cook","Shepherdess","Stonecutter","Peasant","Jester"
    };

    
    private String cardName;    
    private int cardValue;
    

    public Card(int cardValue) {
        this.cardValue = cardValue;
        this.cardName = CARD_NAMES[cardValue-1];
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardValue() {
        return cardValue;
    }

}
