/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutimodel;

import com.google.gson.Gson;
import dalmutiserver.ServerValues;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Miguel Hernandez
 */
public class Game {
    
    private String gameID;

    public String getGameID() {
        return gameID;
    }

    public Player getGameOwner() {
        return gameOwner;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public String getGameState() {
        return gameState;
    }

    public ArrayList<Card> getCurrentPlay() {
        return currentPlay;
    }

    public Deck getDeck() {
        return deck;
    }
    private Player gameOwner;
    private ArrayList<Player> players;
    private int roomSize;
    private String gameState;
    private ArrayList<Card> currentPlay;
    private transient Deck deck;
    
    
    
    
       
     
    public Game(Player owner, int roomSize){
        this.gameID = UUID.randomUUID().toString();
        this.gameOwner = owner;
        this.players = new ArrayList<Player>();
        this.players.add(owner);
        this.roomSize = roomSize;
        this.gameState = DalmutiValues.WAITING_PLAYERS;
        this.deck = new Deck();              
    } 
    
    public String addPlayer(Player player){
        int playerCount = this.players.size();
        
        if(!this.gameState.equalsIgnoreCase(DalmutiValues.WAITING_PLAYERS))
            return ServerValues.ERROR_GAME_STARTED;
        
        if(playerCount == this.roomSize)
            return ServerValues.ERROR_ROOM_FULL;
        
        if(playerCount < this.roomSize){
            this.players.add(player);
            playerCount+=1;
        }
        
        if(playerCount == this.roomSize){
            this.gameState = DalmutiValues.GAME_START;
            
            //TODO: Players draw a card
            //TODO: Split Deck
            return ServerValues.PLAYER_ADDED_GAME_READY;
            
        }
        
        return ServerValues.PLAYER_ADDED;
    }
    
    public void startGame(){
        
    }
    
    
    @Override
    public String toString(){
        Gson gson = new Gson();                            
        return gson.toJson(this);
    }
    
    
    
                   
}
