package com.gh.Client;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.basic.DefaultMenuLayout;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

@SuppressWarnings("unused")
public class LoginFrame {

	private JFrame frame;
	private JTextField id;
	private JPasswordField pwd;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame =  new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/com/gh/res/1.png")));
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		int x=(int)(d.getWidth()-379)/2;
		int y=(int)(d.getHeight()-171)/2;
		frame.setResizable(false);
		frame.setTitle("\u767B\u5F55");
		frame.setBounds(x, y, 379, 171);
		frame.getContentPane().setLayout(new GridLayout(3, 1, 20, 5));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 10);
		fl_panel.setAlignOnBaseline(true);
		panel.setLayout(fl_panel);
		
		JLabel label = new JLabel("\u7528\u6237\u5E10\u53F7\uFF1A");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		panel.add(label);
		
		id = new JTextField();
		panel.add(id);
		id.setColumns(15);
		
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(10);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		panel_1.add(label_1);
		
		pwd = new JPasswordField();
		pwd.setColumns(15);
		panel_1.add(pwd);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(6);
		frame.getContentPane().add(panel_2);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username=id.getText().trim();
				String password=new String(pwd.getPassword());
				if("".equals(username)||username==null||"".equals(password)||password==null){
					JOptionPane.showMessageDialog(frame, "ÓÃ»§ÃûºÍÃÜÂë²»ÄÜÎª¿Õ");
					return;
				}
				new UserService().login(username, password,frame);
			}
		});
		button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		panel_2.add(button);
		
		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int v=JOptionPane.showConfirmDialog(frame, "ÕæµÄÒªÍË³öÂð", "ÍË³ö", JOptionPane.YES_NO_OPTION);
				if(v==JOptionPane.YES_OPTION)System.exit(0);
			}
		});
		button_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		panel_2.add(button_1);
	}

	
}
