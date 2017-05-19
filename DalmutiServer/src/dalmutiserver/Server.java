/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalmutiserver;

import dalmutimodel.Games;
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
        processMessage(message);
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
    
    
    private void processMessage(String message){
        if (message==null)
            return;
        
        
    }

}
