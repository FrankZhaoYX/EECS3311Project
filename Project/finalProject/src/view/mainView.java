package view;

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

import view.admin.adminMain;
import view.officer.officerMain;
import view.user.userLogin;
import view.user.userMain;

public class mainView extends JFrame {
	JLabel iconLabel = new JLabel(new ImageIcon("src/view/yorku-logo-ko.png"));
	JPanel butonPanel = new JPanel();
    JButton userModeBtn = new JButton("User_Mode");
    JButton officerModeBtn = new JButton("Officeier_Mode");
    JButton adminModeBtn = new JButton("admin_Mode");
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
    
        userModeBtn.setPreferredSize(preferredSize);
        officerModeBtn.setPreferredSize(preferredSize);
        adminModeBtn.setPreferredSize(preferredSize);
     
        userModeBtn.setFont(font);
        officerModeBtn.setFont(font);
        adminModeBtn.setFont(font);
       
        butonPanel.add(userModeBtn);
        butonPanel.add(officerModeBtn);
        butonPanel.add(adminModeBtn);
        add(butonPanel, BorderLayout.SOUTH);
    }
    
    private void addListener() {
    
        userModeBtn.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                
                mainView.this.dispose();	
                
                userMain userMenu = new userMain("User Mode");
//                mainView.this.add(userLogin);
                userMenu.setVisible(true);
            }
        });
        
        officerModeBtn.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                
                mainView.this.dispose();	
                
                officerMain officerMenu = new officerMain("Officer Mode");
//              mainView.this.add(userLogin);
                officerMenu.setVisible(true);
            }
        });
        
        adminModeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainView.this.dispose();
				adminMain tmp = new adminMain("Admin Mode");
				tmp.setVisible(true);
			}
        	
        });

    }
    
    public mainView(String title) {
        super(title);
        initFrame();
        addComponent();
        addListener();
        setVisible(true);
    }

    public static void main(String[] args) {
        mainView mf = new mainView("Parking system");
    }
}
