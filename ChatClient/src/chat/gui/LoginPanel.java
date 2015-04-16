package chat.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Login panel for ClientGUI
 *
 * @author Gary
 */
public class LoginPanel extends JPanel {

    /**
     *
     */
    public ClientGUI parent;

    private JLabel loginErrorLabel;
    private RoundPanel loginButton;
    private JTextField loginTextField;
    private JLabel logo;

    private final int COMPONENT_WIDTH = 200;
    private final int IMAGE_SCALE = 4;
    private final Color BUTTON_COLOR = new Color(12, 125, 175);
    private final Color BUTTON_TEXT_COLOR = new Color(255, 255, 255);
    private final Color ERROR_TEXT_COLOR = new Color(255, 255, 255);
    private final Color BACKGROUND_COLOR = new Color(0, 175, 240);
    private final Dimension BUTTON_SIZE = new Dimension(COMPONENT_WIDTH, 35);
    private final Dimension BUTTON_ARC = new Dimension(40, 40);
    private final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    private final Insets TEXT_FIELD_INSETS = new Insets(5, 8, 5, 8);
    private final Insets LAYOUT_INSETS = new Insets(10, 0, 10, 0);
    private final String LOGIN_BUTTON_TEXT = "Login";
    private final String LOGIN_ERROR_TEXT = "Login failed!";

    /**
     *
     * @param parent
     */
    public LoginPanel(JFrame parent) {
        this.parent = (ClientGUI) parent;

        init();
        addListeners();
    }

    /**
     * Init GUI components of this panel.
     */
    private void init() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.insets = LAYOUT_INSETS;

        setLayout(gridbag);
        setBackground(BACKGROUND_COLOR);

        //Error message label
        loginErrorLabel = new JLabel(LOGIN_ERROR_TEXT);
        loginErrorLabel.setFont(DEFAULT_FONT);
        loginErrorLabel.setForeground(ERROR_TEXT_COLOR);
        loginErrorLabel.setVisible(false);

        //Get and set image icon to display.
        try {
            URL url = getClass().getResource(ClientGUI.IMAGE_LOC);
            Image img = Toolkit.getDefaultToolkit().getImage(url);

            //Image img = ImageIO.read(new File(IMAGE_LOC));
            img = img.getScaledInstance(
                    100, -1, Image.SCALE_SMOOTH);
            logo = new JLabel(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Login button
        loginButton = new RoundPanel(BUTTON_ARC, new GridBagLayout());
        loginButton.setOpaque(false);
        loginButton.setForeground(BUTTON_COLOR);
        loginButton.setBackground(BUTTON_COLOR);
        JLabel jb = new JLabel(LOGIN_BUTTON_TEXT);
        jb.setFont(DEFAULT_FONT);
        jb.setForeground(BUTTON_TEXT_COLOR);
        loginButton.setPreferredSize(BUTTON_SIZE);
        loginButton.add(jb);

        //Username text field
        loginTextField = new JTextField();
        loginTextField.setFont(DEFAULT_FONT);
        loginTextField.setPreferredSize(BUTTON_SIZE);
        EmptyBorder eb = new EmptyBorder(TEXT_FIELD_INSETS);
        loginTextField.setBorder(eb);

        c.gridx = 0;
        c.gridy = 0;
        add(logo, c);

        c.gridx = 0;
        c.gridy = 1;
        add(loginTextField, c);

        c.gridx = 0;
        c.gridy = 3;
        add(loginButton, c);

        c.gridx = 0;
        c.gridy = 4;
        add(loginErrorLabel, c);
    }

    private void addListeners() {
        //User pressed enter in the username textfield, try to login
        loginTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    attemptLogin();
                }
            }
        });

        //User pressed the login button, try to login.
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                attemptLogin();
            }
        });
    }

    /**
     * Method tries to login to the server and handles exceptions. If login is
     * successful display the Messaging panel so the user can send message,
     * otherwise the login panel is shown again.
     */
    public void attemptLogin() {
        try {
            if (parent.login(loginTextField.getText())) {
                loginErrorLabel.setVisible(false);
            } else {
                loginErrorLabel.setVisible(true);
                loginErrorLabel.setText(LOGIN_ERROR_TEXT);
                loginButton.setForeground(ERROR_TEXT_COLOR);
            }
        } catch (Exception ex) {
            loginErrorLabel.setVisible(true);
            loginErrorLabel.setText("...Connection Error...");
            loginErrorLabel.setForeground(Color.RED);
            System.out.println(getClass() + ".attemptLogin():ConnectionError");
        }
    }
}
