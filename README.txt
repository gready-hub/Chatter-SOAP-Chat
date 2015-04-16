=======================1/6============================
Title: SOAP Chat, web service coursework. (Chatter)
Author: Gary Read
Student Number: 662193
Instution: Swansea University, College of Science.
Contact: garyyread[at]gmail[dot]com

=======================2/6============================
Running projects from Netbeans:

	1. Open projects
		File > Open project > ".../ChatClient"
		File > Open project > ".../ChatServer"

	2. Deploy chat server
		ChatServer > Deploy 
	
	3. Run chat client
		ChatClient > Run ClientGUI.main(String[] args)

=======================3/6============================
Chat client features:
	
	1. Login page
		> Type a username into the text field.
		- Press enter or Select the login button to login.
		- Username cannot be whitespace, empty, or already exist.
		- Error message may be displayed.
	
	2. Chat page
		- Pressing the logout button does what you expect. Returns you to the 
			login page again.
		- Closing the window at anytime will make the user logout.
	
	3. Global messaging
		- You may type into the bottom text area, pressing enter will send
			the text area text to all connected users.
		- Pressing the "Add image" button will display a file dialog, choose
			a file, on approval your image will display as a thumbnail on 
			the button. Pressing the enter key inside the text area will 
			send your image and message.
		
	3. Private Messaging
		> Select ONE or MORE coloured labels on the left-hand side.
		- Each coloured label, which has some text in represents a connected 
			user.
		- You may select and unselect multiple users.
		- When sending a message, just as before, only those users who are
			selected will have a message sent to them.
	
	4. Messages view
		- Messages are coloured light blue if they're Global messages.
		- Messages are coloured the colour of the connected user label (left in frame)
		- Messages are hidden from view if the sender does not match any 
			of the selected connected users (left in frame)

=======================4/6============================
Chat Server features:

	1. Join/Leave
		- Synchnoised
		- authentication checking
		
	2. Admin on/off 
		> Not implemented in the Client.
		- Synchnoised
		- authentication checking
		
	3. Connected Users
		- Synchnoised
		- authentication checking
		
	4. Message/Private Message
		- Synchnoised
		- Send images
		- Send text
		- authentication checking

=======================5/6============================
Java file Locations
	Client
		\ChatClient\src\chat\Client.java
		\ChatClient\src\chat\gui\*.java
	
	Server
		\ChatServer\src\java\chat\server\*.java
		
=======================6/6============================
Service WAR Location:
	\ChartServer\dist\ChatServer.war
	
Client Application Location
	\ChatClient\dist\ChatClient.jar

Web service:
	chat.server.Server;
	chat.server.User;
	chat.server.Message;
	
Web service client:
	chat.Client;
	chat.gui.*;
	
WSDL Location:
	http://localhost:8080/ChatServer/ServerService?wsdl

=======================END============================