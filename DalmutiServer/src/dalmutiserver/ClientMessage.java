/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutiserver;

import com.google.gson.Gson;
import java.util.Map;

/**
 *
 * @author Miguel Hernandez
 */
public class ClientMessage {
    
    private String userId;
    private String gameID;
    private String action;
    private Map<String,String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    
    @Override
    public String toString(){
        Gson gson = new Gson();                            
        return gson.toJson(this);
    }    
    
    
}
