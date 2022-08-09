package view.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import parkingDB.maintainOfficer;
import parkingDB.officer;

public class officerTableModel extends AbstractTableModel {
	
	String[] columnName = new String[]{"name","email","status"};
	   
    String[][] officerInfo;
    maintainOfficer officerManage = null;
    ArrayList<officer> officers = null;

    public officerTableModel (maintainOfficer tmp) throws Exception {
        officerManage= tmp;
        officers = officerManage.retrieve_officer();
        officerInfo= new String[officers.size()][];
        for (int i=0; i<officers.size(); i++) {
            officer o = officers.get(i);
            if(o.getStatus()==1) {
            	officerInfo[i] = new String[]{
                        o.getName(),
                        o.getEmail(),
                        "Online"};
            } else {
            	officerInfo[i] = new String[]{
            			o.getName(),
                        o.getEmail(),
                        "Offline"};
            }
            
        }
    }
	

	@Override
    public int getRowCount() {
        return officerInfo.length;
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return officerInfo[rowIndex][columnIndex];
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
