package variant8;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import router.Router;
import util.GraphPanel;
import util.GraphPanelThree;
import util.MyJTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ProcessFrame extends JFrame {
    
    // Constants for Processor
    public static final String R1_PROCESSOR_USAGE = "R1_PROCESSOR_USAGE";
    public static final String R1_PROCESSOR_FREE = "R1_PROCESSOR_FREE";
    public static final String R1_5SEC = "R1_5SEC";
    public static final String R1_1MIN = "R1_1MIN";
    public static final String R1_5MIN = "R1_5MIN";

    public static final String R2_PROCESSOR_USAGE = "R2_PROCESSOR_USAGE";
    public static final String R2_PROCESSOR_FREE = "R2_PROCESSOR_FREE";
    public static final String R2_5SEC = "R2_5SEC";
    public static final String R2_1MIN = "R2_1MIN";
    public static final String R2_5MIN = "R2_5MIN";

    public static final String R3_PROCESSOR_USAGE = "R3_PROCESSOR_USAGE";
    public static final String R3_PROCESSOR_FREE = "R3_PROCESSOR_FREE";
    public static final String R3_5SEC = "R3_5SEC";
    public static final String R3_1MIN = "R3_1MIN";
    public static final String R3_5MIN = "R3_5MIN";

    // Constants for IO
    public static final String R1_IO_USAGE = "R1_IO_USAGE";
    public static final String R1_IO_FREE = "R1_IO_FREE";
    public static final String R2_IO_USAGE = "R2_IO_USAGE";
    public static final String R2_IO_FREE = "R2_IO_FREE";
    public static final String R3_IO_USAGE = "R3_IO_USAGE";
    public static final String R3_IO_FREE = "R3_IO_FREE";

    private static final long serialVersionUID = 1L;

    private JButton setButton;
    private JTextField intervalField;
    
    // Router 1 ArrayLists
    private ArrayList<Integer> r1ProcessorUsage = new ArrayList<>();
    private ArrayList<Integer> r1ProcessorFree = new ArrayList<>();
    private ArrayList<Integer> r1_5sec = new ArrayList<>();
    private ArrayList<Integer> r1_1min = new ArrayList<>();
    private ArrayList<Integer> r1_5min = new ArrayList<>();
    private ArrayList<Integer> r1IoUsage = new ArrayList<>();
    private ArrayList<Integer> r1IoFree = new ArrayList<>();

    // Router 2 ArrayLists
    private ArrayList<Integer> r2ProcessorUsage = new ArrayList<>();
    private ArrayList<Integer> r2ProcessorFree = new ArrayList<>();
    private ArrayList<Integer> r2_5sec = new ArrayList<>();
    private ArrayList<Integer> r2_1min = new ArrayList<>();
    private ArrayList<Integer> r2_5min = new ArrayList<>();
    private ArrayList<Integer> r2IoUsage = new ArrayList<>();
    private ArrayList<Integer> r2IoFree = new ArrayList<>();

    // Router 3 ArrayLists
    private ArrayList<Integer> r3ProcessorUsage = new ArrayList<>();
    private ArrayList<Integer> r3ProcessorFree = new ArrayList<>();
    private ArrayList<Integer> r3_5sec = new ArrayList<>();
    private ArrayList<Integer> r3_1min = new ArrayList<>();
    private ArrayList<Integer> r3_5min = new ArrayList<>();
    private ArrayList<Integer> r3IoUsage = new ArrayList<>();
    private ArrayList<Integer> r3IoFree = new ArrayList<>();
    
    private static int MAX_POINTS = 15;
    
    public ProcessFrame() {
        setTitle("Process Monitor");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel();
        intervalField = new JTextField(10);
        setButton = new JButton("Set");
        topPanel.add(new JLabel("Interval (seconds):"));
        topPanel.add(intervalField);
        topPanel.add(setButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(9, 1));

        // Router 1 graphs
        centerPanel.add(new GraphPanel("R1: Processor Usage vs Free", r1ProcessorUsage, r1ProcessorFree));
        centerPanel.add(new GraphPanel("R1: IO Usage vs Free", r1IoUsage, r1IoFree));
        centerPanel.add(new GraphPanelThree("R1: 5sec, 1min, 5min", r1_5sec, r1_1min, r1_5min));
        
        // Router 2 graphs
        
        
        centerPanel.add(new GraphPanel("R2: Processor Usage vs Free", r2ProcessorUsage, r2ProcessorFree));
        centerPanel.add(new GraphPanel("R2: IO Usage vs Free", r2IoUsage, r2IoFree));
        centerPanel.add(new GraphPanelThree("R2: 5sec, 1min, 5min", r2_5sec, r2_1min, r2_5min));
        
        
        // Router 3 graphs
        centerPanel.add(new GraphPanel("R3: Processor Usage vs Free", r3ProcessorUsage, r3ProcessorFree));
        centerPanel.add(new GraphPanel("R3: IO Usage vs Free", r3IoUsage, r3IoFree));
        centerPanel.add(new GraphPanelThree("R3: 5sec, 1min, 5min", r3_5sec, r3_1min, r3_5min));
        
        add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        repaint();

        setVisible(true);
    }
    
    private void addDataToList(ArrayList<Integer> list, int data) {
        if (list.size() >= MAX_POINTS) {
            list.remove(0);
        }
        list.add(data);
    }

    public void addData(int data, String type) {
    	System.out.println(type + " = " + data);
        switch (type) {
            // Router 1
            case R1_PROCESSOR_USAGE:
                addDataToList(r1ProcessorUsage, data);
                break;
            case R1_PROCESSOR_FREE:
                addDataToList(r1ProcessorFree, data);
                break;
            case R1_5SEC:
                addDataToList(r1_5sec, data);
                break;
            case R1_1MIN:
                addDataToList(r1_1min, data);
                break;
            case R1_5MIN:
                addDataToList(r1_5min, data);
                break;
            case R1_IO_USAGE:
                addDataToList(r1IoUsage, data);
                break;
            case R1_IO_FREE:
                addDataToList(r1IoFree, data);
                break;
            // Router 2
            case R2_PROCESSOR_USAGE:
                addDataToList(r2ProcessorUsage, data);
                break;
            case R2_PROCESSOR_FREE:
                addDataToList(r2ProcessorFree, data);
                break;
            case R2_5SEC:
                addDataToList(r2_5sec, data);
                break;
            case R2_1MIN:
                addDataToList(r2_1min, data);
                break;
            case R2_5MIN:
                addDataToList(r2_5min, data);
                break;
            case R2_IO_USAGE:
                addDataToList(r2IoUsage, data);
                break;
            case R2_IO_FREE:
                addDataToList(r2IoFree, data);
                break;
            // Router 3
            case R3_PROCESSOR_USAGE:
                addDataToList(r3ProcessorUsage, data);
                break;
            case R3_PROCESSOR_FREE:
                addDataToList(r3ProcessorFree, data);
                break;
            case R3_5SEC:
                addDataToList(r3_5sec, data);
                break;
            case R3_1MIN:
                addDataToList(r3_1min, data);
                break;
            case R3_5MIN:
                addDataToList(r3_5min, data);
                break;
            case R3_IO_USAGE:
                addDataToList(r3IoUsage, data);
                break;
            case R3_IO_FREE:
                addDataToList(r3IoFree, data);
                break;
        }
        repaint();
    }
    
    public JButton getIntervalButton() {
        return setButton;
    }
    
    public String getInputFromField() {
        return intervalField.getText();
    }
}