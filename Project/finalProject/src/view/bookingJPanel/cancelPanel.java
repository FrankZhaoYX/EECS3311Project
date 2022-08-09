package view.bookingJPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import parkingDB.maintainCustomer;
import view.table.orderTableModel;

public class cancelPanel extends JPanel {
	private orderTableModel orderTable = null;
	private JTable table = null;
	private maintainCustomer customerManage;
	public cancelPanel (maintainCustomer tmp){  
		this.customerManage=tmp;
    	setPreferredSize(new Dimension(800, 500));
    	setSize (800, 450);
    	setLocation(0, 50);
    	
    	try {
    		orderTable = new orderTableModel(tmp);
            table = new JTable(orderTable);
            setComponent();
            addComponent();
            addListener();
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	}
    }
	
	private void setComponent () { 
        setLayout(new FlowLayout());
    }
	
	 private void addComponent () {
	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane, BorderLayout.CENTER);
	}
	 
	 private void addListener () {
		 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	            	int row = table.getSelectedRow();
	        		int bid = Integer.valueOf((String)orderTable.getValueAt(row, 0));
//	        		String email = (String)orderTable.getValueAt(row, 7);
	        		String bookingInfo = "DO you want to cancel Booking " + bid ;
	        		int Yes_or_no = JOptionPane.showConfirmDialog (null, "cancel confirm", bookingInfo, JOptionPane.YES_NO_OPTION);
	        		if (Yes_or_no == JOptionPane.YES_OPTION) {
	        			try {
	        				if(cancelPanel.this.customerManage.cancelOrder(bid)) {
	        					JOptionPane.showMessageDialog(null, "cancel Successfule");
	        				} else {
	        					JOptionPane.showMessageDialog(null, "cancel failed");
	        				}
	        				
	        			} catch (Exception e1) {
	        				JOptionPane.showMessageDialog(null,e1.getMessage());
	        			}
	        			
	        		}
	        		return;
	            }
	        });
	    }
	
}
	 
//	 int row = table.getSelectedRow();
//		int bid = Integer.valueOf((String)orderTable.getValueAt(row, 0));
//		String email = (String)orderTable.getValueAt(row, 7);
//		String bookingInfo = "Booking " + bid ;
//		int Yes_or_no = JOptionPane.showConfirmDialog (null, bookingInfo, "DO you want to cancel", JOptionPane.YES_NO_OPTION);
//		if (Yes_or_no == JOptionPane.YES_OPTION) {
//			try {
//				cancelPanel.this.customerManage.cancelOrder(bid);
//				JOptionPane.showMessageDialog(null, "cancel Successfule");
//			} catch (Exception e1) {
//				JOptionPane.showMessageDialog(null,e1.getMessage());
//			}
//			
//		}
//		return;
