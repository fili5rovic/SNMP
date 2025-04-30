package variant5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import router.RouteChangeListener;
import router.Router;
import util.MyJTable;

import java.awt.BorderLayout;
import java.awt.Color;

public class BGPFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel model;
	private MyJTable table;
	private JComboBox<String> routeChoiceBox;
	
	private RouteChangeListener routeChangeListener;
	
	private String[] columns;
	
	public BGPFrame(String name, String[] columns) {
		setTitle(name);
		this.columns = columns;
		
		this.model = new DefaultTableModel(columns, 0);
        this.table = new MyJTable(model);
        
        this.setLayout(new BorderLayout());
        
        routeChoiceBox = new JComboBox<>(new String[]{"R1", "R2", "R3"});
        routeChoiceBox.setSelectedIndex(0);
        routeChoiceBox.addActionListener(e -> {
        	Router r = Router.getRouters()[routeChoiceBox.getSelectedIndex()];
            routeChangeListener.onChange(r);
        });
        
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Router:"));
        topPanel.add(routeChoiceBox);
        this.add(topPanel, BorderLayout.NORTH);
        
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        
        resize();
        
        this.setSize(1400, 600);
        this.setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// for v4
	public void setData(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        model.setRowCount(0);
        
        for (Object[] row : data) {
            model.addRow(row);
        }
        table.resize();
      
    }
	
	
	public int getColumnNum() {
		return this.columns.length;
	}
	
	public void resize() {
		table.resize();
	}
	
	public void clearTable() {
		model.setRowCount(0);
	}
	
	public DefaultTableModel getModel() {
		return this.model;
	}
	
	public JTable getTable() {
		return table;
	}

	public void setRouteChangeListener(RouteChangeListener bgpMonitor) {
		this.routeChangeListener = bgpMonitor;
	}
}
