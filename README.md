# IRC
ECE542 final project Internet Relay Chat

This project aims to demonstrate socket communications.  The final goal is to have a Server side app responsible for taking incoming connections
from users, managing rooms and relaying messages to all members of a room.  The Client side app will all a user to connect to the server, participate
in various rooms, post and see posts from others in the rooms.  This project is designed to mimic the old IRC applications.

There are two executable jars in the /target.  They are essentially the same jar with different manifest files to change the main entry point depending which mode you want to run the application in.  IRCServer is for the Server execution and IRC-1.0-SNAPSHOT.jar is for the client implementation.  

IRCServer takes one command line argument of the port number to listen to for incoming Socket requests.  To run use java -jar IRCServer.jar 5555, where 5555 is the port number which is set in this version of the client side application.

IRC-1.0-SNAPSHOT.jar takes one command line argument of the IP address where the server is located.  To run use java -jar IRC-1.0-SNAPSHOT.jar localhost, where in this case the server is located on the same machine as the client.  This can be replace for IP address.  The client application requires you to enter a user name that will display with your message on client apps connected to the same server.  You can exit out of the app by typing !exit at the prompt for sending messages.  This is not case sensitive.

The project is managed using Maven. The POM.xml is in the root directory of the project and can be used for standard maven commands.  Compile code with mvn compile.  The POM is set up to create an executable jar for the client app when using mvn package.  This can be adjusted to package for the server application by changing the MainClass entry in the manifest configuration in the POM.xml
