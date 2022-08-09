package view.user;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import parkingDB.maintainCustomer;

public class userRegister extends JPanel{
	JPanel email = new JPanel();
    JPanel password = new JPanel();
    JPanel name  = new JPanel();
    JPanel btn = new JPanel();
    
  
    JLabel emailLabel = new JLabel("Please Enter email:");
    JLabel passwordLabel = new JLabel("Please Enter password:");
    JLabel nameLabel = new JLabel("Please Enter fullName:");

    
    JTextField emailField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JTextField nameField = new JTextField(20);
    
  
    JButton registerButton = new JButton("Register");
    JButton backButton = new JButton("back");
    private void setComponent () {
        setLayout(new GridLayout(4, 1));
    }

    private void addComponent () {
    	email.add(emailLabel);
    	email.add(emailField);
    	password.add(passwordLabel);
    	password.add(passwordField);
    	name.add(nameLabel);
    	name.add(nameField);
    	btn.add(registerButton);
    	btn.add(backButton);
    	add(email);
    	add(password);
    	add(name);
    	add(btn);
    	
    }

    private void addListener() {
    	registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				if (name.length()==0) {
					JOptionPane.showMessageDialog(null,"name cannot be null");
					nameField.grabFocus();
					return;
				}
				
				String password = passwordField.getText();
				if(password.length()==0) {
					JOptionPane.showMessageDialog(null,"password cannot be null");
					return;
				}
				
				String email = emailField.getText();
				if(email.length()==0) {
					JOptionPane.showMessageDialog(null,"email cannot be null");
					return;
				}
				
				try {
					maintainCustomer customerManage = new maintainCustomer();
					boolean test = customerManage.register(name, email, password);
					if(test) {
						JOptionPane.showMessageDialog(null, "Register Success");
						return ;
					} else {
						JOptionPane.showMessageDialog(null, "Register Failed");
						return ;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

		
    		
    	});
    	backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				userRegister.this.setVisible(false);
				new userMain ("User_Mode");
			}
    		
    	});
    }

    public userRegister() {
        setComponent();
        addComponent();
        addListener();
        setVisible(true);
    }
    
    public static void main(String[] args) {
    	
	}
}
