package chat.gui;

import chat.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Chat panel, displays messages
 *
 * @author Gary
 */
public class ChatPanel extends JPanel {

    private final ClientGUI parent;

    private int msgCount = 0;

    private JPanel msgsPanel;
    private JPanel container;
    private JScrollPane scroller;
    private ArrayList<RoundPanel> panelMessages;
    private final Color BACKGROUND = new Color(255, 255, 255);
    private final Color MSG_FROM_COLOR = new Color(196, 237, 251);
    private final Color MSG_ME_COLOR = new Color(229, 247, 253);
    private final Color MSG_PRIV_FROM = new Color(180, 220, 140);
    private final Color MSG_PRIV_ME = new Color(200, 247, 160);
    private final Color MSG_SERVER = new Color(250, 150, 150);
    private final Color GLOBAL_MSG_COLOR = new Color(150, 150, 150);
    private final Dimension SIZE = new Dimension(0, 80);
    private final Dimension ARC = new Dimension(20, 20);
    private final Dimension INPUT_FIELD_SIZE = new Dimension(0, 50);
    private final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    private final Font GLOBAL_MSG_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
    private final Insets TEXT_FIELD_INSETS = new Insets(5, 8, 5, 8);
    private final Insets ME_INSETS = new Insets(5, 40, 5, 5);
    private final Insets FROM_INSETS = new Insets(5, 5, 5, 40);
    private final Insets SERVER_MESSAGE_INSETS = new Insets(5, 40, 5, 40);
    private final EmptyBorder EMPTY_BORDER = new EmptyBorder(TEXT_FIELD_INSETS);

    /**
     * Init ChatPanel.
     *
     * @param parent Parent frame ClientGUI.
     */
    public ChatPanel(JFrame parent) {
        this.parent = (ClientGUI) parent;

        init();
        addListeners();
    }

    /**
     * Init GUI components.
     */
    private void init() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND);
        setPreferredSize(SIZE);

        //List of messages components
        panelMessages = new ArrayList<>();

        //Panel that displays message components
        msgsPanel = new JPanel();
        msgsPanel.setLayout(new GridBagLayout());
        msgsPanel.setBackground(BACKGROUND);

        //Allow message panel to be scrollable
        scroller = new JScrollPane(
                msgsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setBackground(BACKGROUND);
        scroller.setBorder(EMPTY_BORDER);
        scroller.setPreferredSize(INPUT_FIELD_SIZE);
        scroller.setBackground(BACKGROUND);
        scroller.setBorder(BorderFactory.createEmptyBorder());

        add(scroller, BorderLayout.CENTER);
    }

    private void addListeners() {
        //NONE
    }

    /**
     * Remove all message components from the list of message components.
     */
    protected void clear() {
        msgsPanel.removeAll();
    }

    /**
     * Method updates and creates the GUI required to show messages to and from
     * users. Selected user messages are shown only if at least one has been
     * selected.
     *
     * @param newMsgs List of message for the client user.
     */
    protected void updateTextArea(List<Message> newMsgs) {
        String sender;
        String receiver; //Default BROADCAST
        String message;
        byte[] image;

        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < newMsgs.size(); i++) {
            sender = newMsgs.get(i).getSender();
            receiver = newMsgs.get(i).getReceiver();
            message = newMsgs.get(i).getMessage();
            image = newMsgs.get(i).getImage();

            RoundPanel msgPanel;
            JTextArea msgLabel = new JTextArea();
            msgLabel.setFont(DEFAULT_FONT);
            msgLabel.setBorder(EMPTY_BORDER);
            msgLabel.setEditable(false);
            msgLabel.setOpaque(false);

            //Messges I sent
            if (parent.getUserName().equals(sender)) {
                msgLabel.setText(message);

                msgPanel = new RoundPanel(ARC, new FlowLayout(FlowLayout.RIGHT));
                msgPanel.setOpaque(false);

                if (receiver.equals(ClientGUI.BROADCAST)) {
                    //Public message I sent to everyone
                    msgPanel.username = ClientGUI.BROADCAST;

                    msgLabel.setText(message);
                    msgPanel.setBackground(MSG_ME_COLOR);
                    msgPanel.setForeground(MSG_ME_COLOR);
                } else {
                    //Private message I sent to receiver
                    msgPanel.username = receiver;

                    //Set the colour of the private messge to the same colour
                    // as the connected user colour when selected.
                    Color privMsgColor = null;
                    UserListItem item = getUserListItem(receiver);
                    if (item != null) {
                        privMsgColor = item.getItemColor();
                    }
                    msgLabel.setForeground(BACKGROUND);
                    msgPanel.setBackground(privMsgColor.darker());
                    msgPanel.setForeground(privMsgColor.darker());
                }
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                //gbc.gridy = msgCount;
                gbc.insets = ME_INSETS;
                gbc.fill = GridBagConstraints.HORIZONTAL;
            } else {

                //Messages to me
                msgLabel.setText(message);
                msgPanel = new RoundPanel(ARC, new FlowLayout(FlowLayout.LEFT));
                msgPanel.setOpaque(false);

                if (receiver.equals(ClientGUI.BROADCAST)) {
                    //Public messages send to everyone, give default colour
                    msgPanel.username = sender;
                    msgPanel.setBackground(MSG_FROM_COLOR);
                    msgPanel.setForeground(MSG_FROM_COLOR);
                } else {
                    //Private message, set colour to the user who sent it
                    msgPanel.username = sender;
                    Color privMsgColor = null;
                    UserListItem item = getUserListItem(sender);
                    if (item != null) {
                        privMsgColor = item.getItemColor();
                    }
                    msgPanel.setBackground(privMsgColor);
                    msgPanel.setForeground(privMsgColor);
                }
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                //gbc.gridy = msgCount;
                gbc.insets = FROM_INSETS;
                gbc.fill = GridBagConstraints.HORIZONTAL;
            }

            msgPanel.add(msgLabel, 0);

            //Add image
            if (image != null) {
                JLabel imageLabel = new JLabel();
                imageLabel.addMouseListener(imageLabelListener());
                imageLabel.setIcon(processImageBytes(image));
                msgPanel.add(imageLabel);
            }

            //Create timestamp of when I received the message
            String timeStamp = new SimpleDateFormat("HH:mm").format(new Date());

            //Message component formatting
            container = new JPanel();
            JLabel pm = null;
            if (parent.getUserName().equals(sender)) {
                //Messages I sent, put on the right side of panel
                container.setLayout(new FlowLayout(FlowLayout.RIGHT));

                if (receiver.equals(ClientGUI.BROADCAST)) {
                    //Do nothing
                } else {
                    //Private message, add who the message is sent to on the right
                    pm = new JLabel("to " + receiver);
                }
            } else {
                //Message sent to me, put ont the left side of panel
                container.setLayout(new FlowLayout(FlowLayout.LEFT));

                if (receiver.equals(ClientGUI.BROADCAST)) {
                    //Broadcast message
                    container.add(new JLabel(sender));
                } else {
                    //Private message, add PM to the left
                    container.add(new JLabel("[PM] " + sender));
                }
            }

            //Add msgPanel reference to list of messages
            panelMessages.add(msgPanel);

            //Add message content plus private message label
            container.add(msgPanel);
            if (pm != null) {
                container.add(pm);
            }

            //Add time stamp
            JLabel timeLabel = new JLabel(timeStamp);
            timeLabel.setFont(GLOBAL_MSG_FONT);
            timeLabel.setForeground(GLOBAL_MSG_COLOR);
            container.add(timeLabel);
            container.setOpaque(false);

            //Add message containter to scrollpane
            msgsPanel.add(container, gbc, 0);

            //msgCount++;
            //Need to refresh scrolling
            revalidate();
            repaint();
        }

        try {
            //Only show messages from selected users, if none selected then
            // show all messages.
            filterMessages();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show messages from selected users, if no users selected then show all
     * messages.
     *
     * @throws NullPointerException No messages.
     */
    protected void filterMessages() throws NullPointerException {
        ArrayList<String> userList = ((MessagePanel) getParent()).getUserListPanel().getSelectedUsers();
        Component[] msgs = msgsPanel.getComponents();

        //For al mesages
        ArrayList<RoundPanel> toShow = new ArrayList<>();
        for (RoundPanel msg : panelMessages) {
            String username = msg.username;

            //For all selected users
            if (userList.size() > 0) {

                int changed = 0;
                for (String selUsername : userList) {

                    if (username.equals(parent.getUserName())) {
                        //Show your messages
                        toShow.add(msg);
                        changed++;
                    } else if (username.equals(selUsername)) {
                        //Show selected user messages
                        toShow.add(msg);
                        changed++;
                    }
                }

                if (changed == 0) {
                    msg.getParent().setVisible(false);
                }

            } else {
                //Show all messages if no selected users
                msg.getParent().setVisible(true);
            }
        }

        for (RoundPanel comp : toShow) {
            comp.getParent().setVisible(true);
        }
    }

    /**
     * Resolve a string username to a UserListItem that represents the same user
     *
     * @param username Name of userListItem component to retreive.
     * @return
     */
    protected UserListItem getUserListItem(String username) {
        UserListItem userListItem = null;
        Component[] userList = ((MessagePanel) getParent()).getUserListPanel().getListPanel().getComponents();
        for (Component item : userList) {
            if (((UserListItem) item).getUsername().equals(username)) {
                //If it exists, return this...
                userListItem = (UserListItem) item;

                //Code should be this but I don't want to break anything
                //return (UserListItem) item;
            }
        }
        return userListItem;
    }

    /**
     * Method displays a specially formatted message that's added to the
     * messages panel. These cannot be hidden and are used to notify users of
     * important events.
     *
     * @param message Message to display as notification.
     */
    protected void addServerMessage(String message) {
        GridBagConstraints gbc = new GridBagConstraints();

        RoundPanel msgPanel;

        JLabel msgLabel = new JLabel();
        msgLabel.setFont(GLOBAL_MSG_FONT);
        msgLabel.setForeground(MSG_SERVER);
        msgLabel.setBorder(EMPTY_BORDER);

        msgLabel.setText(message);

        msgPanel = new RoundPanel(ARC, new FlowLayout(FlowLayout.CENTER));
        msgPanel.setOpaque(false);

        msgLabel.setText(message);
        msgPanel.setBackground(BACKGROUND);
        msgPanel.setForeground(BACKGROUND);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
//        gbc.gridy = msgCount;
        gbc.insets = SERVER_MESSAGE_INSETS;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        msgPanel.add(msgLabel);
        msgsPanel.add(msgPanel, gbc, 0);

//        msgCount++;
        revalidate();
        repaint();
    }

    /**
     * Convert image bytes to a ImageIcon.
     *
     * @param imgBytes An image represented as a byte array.
     * @return ImageIcon made from imgBytes.
     */
    private MyImageIcon processImageBytes(byte[] imgBytes) {
        MyImageIcon icon = null;

        try {
            // convert byte array back to BufferedImage
            InputStream in = new ByteArrayInputStream(imgBytes);
            Image fullsize = ImageIO.read(in);

            //Save fullsize instance
            icon = new MyImageIcon();
            icon.fullSize = fullsize;

            //Scale image
            Image scaled;
            scaled = fullsize.getScaledInstance(45, -1, Image.SCALE_SMOOTH);
            icon.setImage(scaled);
        } catch (Exception e) {
            //Broken
        }

        return (MyImageIcon) icon;
    }

    /**
     * When an image in the chat is pressed a Frame is shown, this method
     * specifys this action. The Frame is used to display the full size version
     * of the image.
     *
     * @return Mouse listener object that does the above.
     */
    private MouseListener imageLabelListener() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String message = ((JTextArea) ((RoundPanel) e.getComponent().getParent()).getComponent(0)).getText();
                MyImageIcon icon = (MyImageIcon) ((JLabel) e.getComponent()).getIcon();
                JFrame frame = new JFrame(message);

                frame.setLayout(new FlowLayout(FlowLayout.CENTER));
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setIconImage(icon.getImage());
                frame.getContentPane().setBackground(MSG_ME_COLOR);

                JLabel label = new JLabel();
                label.setIcon(new ImageIcon(icon.fullSize));

                frame.add(label);
                frame.pack();
                frame.setVisible(true);
            }
        };
    }
}
