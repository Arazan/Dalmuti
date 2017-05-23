/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutimodel;

import java.util.ArrayList;
import java.util.UUID;
import org.java_websocket.WebSocket;

/**
 *
 * @author Miguel Hernandez
 */
public class Player {

    private String playerID;
    private String playerName;
    private transient WebSocket conn;

    public WebSocket getConn() {
        return conn;
    }

    public void setConn(WebSocket conn) {
        this.conn = conn;
    }
    
    public Player(String playerName, WebSocket conn) {
        this.playerID = UUID.randomUUID().toString();
        this.playerName = playerName;
        this.conn = conn;
    }    

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }
    private ArrayList<Card> playerHand;
                   
}

