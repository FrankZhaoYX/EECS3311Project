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
import view.table.plotTableModel;

public class plotPanel_officer extends JPanel {
	plotTableModel plot = null;
	JTable table = null;
    private maintainOfficer officerManage;
    
    public plotPanel_officer(maintainOfficer officer) {
    	this.officerManage= officer;
    	setPreferredSize(new Dimension(800, 500));
    	setSize (800, 450);
    	setLocation(0, 50);
    	
    	try {
    		plot = new plotTableModel();
    		table = new JTable(plot);
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
            	String plot_id = (String) plot.getValueAt(row, 0);
            		int Yes_or_No = JOptionPane.showConfirmDialog(null, "Confirm Delete ParkingPlot Num ", plot_id, JOptionPane.YES_NO_OPTION);
            		if(Yes_or_No == JOptionPane.YES_OPTION) {
            			try {
							boolean tmp = plotPanel_officer.this.officerManage.remove_plot(Integer.parseInt(plot_id));
							if (tmp) {
								JOptionPane.showMessageDialog(null,"Remove Success");
							} else {
								JOptionPane.showMessageDialog(null,"Remove Failed");
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
