package org.ece542.Client;

import java.io.*;
import java.net.*;
 
/**
 * IRCwritingThread class will be used for listening for user keyboard input and 
 * sending the user message to the server. The IRCwritingThread will remain active until
 * IRCClient is disconnected from server
 */
public class IRCwritingThread extends Thread {
    
	private Socket clientSocket;
	private IRCClient client;
	private PrintWriter messageWriter;
 
	/**
	 * Effects: attaches user input to client's socket output stream
	 *
	 *
	 *@param client : the client object this IRCwritingThread is used for
	 */
    public IRCwritingThread(IRCClient client) {

		this.client = client;
        this.clientSocket = client.getSocket();
        
        try {
            OutputStream dataOut = clientSocket.getOutputStream();
            messageWriter = new PrintWriter(dataOut, true);
        } catch (IOException e) {
            System.out.println("Error Attaching OutputStream: "+e.getMessage());
            e.printStackTrace();
        }
    }
 
    public void run() {
 
        Console console = System.console();
		
        //prompting user for username
		String username = console.readLine("\nPlease Select A Username: ");
        client.setUsername(username);
        //sending username to the server
		messageWriter.println(username);
		//Explaining How To Disconnect to User:
		System.out.println("To Disconnect From Chat, Type In \"!Exit\" ");
 
        String userInput;
 
        do {
            userInput = console.readLine("{" + username + "}:\t");

            messageWriter.println(userInput);


        } while (!userInput.equalsIgnoreCase("!exit"));

        //update flag that socket is being closed.
        client.socketConnected.set(false);

		//once user wishes to disconnect, close output stream, printwriter object stream, and socket connection
        try {

            clientSocket.close();
        } catch (Exception e) {
 
            System.out.println("ERROR @ IRCwritingThread disconnect block: "+ e.getMessage());
        }
    }
}
