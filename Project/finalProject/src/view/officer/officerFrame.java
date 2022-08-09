package view.officer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import parkingDB.bookingInfo;
import parkingDB.maintainOfficer;
import view.bookingJPanel.cancelPanel_officer;
import view.bookingJPanel.plotPanel_officer;

public class officerFrame extends JFrame {
	String loginname ;
	String username ;
	JPanel buttonPanel = new JPanel();
	private maintainOfficer officerActivity;
	private JButton refreshPlotBtn = new JButton("refreshPlot");
	private JButton AddPlotBtn = new JButton("AddingPlot");
	private JButton Add_OnePlotBtn = new JButton("AutoAdd");
	private JButton registerBtn = new JButton("Register_NewUser");
	private JButton orderBtn = new JButton("newOrder");
    private JButton checkOrderBtn = new JButton("viewOrder");
    private JButton payBtn = new JButton("MakeApayment");
    private JButton logoutBtn = new JButton("logout");
    
    private JPanel p = null;
    
    private List<JPanel> list = new ArrayList<JPanel>();
    
    public officerFrame (maintainOfficer tmp) {
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
		buttonPanel.setSize (1000, 50);
		buttonPanel.setLocation(0, 0);
	}
	
	private void changePanel (JPanel panel) {
		for (JPanel p : list) {
			officerFrame.this.getContentPane().remove(p);
		}
		list.clear();
		officerFrame.this.add(panel);
		list.add(panel);
		
		officerFrame.this.setVisible(true);
	}
	
	private void addComponent () {
		p = new plotPanel_officer (officerFrame.this.officerActivity);
		
		list.add(p);
		buttonPanel.add (refreshPlotBtn);
		buttonPanel.add(Add_OnePlotBtn);
		buttonPanel.add(AddPlotBtn);
		buttonPanel.add(refreshPlotBtn);
		buttonPanel.add(registerBtn);
		buttonPanel.add(orderBtn);
		buttonPanel.add (checkOrderBtn);
		buttonPanel.add (payBtn);
		buttonPanel.add (logoutBtn);
		add (buttonPanel);
		add (p);
		
	}
	
	private void addListenter () {
		
		refreshPlotBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			//	System.out.println("state is " + userFrame.this.customerActivity.get_currentUser().getStatus());
				plotPanel_officer panel = new plotPanel_officer(officerFrame.this.officerActivity);
				changePanel(panel);
			}

			
		});
		
		checkOrderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tmp_info = "Please enter a emial to check ";
				String email = JOptionPane.showInputDialog(tmp_info);
				try {
					if(email!=null || email.length()!=0) {
						ArrayList<bookingInfo> tmp = officerFrame.this.officerActivity.retrieveAvaliableOrder(email);
						if(tmp==null || tmp.size()==0) {
							JOptionPane.showMessageDialog(null, "No orders now");
							return;
					}
						else {
//							System.out.println("start retrieve");
							cancelPanel_officer panel = new cancelPanel_officer(officerFrame.this.officerActivity,email);
							changePanel(panel);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Email cannot be null");
					}
					
				}  catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String tmp_info = "Please enter a emial to check ";
					String email = JOptionPane.showInputDialog(tmp_info);
					int totalfee = officerFrame.this.officerActivity.get_totalFee(email);
					if(email==null || email.length()==0) {
						return;
					}
					if(totalfee==0) {
						JOptionPane.showMessageDialog(null, "No orders to pay");
						return;
					}else {
						String payment = "Total fee is " + totalfee + ", Please enter credit CardNum: ";
						String cardNum = JOptionPane.showInputDialog(payment);
						if(officerFrame.this.officerActivity.make_payment(email, cardNum)) {
							JOptionPane.showMessageDialog(null,"payment Successful");
						} else {
							JOptionPane.showMessageDialog(null,"payment Failed");
							return;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		AddPlotBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tmp_info = "Please enter a ParkingPlot Id to add ";
				String id = JOptionPane.showInputDialog(tmp_info);
				if(id.length()==0 || id==null) {
					return;
				}
				try {
					if(officerFrame.this.officerActivity.add_plot(Integer.valueOf(id))) {
						JOptionPane.showMessageDialog(null,"Adding Plot Successful");
						return;
					} else {
						JOptionPane.showMessageDialog(null,"Adding Plot failed");
						return;
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		Add_OnePlotBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(officerFrame.this.officerActivity.add_plot()) {
						JOptionPane.showMessageDialog(null,"Auto Adding Plot Successful");
						return;
					} else {
						JOptionPane.showMessageDialog(null,"Auto Adding Plot failed");
						return;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		registerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 officerRegister officerRegister = new officerRegister(officerFrame.this.officerActivity);
				 officerFrame.this.dispose();	
	             officerRegister.setVisible(true);
			}
			
		});
		
		orderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tmp_info = "Please enter the Customer Email: ";
				String email = JOptionPane.showInputDialog(tmp_info);
				if(email==null || email.length()==0) {
					JOptionPane.showMessageDialog(null,"Booking failed");
					return;
				}
				try {
					if(officerFrame.this.officerActivity.email_exist(email)) {
						tmp_info = "Please enter the plotID to book: ";
						String plot_id = JOptionPane.showInputDialog(tmp_info);
						if(plot_id==null || plot_id.length()==0) {
							JOptionPane.showMessageDialog(null,"Booking failed");
							return;
						}
						
					    tmp_info = "Please enter the duration to book: ";
					    String duration = JOptionPane.showInputDialog(tmp_info);
					    if(duration==null || duration.length()==0) {
					    	JOptionPane.showMessageDialog(null,"Booking failed");
							return;
					    }
					    
					    if(officerFrame.this.officerActivity.make_booking(Integer.valueOf(plot_id), email,Integer.valueOf(duration))) {
					    	JOptionPane.showMessageDialog(null,"Booking successful");
							return;
					    } else {
					    	JOptionPane.showMessageDialog(null,"Booking failed");
							return;
					    }
					    
					} else{
						JOptionPane.showMessageDialog(null,"Booking failed");
						return;
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
		});
		
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(officerFrame.this.officerActivity.logout()) {
					officerFrame.this.dispose();
					officerMain userMenu = new officerMain("officer Mode");
					userMenu.setVisible(true);
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
