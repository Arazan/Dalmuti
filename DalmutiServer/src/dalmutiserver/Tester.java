/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutiserver;

import dalmutimodel.Deck;

/**
 *
 * @author Miguel Hernandez
 */
public class Tester {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Start Test");
        
        Deck deck = new Deck();
        
        System.out.println(deck.getDeckSize());
        System.out.println(deck.drawFromDeck().getCardValue());
        System.out.println(deck.getDeckSize());
        
        
        System.out.println("End Test");
    }    
    
}
