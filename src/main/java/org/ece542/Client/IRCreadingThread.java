package org.ece542.Client;

import java.io.*;
import java.net.*;
 
/**
 * IRCreadingThread is used to listen to server broadcast messages and displaying on client's terminal
 * until connection is closed.
 *
 */
public class IRCreadingThread extends Thread {

	//class attibutes
    private IRCClient client;
	private Socket clientSocket;
    private BufferedReader reader;
 
 
	/**
	 * Constructor for the reading thread.
	 * 
	 *@param clientSocket : the client's socket object
	 *@param client : the client this read is used for
	 */
    public IRCreadingThread(Socket clientSocket, IRCClient client) {
        this.clientSocket = clientSocket;
        this.client = client;
 
        try {
            InputStream serverMessage = clientSocket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(serverMessage));
        } catch (Exception ex) {
            System.out.println("Error Attaching ServerMessage Stream to reader " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
	/**
	 * Run method for the reading thread. Will run until cannot read from buffer. 
	 */
    public void run() {
        while (true) {
            try {
				//prints to user terminal
                System.out.println("\n" + reader.readLine());
 
                // prints the username 
                if (client.getUsername() != null) {
                    System.out.print("{" + client.getUserName() + "}: ");
                }
            } catch (IOException e) {
                System.out.println("Could Not Read From The Server: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }
}
