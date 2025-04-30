package util;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class MyJTable extends JTable {

    private static final long serialVersionUID = 1L;

    public MyJTable(DefaultTableModel model) {
        super(model);
        customizeAppearance();
    }

    private void customizeAppearance() {
        JTableHeader header = getTableHeader();
        header.setFont(new Font("Calibri", Font.BOLD, 14));
        header.setBackground(new Color(135, 206, 250));
        header.setForeground(Color.DARK_GRAY);
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        setRowHeight(30);
        setFont(new Font("Arial", Font.PLAIN, 13));

        setShowGrid(true);
        setGridColor(new Color(220, 220, 220));

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public void resize() {
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                
                Object lastColValue = table.getValueAt(row, table.getColumnCount() - 1);
                if (lastColValue != null && lastColValue.toString().equals("1")) {
                    comp.setBackground(new Color(131, 206, 250, 100));
                } else {
                    comp.setBackground(Color.WHITE);
                }
                
                return comp;
            }
        };
        customRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int column = 0; column < this.getColumnCount(); column++) {
            int maxWidth = this.getColumnName(column).length() * 10;
            for (int row = 0; row < this.getRowCount(); row++) {
                TableCellRenderer renderer = this.getCellRenderer(row, column);
                Component comp = this.prepareRenderer(renderer, row, column);
                maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
            }
            this.getColumnModel().getColumn(column).setPreferredWidth(maxWidth + 10);
            this.getColumnModel().getColumn(column).setCellRenderer(customRenderer);
        }
        
        
    }
    
    
    @Override
    public boolean isCellEditable(int row, int column) {                
        return false;               
};
}
