package org.ece542.Client;

public class ClientMain {

    private String userName;

    /**
     * main entry point for IRC Client.
     * @param args
     */
    public static void main(String[] args) {
        //client info
        String hostname = args[0];
        int portnum = 5555;
        String username = " ";  //will be set by user later
        
        //creating new client
        IRCClient client = new IRCClient(hostname, portnum, username);
        
        //starting up client connection
        client.connectSocket();
    }
}
