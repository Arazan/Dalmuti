/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutiserver;

import dalmutimodel.Deck;
import dalmutimodel.Games;
import dalmutimodel.Player;

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
        
        Games games = new Games();
        games.addRoom(new Player("Miguel Hernandez"), 5);
        games.addRoom(new Player("Pablo Orbe"), 10);
        
        
        System.out.println(games.getRoomCount());
        System.out.println(games.getRoomList());
        System.out.println(games.getRoom(games.getRooms().get(1).getGameID()));
        
        
        
        
        System.out.println("End Test");
    }    
    
}
