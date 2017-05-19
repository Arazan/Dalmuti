package dalmutimodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Miguel Hernandez
 */
public class Deck {
    
    private ArrayList<Card> cards;    
    
    public Deck(){        
        cards = new ArrayList<Card>();
        
        // Add cards to the Deck
        for (int i = 1; i <= 12; i++){
            for (int j = 1; j <= i; j++){
                cards.add(new Card(i));
            }
        }
        
        //Add Jesters
        cards.add(new Card(13));
        cards.add(new Card(13));
        
        //Shufle Deck
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
                
    }
    
    public Card drawFromDeck(){
        return cards.remove( 0 );
    }
    
    public int getDeckSize(){
        return cards.size();
    }
}
