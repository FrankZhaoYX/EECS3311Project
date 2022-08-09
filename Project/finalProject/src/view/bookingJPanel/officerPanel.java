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
import view.table.officerTableModel;

public class officerPanel extends JPanel {
	officerTableModel officers = null;
	JTable table = null;
    private maintainOfficer officerManage;
    
    public officerPanel(maintainOfficer officer) {
    	this.officerManage = officer;
    	setPreferredSize(new Dimension(800, 500));
    	setSize (800, 450);
    	setLocation(0, 50);
    	
    	try {
    		officers = new officerTableModel(this.officerManage);
    		table = new JTable(officers);
    		 setComponent ();
             addComponent();
             addListener();
    	}catch (Exception e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	}
    }
    
    private void setComponent () {
     
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
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
            	String name = (String) officers.getValueAt(row, 0);
            	String email = (String) officers.getValueAt(row, 1);
            		int Yes_or_No = JOptionPane.showConfirmDialog(null, "Whether delete Officer ", name, JOptionPane.YES_NO_OPTION);
            		if(Yes_or_No == JOptionPane.YES_OPTION) {
            			try {
							boolean p = officerPanel.this.officerManage.remove_officer(email);
							if(p) {
								JOptionPane.showMessageDialog(null,"Delete Officer Success");
								return;
							} else {
								JOptionPane.showMessageDialog(null,"Delete Officer Failed");
								return;
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            		} else if (Yes_or_No == JOptionPane.NO_OPTION) {
            			return;
            		}
            	}
            
        });
    }
}
