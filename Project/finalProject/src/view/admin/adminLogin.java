package view.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import parkingDB.maintainOfficer;

public class adminLogin extends JFrame {
	JLabel iconLabel = new JLabel(new ImageIcon("src/view/img.jpg"));
	JPanel panel = new JPanel();
	JLabel loginText = new JLabel("UserName:");
	JLabel passText = new JLabel("PassWord:");
	JTextField loginField = new JTextField(20);
	JPasswordField passField = new JPasswordField(20);
	JButton loginBtn = new JButton("Login");
	JButton cancelBtn = new JButton("Cancel");
	Dimension preferredSize = new Dimension(140,40);
	Font font = new Font(null,Font.BOLD,20);
	
	private void addComponent() {
		setLayout(new BorderLayout());
		add(iconLabel, BorderLayout.NORTH);
		setSize(1000, 100);

		// set the label size 
		loginField.setPreferredSize(new Dimension(60,40));
		passField.setPreferredSize(new Dimension(60,40));
		loginBtn.setPreferredSize(preferredSize);
		cancelBtn.setPreferredSize(preferredSize);
		loginBtn.setFont(font);
		cancelBtn.setFont(font);

		panel.add(loginText);
		panel.add(loginField);
		panel.add(passText);
		panel.add(passField);
		panel.add(loginBtn);
		panel.add(cancelBtn);
		panel.setSize(800,100);
		add(panel, BorderLayout.CENTER);
	}
	
	private void addListener() {
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String loginname = loginField.getText();
				String password = passField.getText();
//				System.out.println("loginname is " + loginname);
//				System.out.println("password is " + password);
				
				if (loginname.length() == 0) {
					JOptionPane.showMessageDialog(null,"username cannot be null");
					loginField.grabFocus();
					loginField.setText("");
					passField.setText("");
					return;
				}
				if (password.length() == 0) {
					JOptionPane.showMessageDialog(null,"password cannot be null");
					loginField.grabFocus();
					loginField.setText("");
					passField.setText("");
					return;
				}
				
				try {
					maintainOfficer officer = new maintainOfficer();
					boolean login = officer.login(loginname, password);
					int autho = officer.get_currentOfficer().getAuthority();
					if(!login || autho!=3) {
						JOptionPane.showMessageDialog(null,"userName or password Not correct");
						loginField.grabFocus();
						loginField.setText("");
						passField.setText("");
						return;
					} else {
						adminLogin.this.dispose();
						adminFrame userMenu = new adminFrame(officer);
						userMenu.setVisible(true);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adminLogin.this.dispose();
				adminMain adminMenu = new adminMain("admin Mode");
				adminMenu.setVisible(true);
			}
			
		});
	}
	
	public adminLogin() {
		addComponent();
		addListener();
	}
}
