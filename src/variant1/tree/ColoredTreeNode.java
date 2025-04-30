package variant1.tree;

import java.awt.Color;

import javax.swing.tree.DefaultMutableTreeNode;

public class ColoredTreeNode extends DefaultMutableTreeNode {
    private static final long serialVersionUID = 1L;
	private final Color color;

    public ColoredTreeNode(String userObject, Color color) {
        super(userObject);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
