package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphPanelThree extends JPanel {
    private ArrayList<Integer> dataA, dataB, dataC;
    private String title;

    public GraphPanelThree(String title, ArrayList<Integer> dataA, ArrayList<Integer> dataB, ArrayList<Integer> dataC) {
        this.dataA = dataA;
        this.dataB = dataB;
        this.dataC = dataC;
        this.title = title;
        setPreferredSize(new Dimension(750, 250));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int value : dataA) {
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }
        for (int value : dataB) {
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }
        for (int value : dataC) {
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }

        if (minValue == Integer.MAX_VALUE || maxValue == Integer.MIN_VALUE) {
            return;
        }


        int panelHeight = getHeight();
        int panelWidth = getWidth();
        int margin = 50;

        // Draw axes
        g.setColor(Color.BLACK);
        g.drawLine(margin, panelHeight - margin, panelWidth - margin, panelHeight - margin); // X-axis
        g.drawLine(margin, panelHeight - margin, margin, margin); // Y-axis

        // Draw X-axis label
        g.drawString("Vreme (s)", panelWidth / 2 - 40, panelHeight - 10);

        // Draw the graph title
        g.setColor(Color.BLACK);
        g.drawString(title, 100, 30);

        // Calculate Y-axis scale steps
        int numSteps = 10; // Number of scale steps
        int range = maxValue - minValue;
        int stepSize = range / numSteps;
        if (stepSize == 0) {
            stepSize = 1; // Handle case where values are all the same
        }

        // Draw Y-axis scale labels
        for (int i = 0; i <= numSteps; i++) {
            int labelValue = minValue + i * stepSize;
            if (labelValue > maxValue) {
                labelValue = maxValue; // Ensure the max value is displayed
            }
            int yPos = panelHeight - margin - (int) (((double) (labelValue - minValue) / range) * (panelHeight - 2 * margin));
            g.drawString(String.valueOf(labelValue), margin - 40, yPos);
        }

        // Draw data lines and dots for dataA (blue)
        g.setColor(Color.BLUE);
        for (int i = 1; i < dataA.size(); i++) {
            int x1 = margin + (i - 1) * (panelWidth - 2 * margin) / (dataA.size() - 1);
            int y1 = panelHeight - margin - (int) (((double) (dataA.get(i - 1) - minValue) / range) * (panelHeight - 2 * margin));
            int x2 = margin + i * (panelWidth - 2 * margin) / (dataA.size() - 1);
            int y2 = panelHeight - margin - (int) (((double) (dataA.get(i) - minValue) / range) * (panelHeight - 2 * margin));

            g.drawLine(x1, y1, x2, y2);

            // Draw a dot on each data point
            g.fillOval(x1 - 3, y1 - 3, 6, 6);
        }

        // Draw data lines and dots for dataB (red)
        g.setColor(Color.RED);
        for (int i = 1; i < dataB.size(); i++) {
            int x1 = margin + (i - 1) * (panelWidth - 2 * margin) / (dataB.size() - 1);
            int y1 = panelHeight - margin - (int) (((double) (dataB.get(i - 1) - minValue) / range) * (panelHeight - 2 * margin));
            int x2 = margin + i * (panelWidth - 2 * margin) / (dataB.size() - 1);
            int y2 = panelHeight - margin - (int) (((double) (dataB.get(i) - minValue) / range) * (panelHeight - 2 * margin));

            g.drawLine(x1, y1, x2, y2);

            // Draw a dot on each data point
            g.fillOval(x1 - 3, y1 - 3, 6, 6);
        }

        // Draw data lines and dots for dataC (yellow)
        g.setColor(Color.YELLOW);  // Treća linija biće žuta
        for (int i = 1; i < dataC.size(); i++) {
            int x1 = margin + (i - 1) * (panelWidth - 2 * margin) / (dataC.size() - 1);
            int y1 = panelHeight - margin - (int) (((double) (dataC.get(i - 1) - minValue) / range) * (panelHeight - 2 * margin));
            int x2 = margin + i * (panelWidth - 2 * margin) / (dataC.size() - 1);
            int y2 = panelHeight - margin - (int) (((double) (dataC.get(i) - minValue) / range) * (panelHeight - 2 * margin));

            g.drawLine(x1, y1, x2, y2);

            // Draw a dot on each data point
            g.fillOval(x1 - 3, y1 - 3, 6, 6);
        }
    }
}
