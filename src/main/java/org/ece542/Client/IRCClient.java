package org.ece542.Client;

import java.io.IOException;
import java.net.Socket;

/**
 * Creates new IRCClient object with socket and 2 threads for simultaneous reading from and writing to server
 */
public class IRCClient {
    private String hostname;
    private String username;
    private int portnum;
    private Socket clientSocket;

    /**
     * Effects: Constructor for IRCClient
     * @param hostname String IP Address or hostname of the server connecting to.
     * @param portnum portnum used for IRC server
	 * @param username : the username the user wishes to select
     */
    public IRCClient(String hostname,int portnum, String username){
        this.hostname = hostname;
        this.portnum = portnum;
        this.username = username;
    }
    
    /**
     * Requires: portnum and hostname not null.  portnum>0
     * Effects: Creates new IRCClientSocket instance to opens a client Socket to IRC Server. 
     *          Also Attaches input stream and output stream of Socket.
     */
    public void connectSocket(){
		if(hostname != null && !(hostname.isEmpty()) && portnum>0){
			try {
				this.clientSocket = new Socket(hostname, portnum);
				system.out.println("Socket is Connected");
				//instantiates and starts the 2 threads for simultaneous reading from server and writing to server
				new IRCreadingThread(socket, this).start();
				new IRCwritingThread(socket, this).start();
	 
			} catch (UnknownHostException e) {
				System.out.println("Server Not Located "+e.getMessage());
			} catch (IOException ex) {
				System.out.println("I/O Error: "+e.getMessage());
			}
		}
		else
			System.out.println("Host IP or port number invalid");
    } 
    
	/**
     * Effects: returns the client's username
     */
    public String getUsername(){
        return username;
    }
	
     /**
     * Effects: Changes this client's username if not null or empty
     * @param username: the client's new proposed username
     */
    public void changeUsername(String username){
        if(username == null || username.isEmpty())
            System.out.println("INVALID USERNAME");
        else{
            this.username = username;
            System.out.println("Username Changed to: "+this.username);
        }
    }

}
