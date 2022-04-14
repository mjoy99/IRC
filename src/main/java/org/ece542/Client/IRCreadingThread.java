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
	 *
	 *@param client : the client this reader is used for
	 */
    public IRCreadingThread(IRCClient client) {

        this.client = client;
        this.clientSocket = client.getSocket();

        try {
            InputStream serverMessage = clientSocket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(serverMessage));
        } catch (Exception ex) {
            System.out.println("Class: IRCreadingThread. Method: Constructor. Error Attaching " +
                    "ServerMessage Stream to reader " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
	/**
	 * Run method for the reading thread. Will run until cannot read from buffer. 
	 */
    public void run() {

        String fromServer;

        while (client.socketConnected.get()) {


            try {
                //prints to user terminal

                fromServer = reader.readLine();

              System.out.println("\n" + fromServer);


              System.out.print("{" + client.getUserName() + "}: ");


            } catch (IOException e) {
                System.out.println("Could Not Read From The Server: " + e.getMessage());
                break;
            }


        }


    }

}
