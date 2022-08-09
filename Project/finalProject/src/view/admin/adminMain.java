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
import javax.swing.JPanel;

import view.mainView;

public class adminMain extends JFrame {
	JLabel iconLabel = new JLabel(new ImageIcon("src/view/yorku-logo-ko.png"));
	JPanel butonPanel = new JPanel();
    JButton officerLoginBtn = new JButton("Login");
  //  JButton offRegisterBtn = new JButton("Register");
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
    
        officerLoginBtn.setPreferredSize(preferredSize);
     //   userRegisterBtn.setPreferredSize(preferredSize);
        backBtn.setPreferredSize(preferredSize);
     
        officerLoginBtn.setFont(font);
    //    userRegisterBtn.setFont(font);
        backBtn.setFont(font);
       
        butonPanel.add(officerLoginBtn);
    //    butonPanel.add(userRegisterBtn);
        butonPanel.add(backBtn);
        add(butonPanel, BorderLayout.SOUTH);
    }
    
    private void addListener() {
    
        officerLoginBtn.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                
                adminMain.this.dispose();	
                
                adminLogin admin = new adminLogin();
//                mainView.this.add(userLogin);
                admin.setVisible(true);
            }
        });
        
        backBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                adminMain.this.dispose();	
                mainView mainMenu = new mainView("Parking_System");
//              mainView.this.add(userLogin);
                mainMenu.setVisible(true);
            }
        });

    }
    
    public adminMain(String title) {
        super(title);
        initFrame();
        addComponent();
        addListener();
        setVisible(true);
    }
}
