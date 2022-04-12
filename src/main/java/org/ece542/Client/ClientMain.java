package org.ece542.Client;

public class ClientMain {


    /**
     * main entry point for IRC Client.
     * @param args command line parameters.  Expect 1 command line parameter for hostname/IP address
     */
    public static void main(String[] args) {
        //client info
        String hostname = args[0];
        int portnum = 5555;
        
        //creating new client
        IRCClient client = new IRCClient(hostname, portnum);
        
        //starting up client connection
        client.connectSocket();
    }
}
