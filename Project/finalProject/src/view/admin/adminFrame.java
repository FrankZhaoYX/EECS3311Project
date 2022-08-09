package view.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import parkingDB.maintainOfficer;
import view.bookingJPanel.officerPanel;

public class adminFrame extends JFrame{
	String loginname ;
	String username ;
	JPanel buttonPanel = new JPanel();
	private maintainOfficer officerActivity;
	private JButton RefreshBtn = new JButton("Refresh OfficerList");
	private JButton RegisterBtn = new JButton("register Offficer");
	private JButton RemoveBtn	= new JButton("remove Officer");
    private JButton logoutBtn = new JButton("logout");
    
    private JPanel p = null;
    
    private List<JPanel> list = new ArrayList<JPanel>();
    
    public adminFrame (maintainOfficer tmp) {
		super("Parking system");
		this.officerActivity = tmp;
		init ();
		setComponent ();
		addComponent ();
		addListenter ();
		setVisible(true);
	}
    
	private void setComponent () {
		this.setLayout(null);
		buttonPanel.setSize (800, 50);
		buttonPanel.setLocation(0, 0);
	}
	
	private void changePanel (JPanel panel) {
		for (JPanel p : list) {
			adminFrame.this.getContentPane().remove(p);
		}
		list.clear();
		adminFrame.this.add(panel);
		list.add(panel);
		
		adminFrame.this.setVisible(true);
	}
	
	private void addComponent () {
		p = new officerPanel (adminFrame.this.officerActivity);
		
		list.add(p);
		buttonPanel.add(RefreshBtn);
		buttonPanel.add(RegisterBtn);
		buttonPanel.add(RemoveBtn);
		buttonPanel.add (logoutBtn);
		add (buttonPanel);
		add (p);
		
	}
	
	private void addListenter () {
		
		RefreshBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				officerPanel panel = new officerPanel(adminFrame.this.officerActivity);
				changePanel(panel);
			}
			
		});
		
		RegisterBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adminRegister officerRegister = new adminRegister(adminFrame.this.officerActivity);
				 adminFrame.this.dispose();	
	             officerRegister.setVisible(true);
			}
			
		});
		
		RemoveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tmp_info = "Please enter officer Email to remove: ";
				String email = JOptionPane.showInputDialog(tmp_info);
				if(email!=null || email.length()!=0) {
					try {
						if(adminFrame.this.officerActivity.remove_officer(email)) {
							JOptionPane.showMessageDialog(null,"Remove officer Successfully");
							return;
						}else {
							JOptionPane.showMessageDialog(null,"Remove officer failed");
							return;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		
		
		
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(adminFrame.this.officerActivity.logout()) {
					adminFrame.this.dispose();
					adminMain officerMenu = new adminMain("officer Mode");
					officerMenu.setVisible(true);
				}
			}
			
		});
	}

	private void init () {
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
}
