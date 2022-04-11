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
	private PrintmessageWriter messageWriter;
 
	/**
	 * Effects: attaches user input to client's socket output stream
	 *
	 *@param clientSocket : the client's socket used to send data to server 
	 *@param client : the client object this IRCwritingThread is used for
	 */
    public IRCwritingThread(Socket clientSocket, IRCClient client) {
        this.clientSocket = clientSocket;
		this.client = client;
        
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
        client.changeUsername(username);
        //sending username to the server
		messageWriter.println(username);
		//Explaining How To Disconnect to User:
		system.out.println("To Disconnect From Chat, Type In \"DONE!\" ");
 
        String userInput;
 
        do {
            userInput = console.readLine("{" + username + "}:\t");
            messageWriter.println(userInput);
 
        } while (!userInput.equals("DONE!"));
 
		//once user wishes to disconnect, close output stream, printwriter object stream, and socket connection
        try {
			dataOut.close();
			messageWriter.close();
            clientSocket.close();
        } catch (Exception e) {
 
            System.out.println("ERROR @ IRCwritingThread disconnect block: "e.getMessage());
        }
    }
}
