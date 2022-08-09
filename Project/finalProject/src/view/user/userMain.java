package view.user;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.mainView;

public class userMain extends JFrame {
	JLabel iconLabel = new JLabel(new ImageIcon("src/view/yorku-logo-ko.png"));
	JPanel butonPanel = new JPanel();
    JButton userLoginBtn = new JButton("Login");
    JButton userRegisterBtn = new JButton("Register");
    JButton backBtn = new JButton("back");
    Dimension preferredSize = new Dimension(210,60);
    Font font = new Font(null,Font.BOLD,18);

    /**
     * Initial Frame
     */
    private void initFrame() {
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    /**
     * Add component
     */
    private void addComponent() {
 
        add(iconLabel, BorderLayout.NORTH);
    
        userLoginBtn.setPreferredSize(preferredSize);
        userRegisterBtn.setPreferredSize(preferredSize);
        backBtn.setPreferredSize(preferredSize);
     
        userLoginBtn.setFont(font);
        userRegisterBtn.setFont(font);
        backBtn.setFont(font);
       
        butonPanel.add(userLoginBtn);
        butonPanel.add(userRegisterBtn);
        butonPanel.add(backBtn);
        add(butonPanel, BorderLayout.SOUTH);
    }
    
    private void addListener() {
    
        userLoginBtn.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                
                userMain.this.dispose();	
                
                userLogin userLogin = new userLogin();
//                mainView.this.add(userLogin);
                userLogin.setVisible(true);
            }
        });
        
        backBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                userMain.this.dispose();	
                mainView mainMenu = new mainView("Parking_System");
//              mainView.this.add(userLogin);
                mainMenu.setVisible(true);
            }
        });
        
        userRegisterBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userMain.this.remove(butonPanel);
				userMain.this.remove(iconLabel);
				userRegister event = new userRegister();
				userMain.this.add(event);
				userMain.this.setVisible(true);
			}
        	
        });

    }
    
    public userMain(String title) {
        super(title);
        initFrame();
        addComponent();
        addListener();
        setVisible(true);
    }

    public static void main(String[] args) {
        
    }
}
