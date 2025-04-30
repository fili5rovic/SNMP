package variant1.tree;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;


public class ColorDotTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        // Get the default rendering component
        JLabel label = (JLabel) super.getTreeCellRendererComponent(
                tree, value, selected, expanded, leaf, row, hasFocus);

        if (value instanceof ColoredTreeNode) {
            ColoredTreeNode node = (ColoredTreeNode) value;
            if (node.getColor() != null) {
                // Set the colored dot icon
                label.setIcon(new ColorDotIcon(node.getColor()));
            } else {
                // Reset to default icon for nodes without a color
                label.setIcon(getDefaultOpenIcon());
            }
        }

        return label;
    }
}
