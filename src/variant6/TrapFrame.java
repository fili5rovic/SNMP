package variant6;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import util.MyJTable;

public class TrapFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MyJTable table;
	private DefaultTableModel model;
	
	private static final String[] columns = new String[] {"Trap", "Router","Vreme"};

	public TrapFrame() {
		setTitle("Trap handler");
		setSize(500, 300);
		
		model = new DefaultTableModel(columns, 0);
		table = new MyJTable(model);
		
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addRow(Object[] data) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.insertRow(0, new Vector<>(java.util.Arrays.asList(data))); // Convert Object[] to Vector
	    table.resize();
	}

	
	
}
