package org.ece542.Server;


import java.io.*;
import java.net.Socket;

public class UserThread extends Thread{

    private Socket clientSocket;
    private ServerMain serverMain;
    private PrintWriter writer;

    /**
     * Constructor
     * @param socket  client socket
     * @param serverMain ServerMain used to generate the UserThread.
     */
    public UserThread(Socket socket, ServerMain serverMain) {
        clientSocket=socket;
        this.serverMain=serverMain;

    }

    /**
     * Effects: reads lines from client socket.  First line is user name. Send broadcast to <bold>ServerMain</bold> new user
     * has connected.  Sends broadcast to <bold>ServerMain</bold> for each new line received from client socket
     * until "!exit" is received.  sends exit notification, closes client socket and removes user from userThread
     * Set.
     */
    @Override
    public void run() {

        try {
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = clientSocket.getOutputStream();
            writer = new PrintWriter(outputStream,true);

            SendConnectionMessage();

            String userName = reader.readLine();
            serverMain.addUserName(userName);

            String serverMessage = userName + " has joined the chat.";
            serverMain.sendBroadcast(serverMessage, this);

            String clientMessage;

            while(true){
                clientMessage = reader.readLine();
                if (clientMessage.equalsIgnoreCase("!exit"))
                    break;
                serverMessage = userName + ": " + clientMessage;
                serverMain.sendBroadcast(serverMessage, this);
            }

            serverMain.removeUser(userName, this);
            clientSocket.close();

            serverMessage = userName + " has left the group chat";
            serverMain.sendBroadcast(serverMessage, null);

        } catch (IOException e) {
            System.out.println("Class: UserThread. Method: run. IOExcpetion on clientSocket.getInputStream.");
            e.printStackTrace();
        }
    }

    /**
     * Effects: Sends client message that connection was successful and a list of users connected.
     */
    private void SendConnectionMessage() {
        writer.println("Connection to server successful.");
        if (serverMain.HasUsers())
            writer.println("All users connected: " + serverMain.getUserNames());
        else
            writer.println("No other users connected at this time.");
    }

    /**
     * Effects: sends the message out to the client Socket.
     * @param message String containing text of message.
     */
    public void sendMessage(String message) {
        writer.println(message);
    }
}
