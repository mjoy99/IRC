package org.ece542.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <bold>ServerMain class</bold> This class is the main entry point for the serverside application.  A Set of
 * user names and a Set of <bold>UserThread</bold>s which hold the client sockets for each connected user.
 * This class is run through terminal window and takes a single command line argument for the port number
 * the server will be listening for incoming socket requests on.
 */
public class ServerMain {

    private final int PORTNUM;

    /**
     * Effects: Gets the names of the users signed in to the server.
     * @return Set of Usernames
     */
    public Set<String> getUserNames() {
        return userNames;
    }

    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new CopyOnWriteArraySet<>();

    public ServerMain(int port){
        this.PORTNUM=port;
    }

    /**
     * <bold>private start()</bold>
     * Effects: starts a new ServerSocket, listens for incoming client requests.  Starts a
     * new thread for each client Socket accepted.
     */
    private void start(){

        try {
            ServerSocket serverSocket = new ServerSocket(PORTNUM);

            while(true){
                Socket socket = serverSocket.accept();

                UserThread newUser = new UserThread(socket, this);

                userThreads.add(newUser);
                newUser.start();
            }


        } catch (IOException e) {
            System.out.println("Class: ServerMain. Method: start. IOException for creating a ServerSocket");
            e.printStackTrace();
        }
    }

    /**
     * Effects: Removes User and associated thread from server tracking.
     * Modifies: userNames Set, userThreads Set.
     * @param userName String name of the user to be removed.
     * @param user UserThread for user to be removed.
     */
    public void removeUser(String userName, UserThread user){
        userNames.remove(userName);
        userThreads.remove(user);

    }

    /**
     * Effects:  Sends message to all userThreads connected to the server with the exception of the
     * originator. If originator is null, message will be sent to all connected users.
     *
     * @param message String containing message to be sent to all connected users.
     * @param originator UserThread designated as originator, and will be excluded from broadcast.
     */
    public void sendBroadcast(String message, UserThread originator){

        for (UserThread user : userThreads){
            if (user != originator){
                user.sendMessage(message);
            }
        }

    }

    /**
     * Effects: will add userName to set of UserNames if not already present.
     * @param userName String of userName to be added.
     */
    public void addUserName(String userName){
        userNames.add(userName);
    }

    /**
     * Main entry point for Server.  Responsible for starting the chat server, accepting clients,
     * keeping track of clients and receiving/sending chat messages.
     *
     * @param args String containing port number for listening port
     */
    public static void main(String[] args) {

        if (args.length !=1) {
            throw new ArrayIndexOutOfBoundsException("Class:ServerMain, Method: main. Expected" +
                    "one command line argument containing port number");
        }

        int port = Integer.parseInt(args[0]);

        ServerMain serverMain = new ServerMain(port);

        serverMain.start();



    }


    /**
     * Effects: returns true if users are connected.
     * @return Boolean true for other users.
     */
    public boolean HasUsers() {
        return !userNames.isEmpty();
    }
}
