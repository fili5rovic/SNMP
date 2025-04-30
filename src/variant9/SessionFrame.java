package variant9;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import util.MyJTable;

public class SessionFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MyJTable tcpTable;
	private DefaultTableModel tcpModel;
	private static String[] tcpCols = new String[] {"Local IP", "Remote IP","Local Port","Remote port"};
	
	private MyJTable udpTable;
	private DefaultTableModel udpModel;
	private static String[] udpCols = new String[] {"Address","Port number"};

	public SessionFrame() {
		super();
		
		this.setTitle("Session monitor");
		this.setLayout(new BorderLayout());
		
		this.tcpModel = new DefaultTableModel(tcpCols, 0);
		this.tcpTable = new MyJTable(tcpModel);
		this.tcpTable.resize();
		
		this.udpModel = new DefaultTableModel(udpCols, 0);
		this.udpTable = new MyJTable(udpModel);
		this.udpTable.resize();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(new JScrollPane(tcpTable));
		panel.add(new JScrollPane(udpTable));
		
		this.setSize(300,300);
		this.add(panel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setTCPData(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) tcpTable.getModel();
        
        model.setRowCount(0);
        
        for (Object[] row : data) {
            model.addRow(row);
        }
        tcpTable.resize();
    }
	
	public void setUDPData(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) udpTable.getModel();
        
        model.setRowCount(0);
        
        for (Object[] row : data) {
            model.addRow(row);
        }
        udpTable.resize();
    }
	public static int getTCPColumnNum() {
		return tcpCols.length;
	}
	
	public static int getUDPColumnNum() {
		return udpCols.length;
	}
}
