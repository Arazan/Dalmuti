/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutiserver;

import com.google.gson.Gson;
import dalmutimodel.Game;
import dalmutimodel.Games;
import dalmutimodel.Player;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 *
 * @author Miguel Hernandez
 */
public class Server extends WebSocketServer {

    private ArrayList<WebSocket> clients = new ArrayList<WebSocket>();
    private Games DalmutiGame;

    public Server(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New Connection From " + conn.getRemoteSocketAddress().toString());
        clients.add(conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Connection Closed From " + conn.getRemoteSocketAddress().toString());
        clients.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message from " + conn.getRemoteSocketAddress().toString() + "-" + message);
        processMessage(conn, message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println(ex.getMessage());
    }

    @Override
    public void onStart() {
        this.DalmutiGame = new Games();
        System.out.println("Dalmuti Server Started");
    }

    private void processMessage(WebSocket conn, String message) {
        ClientMessage clientMessage;

        if (message == null) {
            return;
        }
        try {
            Gson gson = new Gson();
            clientMessage = gson.fromJson(message, ClientMessage.class);
        } catch (Exception e) {
            System.out.println("Invalid Message from" + conn.getRemoteSocketAddress().toString());
            return;
        }

        if (clientMessage == null) {
            return;
        }

        //get rooms
        if (ServerValues.ACTION_GET_ROOMS.equalsIgnoreCase(clientMessage.getAction())) {
            conn.send(this.DalmutiGame.getRoomList());
        }

        //Create room action
        if (ServerValues.ACTION_CREATE_ROOM.equalsIgnoreCase(clientMessage.getAction())) {
            String username = clientMessage.getData().get("username");
            int roomSize = Integer.parseInt(clientMessage.getData().get("roomSize"));

            //Search for username availability
            for (Game g : this.DalmutiGame.getRooms()) {
                for (Player p : g.getPlayers()) {
                    if (p.getPlayerName().equalsIgnoreCase(username)) {
                        ServerMessage sm = new ServerMessage("", ServerValues.STATUS_ERROR, ServerValues.ERROR_USER_IN_USE, null);
                        conn.send(sm.toString());
                        return;
                    }
                }
            }

            //create room
            this.DalmutiGame.addRoom(new Player(username, conn), roomSize);

            //Join Game
            //publish new room
            this.sendToAll(this.DalmutiGame.getRoomList());
        }

        //Join a new room
        if (ServerValues.ACTION_JOIN_ROOM.equalsIgnoreCase(clientMessage.getAction())) {
            String username = clientMessage.getData().get("username");
            String gameID = clientMessage.getData().get("gameId");

            //Search for username availability
            for (Game g : this.DalmutiGame.getRooms()) {
                for (Player p : g.getPlayers()) {
                    if (p.getPlayerName().equalsIgnoreCase(username)) {
                        ServerMessage sm = new ServerMessage("", ServerValues.STATUS_ERROR, ServerValues.ERROR_USER_IN_USE, null);
                        conn.send(sm.toString());
                        return;
                    }
                }
            }

            //Validate game Id
            Game game = this.DalmutiGame.getRoom(gameID);
            if (game == null) {
                conn.send(new ServerMessage("", ServerValues.STATUS_ERROR, ServerValues.ERROR_INVALID_GAME_ID, null).toString());
                return;
            }

            //Join Game
            String addPlayerResult = game.addPlayer(new Player(username, conn));

            if ((addPlayerResult.equalsIgnoreCase(ServerValues.PLAYER_ADDED))
                    || (addPlayerResult.equalsIgnoreCase(ServerValues.PLAYER_ADDED_GAME_READY))) {

                 //TODO
                 //The player was added
                 
                 if(addPlayerResult.equalsIgnoreCase(ServerValues.PLAYER_ADDED_GAME_READY)){
                    //TODO                     
                    //Notify game start
                 }                                                   
                return;
            }else{
                //Error While adding player
                conn.send(new ServerMessage("", ServerValues.STATUS_ERROR, addPlayerResult, null).toString());
            }
        }
    }

    private void sendToAll(String message) {
        for (WebSocket ws : clients) {
            ws.send(message);
        }
    }

}
