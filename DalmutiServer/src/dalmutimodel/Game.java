/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutimodel;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Miguel Hernandez
 */
public class Game {
    
    private String gameID;
    private Player gameOwner;
    private ArrayList<Player> players;
    private int roomSize;
    private int gameState;
    private ArrayList<Card> currentPlay;
    private Deck deck;
    
    public static final String ERROR_GAME_STARTED = "ERROR_GAME_STARTED";
    public static final String ERROR_ROOM_FULL = "ERROR_ROOM_FULL";
    
    public static final String PLAYER_ADDED = "PLAYER_ADDED";
    
     
    public Game(Player owner, int roomSize){
        this.gameID = UUID.randomUUID().toString();
        this.gameOwner = owner;
        this.players = new ArrayList<Player>();
        this.players.add(owner);
        this.roomSize = roomSize;
        this.gameState = GameStates.NEW_GAME;
        this.deck = new Deck();              
    } 
    
    public String addPlayer(Player player){
        int playerCount = this.players.size();
        
        if(this.gameState!=GameStates.WAITING_PLAYERS)
            return ERROR_GAME_STARTED;
        
        if(playerCount == this.roomSize)
            return ERROR_ROOM_FULL;
        
        if(playerCount < this.roomSize)
            this.players.add(player);
        
        if(playerCount+1 < this.roomSize){
            this.gameState = GameStates.GAME_START;
            
            //TODO: Players draw a card
            //TODO: Split Deck
            
        }
        
        return PLAYER_ADDED;
    }
    
    public void startGame(){
        
    }
    
    
                   
}
