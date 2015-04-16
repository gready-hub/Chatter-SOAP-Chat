package chat.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Message panel for ClientGUI
 *
 * @author Gary
 */
class MessagePanel extends JPanel {

    private final ClientGUI parent;

    private UserListPanel userListPanel;
    private InputPanel inputPanel;
    private ChatPanel chatPanel;

    private final Color BACKGROUND = new Color(228, 236, 239);

    public MessagePanel(JFrame parent) {
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

        setLayout(gridbag);
        setBackground(BACKGROUND);

        userListPanel = new UserListPanel(parent);
        inputPanel = new InputPanel(parent);
        chatPanel = new ChatPanel(parent);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(0, 0, 0, 1);
        add(userListPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 1, 0);
        add(chatPanel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        add(inputPanel, c);
    }

    private void addListeners() {
        //TODO
    }

    protected UserListPanel getUserListPanel() {
        return userListPanel;
    }

    protected InputPanel getInputPanel() {
        return inputPanel;
    }

    protected ChatPanel getChatPanel() {
        return chatPanel;
    }
}
