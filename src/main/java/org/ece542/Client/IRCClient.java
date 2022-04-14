package org.ece542.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Creates new IRCClient object with socket and 2 threads for simultaneous reading from and writing to server
 */
public class IRCClient {
    private String hostname;
    private String userName;
    private final int port;
    private Socket clientSocket;
    public AtomicBoolean socketConnected = new AtomicBoolean(false);

    /**
     * Effects: Constructor for IRCClient
     * @param hostname String IP Address or hostname of the server connecting to.
     * @param port portnum used for IRC server
	 *
     */
    public IRCClient(String hostname,int port){
        this.hostname = hostname;
        this.port = port;
    }
    
    /**
     * Requires: portnum and hostname not null.  portnum>0
     * Effects: Creates new IRCClientSocket instance to opens a client Socket to IRC Server. 
     *          Also Attaches input stream and output stream of Socket.
     */
    public void connectSocket(){

		if(hostname != null && !(hostname.isEmpty()) && port >0){
			try {
				this.clientSocket = new Socket(hostname, port);
                socketConnected.set(true);
				System.out.println("Socket is Connected");
				//instantiates and starts the 2 threads for simultaneous reading from server and writing to server
				new IRCreadingThread(this).start();
				new IRCwritingThread(this).start();


			} catch (UnknownHostException e) {
				System.out.println("Server Not Located "+e.getMessage());
			} catch (IOException ex) {
				System.out.println("I/O Error: "+ex.getMessage());
			}
		}
		else
			System.out.println("Host IP or port number invalid");
    } 
    
	/**
     * Effects: returns the client's userName
     */
    public String getUserName(){
        return userName;
    }
	
     /**
     * Effects: Changes this client's userName if not null or empty
     * @param username: the client's new proposed userName
     */
    public void setUsername(String username){
        if(username == null || username.isEmpty())
            System.out.println("INVALID USERNAME");
        else{
            this.userName = username;
            System.out.println("Username Changed to: "+this.userName);
        }
    }

    public Socket getSocket() {
        return clientSocket;
    }
}
