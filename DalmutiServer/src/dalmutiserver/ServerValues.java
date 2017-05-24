/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutiserver;

/**
 *
 * @author Miguel Hernandez
 */
public class ServerValues {
    public static final String ACTION_GET_ROOM = "GET_ROOM";
    public static final String ACTION_GET_ROOMS = "GET_ROOMS";
    public static final String ACTION_CREATE_ROOM = "CREATE_ROOM";
    public static final String ACTION_JOIN_ROOM = "JOIN_ROOM";
    
    
    public static final String STATUS_SUCESS = "SUCESS";
    public static final String STATUS_ERROR = "ERROR";
    
    public static final String ERROR_USER_IN_USE = "The Username is not available";
    public static final String ERROR_INVALID_GAME_ID = "The game id provided is not available";
    public static final String ERROR_GAME_STARTED = "The game already started";
    public static final String ERROR_ROOM_FULL = "The room is already full";
    
    public static final String PLAYER_ADDED = "PLAYER_ADDED";
    public static final String PLAYER_ADDED_GAME_READY = "PLAYER_ADDED_GAME_READY";
    
    
}
