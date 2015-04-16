package chat.gui;

import chat.User;
import chat.Message;
import chat.Client;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ConnectException;
import java.net.URL;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.ws.WebServiceException;

/**
 * Client Graphical User Interface This is a JFrame that contains all of the
 * components used to interact with the server.
 *
 * @author Gary
 */
public class ClientGUI extends JFrame {

    /**
     * Represents the receiver when sending to all users
     */
    public static String BROADCAST = "BROADCAST";

    /**
     * Image (relative) location of the icon
     */
    protected static final String IMAGE_LOC = "/META-INF/logo.png";

    private final String LOGIN_CARD = "0";
    private final String MESSAGE_CARD = "1";

    private String userName;
    private final Thread updateThread;

    private boolean loggedin;
    private boolean terminate;

    private Client client;
    private final JPanel cards;

    /**
     * Extends JPanel, this is the interface that is shown when a user needs to
     * enter a username to login.
     */
    public final LoginPanel loginPanel;
    private final MessagePanel messagePanel;

    private final Dimension WINDOW_SIZE = new Dimension(800, 600);
    private final Dimension MIN_SIZE = new Dimension(600, 400);

    /**
     * Constructor, init components, create a new Client to connect though to
     * the server. Create thread used to poll the server for messages and
     * connected users.
     */
    public ClientGUI() {
        loggedin = false;
        terminate = false;

        userName = null;

        //Connect to server though Client object
        client = null;
        try {
            client = new Client();
        } catch (WebServiceException ex) {
            System.out.println(getClass() + "ClientGUI():new Client() failed");
        }

        cards = new JPanel(new CardLayout());
        loginPanel = new LoginPanel(this);
        messagePanel = new MessagePanel(this);

        //Polling thread
        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!terminate) {
                    //Sleep my pretty
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {

                    }

                    //Update title
                    if (!loggedin) {
                        setTitle("Chatter");
                    } else {
                        setTitle("Chatter - " + userName);
                    }

                    //Update!
                    if (loggedin) {
                        poll();
                    }
                }
            }
        });

        init();
    }

    /**
     * Init GUI components.
     */
    private void init() {
        setTitle("Chatter");
        setSize(WINDOW_SIZE);
        setMinimumSize(MIN_SIZE);
        setPreferredSize(WINDOW_SIZE);
        setResizable(false);

        //JFrame image icon
        try {
            URL url = getClass().getResource(ClientGUI.IMAGE_LOC);
            Image img = Toolkit.getDefaultToolkit().getImage(url);
            setIconImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Center frame on the screen
        setLocationRelativeTo(this);
        setResizable(true);

        // On close, user leave the chat then close.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        //Card layout for panels, used to flip between the two
        cards.add(loginPanel, LOGIN_CARD);
        cards.add(messagePanel, MESSAGE_CARD);

        add(cards, BorderLayout.CENTER);

        pack();

        //Start polling
        updateThread.start();
    }

    /**
     * Try to log the user out and then dispose JFrame.
     */
    @Override
    public void dispose() {
        terminate = true;
        try {
            logout();
        } catch (Exception e) {
            System.out.println(getClass() + ".dispose():ConnectionError");
        } finally {
            super.dispose();
        }

    }

    /**
     * Attempt to get username, connected users and user messages from the
     * server.
     */
    public void poll() {
        try {
            messagePanel.getUserListPanel().updateUserName(userName);
            messagePanel.getUserListPanel().updateListPanel(getConnectedUsers());
            messagePanel.getChatPanel().updateTextArea(listen());
        } catch (Exception e) {
            System.out.println(getClass() + ".poll():ConnectionError");
        }
    }

    /**
     * Get the current connected user for this Client.
     *
     * @return Users username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Login with a username set in the text field.
     *
     * @param user Candidate username.
     * @return True if candidate joined the chat.
     * @throws ConnectException Server connection failed.
     */
    public boolean login(String user) throws ConnectException {
        this.userName = user;

        if (client.join(userName)) {
            client.adminLogon(userName, "pass");

            loggedin = true;

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, MESSAGE_CARD);
            return true;
        } else {
            loggedin = false;

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, LOGIN_CARD);
            return false;
        }
    }

    /**
     * Logout client current user.
     *
     * @throws ConnectException Server connection failed.
     */
    public void logout() throws ConnectException {
        client.leave(userName);
        loggedin = false;

        messagePanel.getChatPanel().clear();

        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, LOGIN_CARD);
    }

    /**
     * Return a list of all the chats connected users.
     *
     * @return @throws ConnectException Server connection failed.
     */
    public List<User> getConnectedUsers() throws ConnectException {
        return client.connectedUsers(userName);
    }

    /**
     * Send a message from current user to all users.
     *
     * @param message Message to send to all users.
     * @param image Image to sent to all users.
     * @return True if message was sent.
     * @throws ConnectException Server connection failed.
     */
    public boolean broadcast(
            String message, byte[] image) throws ConnectException {

        return client.broadcast(userName, message, image);
    }

    /**
     * Send message to a specific user.
     * @param receiver Candidate receiver.
     * @param message Message to send to receiver.
     * @param image Image to send to receiver.
     * @return True if message sent.
     * @throws ConnectException Server connection failed.
     */
    public boolean privateMessage(String receiver,
            String message, byte[] image) throws ConnectException {

        return client.privateMessage(userName, receiver, message, image);
    }

    /**
     * Get all message for the client user.
     * @return @throws ConnectException Server connection failed.
     */
    public List<Message> listen() throws ConnectException {
        return client.listen(userName);
    }

    /**
     * Main method, initiates the ClientGUI...
     * @param args Not used.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClientGUI clientGUI_1 = new ClientGUI();
                clientGUI_1.setVisible(true);
            }
        });
    }
}
