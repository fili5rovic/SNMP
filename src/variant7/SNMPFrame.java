package variant7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import router.Router;
import util.MyJTable;

import java.awt.*;
import java.util.HashMap;

public class SNMPFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private static String[] columnNames = {"InPackets", "OutPackets", "GetNum", "SetNum","TrapNum","BadCommunityNum"};
    
	private HashMap<Router, MyJTable> tableMap = new HashMap<>();

    public SNMPFrame() {
        setTitle("SNMP Stats");
        setSize(600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        int numTables = Router.getRouters().length;
        setLayout(new GridLayout(2 * numTables, 1)); 
        
        for (Router r : Router.getRouters()) {
        	MyJTable table = createTable(null);
            JScrollPane scrollPane = new JScrollPane(table);
            
            tableMap.put(r, table);
            
            JLabel title = new JLabel(r.getName());
            title.setHorizontalAlignment(SwingConstants.CENTER);
            
            add(title);
            add(scrollPane);
            
        }

        setVisible(true);
    }
    
    public void setData(Router r, Object[][] data) {
    	MyJTable table = tableMap.get(r);
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        model.setRowCount(0);
        
        for (Object[] row : data) {
            model.addRow(row);
        }
        table.resize();
    }
    
    public static int getColumnNum() {
    	return columnNames.length;
    }

    private MyJTable createTable(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        MyJTable table = new MyJTable(model);
        table.resize();
        return table;
    }
}
