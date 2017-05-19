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
public class DalmutiServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.err.println("Usage: java DalmutiServer <port number>");
            System.exit(1);
        }
 
        //int portNumber = Integer.parseInt(args[0]);              
        Server serv = new Server(4444);
        serv.start();
    }

}
