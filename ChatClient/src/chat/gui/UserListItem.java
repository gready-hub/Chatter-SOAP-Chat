package chat.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Rounded Panel with Label centred.
 * This represents a user and has a random colour assigned to it.
 *
 * @author Gary
 */
public class UserListItem extends JPanel {

    private Dimension arcs;
    private JLabel label;
    private boolean selected;
    private boolean mouseOver;

    private final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    private Color BUTTON_USER = new Color(200, 247, 160);
    private Color BUTTON_USER_PRESSED = new Color(12, 175, 125);
    private Color BUTTON_USER_OVER = new Color(60, 188, 175);
    private final Color USER_TEXT_COLOR = new Color(0, 0, 0);
    private final Color BUTTON_TEXT_COLOR = new Color(255, 255, 255);

    /**
     * Initiate.
     * @param arcs Specify how large the rounding should be.
     * @param username Text to display in the item.
     */
    public UserListItem(Dimension arcs, String username) {
        super();
        this.arcs = arcs;
        label = new JLabel(username);

        init();
    }

    /**
     * Colour of this item.
     * @return Colour of item.
     */
    public Color getItemColor() {
        return BUTTON_USER;
    }

    /**
     * Set the selected property to true or false.
     * @param bool True = this component is selected, false if not.
     */
    public void setSelected(boolean bool) {
        selected = bool;
    }

    /**
     * Check if item is selected.
     * @return True if selected, false otherwise.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Return the label text, username.
     * @return Username of this item.
     */
    public String getUsername() {
        return ((JLabel) getComponent(0)).getText();
    }

    /**
     * Get the JLabel that contains the username String.
     * @return Label containing username string.
     */
    public JLabel getLabel() {
        return (JLabel) getComponent(0);
    }

    @Override
    public Component add(Component c) {
        return add(c, 0);
    }

    /**
     * Init GUI components of this JPanel and their event listeners.
     */
    private void init() {
        selected = false;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setMinimumSize(new Dimension(150, 30));
        setMaximumSize(new Dimension(200, 30));
        setPreferredSize(new Dimension(150, 30));

        //Random color generator, pastel colours
        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = (random.nextInt(3000) + 1000) / 10000f;
        final float luminance = 0.9f;
        final Color color = Color.getHSBColor(hue, saturation, luminance);
        BUTTON_USER = color;
        BUTTON_USER_OVER = color.darker();
        BUTTON_USER_PRESSED = color.darker().darker();

        setBackground(BUTTON_USER);
        setForeground(BUTTON_TEXT_COLOR);
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Set color on click and flip selected boolean
                selected = !selected;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //Set color on entering
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //Set color on leaving
                mouseOver = false;
                repaint();
            }
        });

        label.setFont(DEFAULT_FONT);
        label.setForeground(USER_TEXT_COLOR);
        label.setPreferredSize(new Dimension(150, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label, 0);
    }

    /**
     * Overridden paint method to set the colour of this item with response
     * to mouse events. Mouse over and isSelected are concerned.
     * @param g Graphics to paint.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        if (selected) {
            //Set color
            setBackground(BUTTON_USER_PRESSED);
            setForeground(BUTTON_TEXT_COLOR);
            label.setForeground(BUTTON_TEXT_COLOR);
        } else if (mouseOver) {
            setBackground(BUTTON_USER_OVER);
            setForeground(BUTTON_TEXT_COLOR);
            label.setForeground(BUTTON_TEXT_COLOR);
        } else {
            setBackground(BUTTON_USER);
            setForeground(BUTTON_TEXT_COLOR);
            label.setForeground(USER_TEXT_COLOR);
        }

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border
    }
}
