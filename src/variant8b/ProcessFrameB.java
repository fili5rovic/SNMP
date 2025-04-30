package variant8b;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import router.Router;
import util.MyJTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ProcessFrameB extends JFrame {

    private static final long serialVersionUID = 1L;

    private static String[] columnNames = {"Name", "Used", "Free", "5sec", "1min", "5min"};

    private HashMap<Router, MyJTable> tableMap = new HashMap<>();

    private JButton setIntervalButton;
    private JTextField intervalField;
    
    public ProcessFrameB(Router[] routers) {
        setTitle("Process Monitor");
        setSize(500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Top panel for text field and button
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align to the left for a better look
        topPanel.setPreferredSize(new Dimension(getWidth(), 50)); // Ensure visibility

        JLabel intervalLabel = new JLabel("Polling Interval (seconds):");
        intervalField = new JTextField(10);
        setIntervalButton = new JButton("Set Interval");

        topPanel.add(intervalLabel);
        topPanel.add(intervalField);
        topPanel.add(setIntervalButton);

        setIntervalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = intervalField.getText();
                try {
                    int seconds = Integer.parseInt(text);
                    System.out.println("Polling interval set to: " + seconds + " seconds");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ProcessFrameB.this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Main content layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2 * routers.length, 1));

        for (Router r : routers) {
            MyJTable table = createTable(null);
            JScrollPane scrollPane = new JScrollPane(table);

            tableMap.put(r, table);

            JLabel title = new JLabel(r.getName());
            title.setHorizontalAlignment(SwingConstants.CENTER);

            mainPanel.add(title);
            mainPanel.add(scrollPane);
        }

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

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
    
    public JButton getIntervalButton() {
    	return setIntervalButton;
    }
    public String getInputFromField() {
    	return intervalField.getText();
    }

    private MyJTable createTable(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        MyJTable table = new MyJTable(model);
        table.resize();
        return table;
    }
}
