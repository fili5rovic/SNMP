package variant2;

import javax.swing.*;

import util.GraphPanel;

import java.awt.*;
import java.util.ArrayList;

public class PacketFrame extends JFrame {
    private ArrayList<Integer> totalInTraffic, totalOutTraffic, totalUniInTraffic, totalUniOutTraffic, totalNonUniInTraffic, totalNonUniOutTraffic;
    private static final int MAX_POINTS = 15;
//    private String yAxisLabel = "Vrednost";

    public PacketFrame() {
        totalInTraffic = new ArrayList<>();
        totalOutTraffic = new ArrayList<>();
        totalUniInTraffic = new ArrayList<>();
        totalUniOutTraffic = new ArrayList<>();
        totalNonUniInTraffic = new ArrayList<>();
        totalNonUniOutTraffic = new ArrayList<>();

        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.33;

        add(new GraphPanel("Ukupan saobracaj",totalInTraffic, totalOutTraffic), gbc);
        gbc.gridy = 1;
        add(new GraphPanel("Ukupan unicast saobracaj",totalUniInTraffic, totalUniOutTraffic), gbc);
        gbc.gridy = 2;
        add(new GraphPanel("Ukupan non-unicast saobracaj", totalNonUniInTraffic, totalNonUniOutTraffic), gbc);

        setVisible(true);
    }
    
    public void addTotalTraffic(int in, int out) {
        addData(totalInTraffic, in, totalOutTraffic, out);
    }
    
    public void addTotalNonUnicastTraffic(int in, int out) {
        addData(totalNonUniInTraffic, in, totalNonUniOutTraffic, out);
    }
    
    public void addTotalUnicastTraffic(int in, int out) {
        addData(totalUniInTraffic, in, totalUniOutTraffic, out);
    }
    
    public void addData(ArrayList<Integer> dataA, int valueA, ArrayList<Integer> dataB, int valueB) {
        if (dataA.size() >= MAX_POINTS) {
            dataA.remove(0);  
        }
        if (dataB.size() >= MAX_POINTS) {
            dataB.remove(0); 
        }
        dataA.add(valueA);
        dataB.add(valueB);
        repaint();
    }
    
    

    
    
    
}
    
