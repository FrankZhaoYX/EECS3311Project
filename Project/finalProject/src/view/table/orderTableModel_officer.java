package view.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import parkingDB.bookingInfo;

public class orderTableModel_officer extends AbstractTableModel {
	String[] columnName = new String[]{"BookingId", "Email", "Plot_Id", "StartTime", "ExpireTime", "Duration","status"};
	  
    String[][] bookingTable;
    
    ArrayList<bookingInfo> plots = null;

    public orderTableModel_officer (ArrayList<bookingInfo> tmp) throws Exception {
    	
    	SimpleDateFormat dt = new SimpleDateFormat("dd/MM HH:mm");
    	plots = tmp;
//    	System.out.println("size is " + plots.size());
    	try {
			plots = tmp;
	    	bookingTable = new String[plots.size()][];
	        for (int i=0; i<plots.size(); i++) {
	            bookingInfo b = plots.get(i);
	            if(b.getStatus()==0) {
	            	bookingTable[i] = new String[]{
		                    String.valueOf(b.getID()),
		                    b.getEmail(),
		                    String.valueOf(b.getPid()),
		                    dt.format(b.getStart()),
		                    dt.format(b.getEnd()),
		                    String.valueOf(b.getDuation()),
		                    "expired",
		            };
	            } else if(b.getStatus()==1) {
	            	bookingTable[i] = new String[]{
		                    String.valueOf(b.getID()),
		                    b.getEmail(),
		                    String.valueOf(b.getPid()),
		                    dt.format(b.getStart()),
		                    dt.format(b.getEnd()),
		                    String.valueOf(b.getDuation()),
		                    "holding",
		            };
	            } else if(b.getStatus()==2) {
	            	bookingTable[i] = new String[]{
		                    String.valueOf(b.getID()),
		                    b.getEmail(),
		                    String.valueOf(b.getPid()),
		                    dt.format(b.getStart()),
		                    dt.format(b.getEnd()),
		                    String.valueOf(b.getDuation()),
		                    "paid",
		            };
	            }
//	            bookingTable[i] = new String[]{
//	                    String.valueOf(b.getID()),
//	                    b.getEmail(),
//	                    String.valueOf(b.getPid()),
//	                    dt.format(b.getStart()),
//	                    dt.format(b.getEnd()),
//	                    String.valueOf(b.getDuation()),
//	                    String.valueOf(b.getStatus()),
//	            };
	        }
    	} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

    
    @Override
    public int getRowCount() {
        return bookingTable.length;
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return bookingTable[rowIndex][columnIndex];
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
