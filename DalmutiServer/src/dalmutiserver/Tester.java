/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutiserver;

import com.google.gson.Gson;
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
        games.addRoom(new Player("Miguel Hernandez",null), 5);
        games.addRoom(new Player("Pablo Orbe",null), 10);
        
        
        System.out.println(games.getRoomCount());
        System.out.println(games.getRoomList());
        System.out.println(games.getRoom(games.getRooms().get(1).getGameID()));
        
        
        ClientMessage message = new ClientMessage();
        
        message.setUserId("USER");
        message.setGameID("GAME");
        message.setAction("ACTION");
        //message.setData("DATA");
        
        System.out.println(message.toString());
        
        
        Gson gson = new Gson();
        ClientMessage b = gson.fromJson( "{\"userId\":\"USER\",\"gameID\":\"GAME\",\"action\":\"ACTION\",\"data\":{\"username\":\"apple\",\"roomSize\":\"orange\"}}", ClientMessage.class ); 
        

        //{"userId":"USER","gameID":"GAME","action":"ACTION","data":{"username":"apple","roomSize":"orange"}}
        
        
        System.out.println(b.getData().get("username"));        
        System.out.println("End Test");
    }    
    
}
