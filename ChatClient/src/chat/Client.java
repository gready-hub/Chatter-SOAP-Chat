package chat;

import java.net.ConnectException;
import java.util.List;
import javax.xml.ws.WebServiceException;

/**
 * Client, This is used as a middle-man between the GUI and ServerService.
 * Client simply places all WebMethods of ServerService into java methods that
 * have error detection.
 *
 * @author Gary
 */
public class Client {

    ServerService service;
    Server port;

    /**
     * Constructor connects to a new ServerService and gets the ServerPort.
     *
     * @throws WebServiceException
     */
    public Client() throws WebServiceException {
        service = new ServerService();
        port = service.getServerPort();
    }

    /**
     * Add a candidate user with username to the list of connected users, this
     * allows this user to send and receive messages.
     * @param username Candidate username.
     * @return True if the candidate user joined the connected user list.
     * @throws ConnectException Thrown if it could not connect to service.
     */
    public boolean join(String username) throws ConnectException {
        return port.join(username);
    }

    /**
     * Candidate user to leave the server.
     * @param username Candidate username.
     * @throws ConnectException Thrown if it could not connect to service
     */
    public void leave(String username) throws ConnectException {
        port.leave(username);
    }

    /**
     * Send a message to all users from the candidate sender.
     * @param sender Username of sender.
     * @param message Message to send to all users.
     * @param image Image to send to all users.
     * @return True if message was sent to all users.
     * @throws ConnectException Thrown if it could not connect to service.
     */
    public boolean broadcast(String sender,
            String message, byte[] image) throws ConnectException {

        return port.broadcast(sender, message, image);
    }

    /**
     * Send a message and image from the sender to the receiver
     * @param username Candidate sender.
     * @param receiver Candidate receiver.
     * @param message Message to be sent to receiver.
     * @param image Image to be sent to receiver.
     * @return Server result, true if message was sent
     * @throws ConnectException Thrown if it could not connect to service.
     */
    public boolean privateMessage(String username, String receiver,
            String message, byte[] image) throws ConnectException {

        return port.privateMessage(username, receiver, message, image);
    }

    /**
     * Make a candidate user admin.
     * Note: This is not encrypted, nor is it encoded.
     * @param username Candidate username.
     * @param pass Candidate password.
     * @return Server response, true if candidate was made admin.
     * @throws ConnectException Thrown if it could not connect to service.
     */
    public boolean adminLogon(
            String username, String pass) throws ConnectException {

        return port.adminLogon(username, pass);
    }

    /**
     * Logout a candidate user.
     * @param username Candidate username.
     * @return Server response, true if candidate user is no longer admin.
     * @throws ConnectException Thrown if it could not connect to service.
     */
    public boolean adminLogoff(String username) throws ConnectException {
        return port.adminLogoff(username);
    }

    /**
     * Get all the connected users of the ServerService.
     * @param username Candidate username.
     * @return List of all users connected to the server.
     * @throws ConnectException Thrown if it could not connect to service.
     */
    public List<User> connectedUsers(
            String username) throws ConnectException {

        return port.connectedUsers(username);
    }

    /**
     * Collect all message for a candidate user.
     * @param username Candidate username.
     * @return List of Message objects that have been sent to the candidate user.
     */
    public List<Message> listen(String username) {
        return port.listen(username);
    }
}
