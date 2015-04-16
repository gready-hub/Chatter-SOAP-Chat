package chat.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Rounded Panel with Label centred
 *
 * @author Gary
 */
public class RoundPanel extends JPanel {

    private Dimension arcs;

    /**
     * Username of a connected User
     */
    public String username;

    /**
     * Init RoundPanel, calls super().
     * @param arcs Roundness of label.
     * @param layout Layout of panel.
     */
    public RoundPanel(Dimension arcs, LayoutManager layout) {
        super();
        setLayout(layout);
        this.arcs = arcs;
    }

    /**
     * Paint rounded border.
     * @param g Graphics to paint.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border
    }
}
