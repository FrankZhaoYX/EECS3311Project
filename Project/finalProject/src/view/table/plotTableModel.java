package view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import parkingDB.maintainPlot;
import parkingDB.parkingPlot;

public class plotTableModel extends AbstractTableModel {
	
    String[] columnName = new String[]{"id","status"};
   
    String[][] plotInfo;
    maintainPlot plotManage = null;
    ArrayList<parkingPlot> plots = null;

    public plotTableModel () throws Exception {
        plotManage = new maintainPlot();
        plots = plotManage.get_parkingPlot();
        plotInfo= new String[plots.size()][];
        for (int i=0; i<plots.size(); i++) {
            parkingPlot plot = plots.get(i);
            if(plot.get_status()==1) {
            	plotInfo[i] = new String[]{
                        String.valueOf(plot.get_id()),
                        "occupied"};
            }else {
            	plotInfo[i] = new String[]{
                        String.valueOf(plot.get_id()),
                        "free"};
            }
            
        }
    }

   
    @Override
    public int getRowCount() {
        return plotInfo.length;
    }

   
    @Override
    public int getColumnCount() {
        return columnName.length;
    }

  
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return plotInfo[rowIndex][columnIndex];
    }

   
    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

   
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
