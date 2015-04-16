package chat.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Input Panel is a text field and button, the button is used to show a file
 * chooser to send images. The text field is used to send message.
 *
 * @author Gary
 */
public class InputPanel extends JPanel {

    private final ClientGUI parent;

    private JTextArea inputField;
    private JScrollPane scroller;
    private JButton sendImageButton;
    private JFileChooser filePicker;
    private final Color BACKGROUND = new Color(255, 255, 255);
    private final Color BACKGROUND_ON_FOCUS = new Color(229, 247, 253);
    private final Color SELECTION_COLOR = new Color(125, 192, 254);
    private final Color BUTTON_COLOR = new Color(0, 175, 240);
    private final Color BUTTON_COLOR_ON_HOVER = new Color(12, 125, 175);
    private final Dimension SIZE = new Dimension(0, 80);
    private final Dimension INPUT_FIELD_SIZE = new Dimension(200, 35);
    private final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    private final Insets TEXT_FIELD_INSETS = new Insets(5, 8, 5, 8);
    private final EmptyBorder EMPTY_BORDER = new EmptyBorder(TEXT_FIELD_INSETS);

    /**
     * Input Panel is a text field and button, the button is used to show a file
     * chooser to send images. The text field is used to send message.
     *
     * @param parent Parent frame, ClientGUI object.
     */
    public InputPanel(JFrame parent) {
        this.parent = (ClientGUI) parent;

        init();
        addListeners();
    }

    /**
     * Init GUI components of this JPanel.
     */
    private void init() {
        setBackground(BACKGROUND);
        setPreferredSize(SIZE);
        setLayout(new BorderLayout());

        //File chooser dialog
        filePicker = new JFileChooser();
        filePicker.setDialogTitle("Add Image");

        //Image sending button
        sendImageButton = new JButton("Add Image");
        sendImageButton.setBackground(BUTTON_COLOR);
        sendImageButton.setFont(DEFAULT_FONT);
        sendImageButton.setBorderPainted(false);

        //Message content text field
        inputField = new JTextArea();
        inputField.setFont(DEFAULT_FONT);
        inputField.setBorder(EMPTY_BORDER);
        inputField.setBackground(BACKGROUND);
        inputField.setSelectionColor(SELECTION_COLOR);
        inputField.setLineWrap(true);
        inputField.setFocusable(true);

        //Allow inputField to scroll
        scroller = new JScrollPane(
                inputField,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBackground(BACKGROUND);
        scroller.setBorder(EMPTY_BORDER);
        scroller.setPreferredSize(INPUT_FIELD_SIZE);

        add(sendImageButton, BorderLayout.LINE_END);
        add(scroller, BorderLayout.CENTER);
    }

    private void addListeners() {
        //Change colour when entering text area
        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                inputField.setBackground(BACKGROUND_ON_FOCUS);
                scroller.setBackground(BACKGROUND_ON_FOCUS);
            }

            //Change colour when entering text area
            @Override
            public void focusLost(FocusEvent e) {
                inputField.setBackground(BACKGROUND);
                scroller.setBackground(BACKGROUND);
            }
        });

        //Attempt to send message to all selected users, if no user selected 
        // then we simply send  message to everyone.
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    try {
                        ArrayList<String> selUsers = ((MessagePanel) getParent()).getUserListPanel().getSelectedUsers();

                        if (selUsers.size() < 1) {
                            parent.broadcast(inputField.getText(), getImageBytes());
                        } else {
                            for (String user : selUsers) {
                                parent.privateMessage(user, inputField.getText(), getImageBytes());
                            }
                        }
                    } catch (Exception ex) {
                        ((MessagePanel) getParent()).getChatPanel().addServerMessage("MESSAGE NOT SEND");
                        System.out.println(getClass() + ".inputField.keyTyped():ConnectionError");
                    } finally {
                        clear();
                    }
                }
            }
        });

        //Button, when clicked show file chooser and change the button icon
        // to the selected image file
        sendImageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                filePicker.showOpenDialog(getParent());

                File file = filePicker.getSelectedFile();

                if (file == null) {
                    sendImageButton.setText("Add Image");
                    sendImageButton.setIcon(null);
                } else {
                    sendImageButton.setText("");
                    sendImageButton.setIcon(getImageIcon());
                }
            }
        });

        //User notification when mouse over image button
        sendImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Set color
                sendImageButton.setBackground(BUTTON_COLOR_ON_HOVER);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //Set color
                sendImageButton.setBackground(BUTTON_COLOR_ON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //Set color
                sendImageButton.setBackground(BUTTON_COLOR);
            }
        });
    }

    /**
     * Clear the text area and send image button icon. Called when sending a
     * message.
     */
    protected void clear() {
        inputField.setText("");
        filePicker.setSelectedFile(null);
        sendImageButton.setText("Add Image");
        sendImageButton.setIcon(null);
    }

    /**
     * Get the text from the message text area.
     *
     * @return Return the text in the message text area.
     */
    protected String getInputText() {
        return inputField.getText();
    }

    /**
     * Convert a selected image to a byte[] to be sent to the server.
     *
     * @return Selected image file bytes.
     */
    protected byte[] getImageBytes() {
        byte[] imageInByte = null;
        File file = filePicker.getSelectedFile();

        try {
            String name = file.getName();
            String type = name.substring(name.lastIndexOf(".") + 1);

            BufferedImage originalImage = ImageIO.read(file);

            // convert BufferedImage to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, type, baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();

        } catch (IOException | NullPointerException e) {
            imageInByte = null;
        }
        return imageInByte;
    }

    /**
     * Get selected image from file, set fullsize version and create a thumbnail
     * version, this is encapsulated in a MyImageIcon.
     *
     * @return Selected image from file as a MyImageIcon.
     */
    protected MyImageIcon getImageIcon() {
        MyImageIcon image = null;
        File file = filePicker.getSelectedFile();

        try {
            //Get image and resize
            BufferedImage bigImg = ImageIO.read(file);
            Image smallImg = bigImg.getScaledInstance(45, -1, Image.SCALE_SMOOTH);

            image = new MyImageIcon();
            image.fullSize = bigImg;
            image.setImage(smallImg);
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            image = null;
        }
        return image;
    }
}
