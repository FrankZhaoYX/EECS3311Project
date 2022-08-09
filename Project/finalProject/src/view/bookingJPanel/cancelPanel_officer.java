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

import parkingDB.maintainOfficer;
import view.table.orderTableModel_officer;

public class cancelPanel_officer extends JPanel{
	private orderTableModel_officer orderTable = null;
	private JTable table = null;
	private maintainOfficer officerManage;
	private String email;
	public cancelPanel_officer (maintainOfficer tmp, String email) throws Exception{  
		this.officerManage=tmp;
		this.email = email;
    	setPreferredSize(new Dimension(800, 500));
    	setSize(800, 450);
    	setLocation(0, 50);
//    	System.out.println("enter panel");
    	orderTable = new orderTableModel_officer(tmp.retrieveAvaliableOrder(email));
        table = new JTable(orderTable);
        setComponent();
        addComponent();
        addListener();
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
	        				if(cancelPanel_officer.this.officerManage.cancel_order(email, bid)) {
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
