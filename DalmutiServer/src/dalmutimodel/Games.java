/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutimodel;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author Miguel Hernandez
 */
public class Games {
    
    private ArrayList<Game> gameRooms;
    
    public Games(){
        this.gameRooms = new ArrayList<Game>();
    }
    
    public int getRoomCount(){
        return gameRooms.size();
    }
    
    public void addRoom(Player owner, int roomSize){
        gameRooms.add(new Game(owner,roomSize));
    }
    
    public String getRoomList(){
        return this.toString();
    }
    
    public ArrayList<Game> getRooms(){
        return gameRooms;
    }
    
    public String getRoom(String roomID){
        for(Game game:gameRooms){
            if(roomID.equalsIgnoreCase(game.getGameID())){
                Gson gson = new Gson();
                return gson.toJson(game);
            }
        }
        return null;
    }
    
    @Override
    public String toString(){
        Gson gson = new Gson();                            
        return gson.toJson(this);
    }    
    
}
