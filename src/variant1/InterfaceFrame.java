package variant1;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.HashSet;

import router.RouteChangeListener;
import router.Router;
import variant1.tree.ColorDotTreeCellRenderer;

public class InterfaceFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTree tree;
    
    private static final String[] properties = 
    		new String[] {"Opis","Tip","MTU","Brzina","Fizicka Adresa","Administrativni status","Operativni status"};

    public InterfaceFrame(String name) {
        setTitle(name);
        this.setLayout(new BorderLayout());
        
        DefaultMutableTreeNode nodeRoot = new DefaultMutableTreeNode("Loading...");
        tree = new JTree(nodeRoot);
        tree.setCellRenderer(new ColorDotTreeCellRenderer());
        
        JScrollPane treeScrollPane = new JScrollPane(tree);
        this.add(treeScrollPane, BorderLayout.CENTER);

        this.setSize(400, 600);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void updateTree(DefaultMutableTreeNode newRoot) {
    	HashSet<Integer> expandedPathIndices = new HashSet<>();
        for (int i = 0; i < tree.getRowCount(); i++) {
            TreePath path = tree.getPathForRow(i);
            if (tree.isExpanded(path)) {
                expandedPathIndices.add(i);
            }
        }
    	
    	tree.setModel(new DefaultTreeModel(newRoot));
    	
    	for (int i = 0; i < tree.getRowCount(); i++) {
            if(expandedPathIndices.contains(i)) {
            	TreePath path = tree.getPathForRow(i);
            	tree.expandPath(path);
            }
    	}
    }
}
