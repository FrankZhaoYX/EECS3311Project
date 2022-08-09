package view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import parkingDB.bookingInfo;
import parkingDB.customer;
import parkingDB.maintainCustomer;
import view.bookingJPanel.cancelPanel;
import view.bookingJPanel.plotPanel;

public class userFrame extends JFrame{
	String loginname ;
	String username ;
	JPanel buttonPanel = new JPanel();
	private maintainCustomer customerActivity;
	private JButton orderPlotBtn = new JButton("refreshPlot");
    private JButton selectTicketBtn = new JButton("viewOrder");
    private JButton payBtn = new JButton("MakeApayment");
    private JButton logoutBtn = new JButton("logout");
    
    private JPanel p = null;
    
    private List<JPanel> list = new ArrayList<JPanel>();
    
    public userFrame (maintainCustomer tmp) {
		super("Parking system");
	//	System.out.println("state is " + tmp.get_currentUser().getStatus());
		this.customerActivity = tmp;
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
			userFrame.this.getContentPane().remove(p);
		}
		list.clear();
		userFrame.this.add(panel);
		list.add(panel);
		
		userFrame.this.setVisible(true);
	}
	
	private void addComponent () {
		p = new plotPanel (userFrame.this.customerActivity);
		
		list.add(p);
		buttonPanel.add (orderPlotBtn);
		buttonPanel.add (selectTicketBtn);
		buttonPanel.add (payBtn);
		buttonPanel.add (logoutBtn);
		add (buttonPanel);
		add (p);
		
	}
	
	private void addListenter () {
		
		orderPlotBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			//	System.out.println("state is " + userFrame.this.customerActivity.get_currentUser().getStatus());
				plotPanel panel = new plotPanel(userFrame.this.customerActivity);
				changePanel(panel);
			}

			
		});
		
		selectTicketBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArrayList<bookingInfo> tmp = userFrame.this.customerActivity.retriveAvaliableOrder();
					if(tmp==null || tmp.size()==0) {
						JOptionPane.showMessageDialog(null, "No orders now");
						return;
					} else {
						cancelPanel panel = new cancelPanel(userFrame.this.customerActivity);
						changePanel(panel);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int totalfee = userFrame.this.customerActivity.get_Totalfee();
					System.out.println("fee is " + totalfee);
					System.out.println("user is " + userFrame.this.customerActivity.get_currentUser().getEmail());
					if(totalfee==0) {
						JOptionPane.showMessageDialog(null, "No orders to pay");
						return;
					}else {
						String payment = "Total fee is " + totalfee + ", Please enter credit CardNum: ";
						String cardNum = JOptionPane.showInputDialog(payment);
						if(userFrame.this.customerActivity.make_payment(cardNum)) {
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
		
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(userFrame.this.customerActivity.logout()) {
					userFrame.this.dispose();
					userMain userMenu = new userMain("User Mode");
					userMenu.setVisible(true);
				}
			}
			
		});
	}

	private void init () {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		customer tmp = new customer();
		maintainCustomer testDB = new maintainCustomer(tmp);
		testDB.login("test01@yorku.ca", "123");
	//	System.out.println("state is " + testDB.get_currentUser().getStatus());
		userFrame userFrame = new userFrame (testDB);
	}

}
