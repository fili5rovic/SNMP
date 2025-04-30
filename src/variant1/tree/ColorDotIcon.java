package variant1.tree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ColorDotIcon implements Icon {
    private final int size = 14; // Diameter of the dot
    private final Color color;

    public ColorDotIcon(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}
