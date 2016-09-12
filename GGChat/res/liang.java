package com.gh.view;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;

import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class liang extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
    private  JButton   small;
    private  JButton   close;
    private   JButton  register;
    private   JButton  cancel;
    
    
  //  private EventClass event =null;
    
  //全局的位置变量，用于表示鼠标在窗口上的位置
	  static Point origin=new Point();
    
	public liang() {
		setResizable(false);
		setBounds(new Rectangle(100, 46, 495, 550));
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		getContentPane().setLayout(null);
		setUndecorated(true);//去掉边框
		
		JLabel label = new JLabel("\u7528 \u6237 \u540D\uFF1A");
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(28, 52, 79, 15);
		getContentPane().add(label);
		label.setOpaque(false);
		
		
		JLabel label_1 = new JLabel("\u8D26    \u53F7\uFF1A");
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(266, 52, 79, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6    \u7801\uFF1A");
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_2.setBounds(28, 106, 79, 15);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u5BC6\u7801\u786E\u8BA4\uFF1A");
		label_3.setForeground(new Color(0, 0, 0));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_3.setBounds(266, 106, 79, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u7231    \u597D\uFF1A");
		label_4.setForeground(new Color(0, 0, 0));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_4.setBounds(28, 193, 79, 15);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u90AE    \u7BB1\uFF1A");
		label_5.setForeground(new Color(0, 0, 0));
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_5.setBounds(28, 263, 79, 15);
		getContentPane().add(label_5);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		textField.setForeground(new Color(0, 0, 0));
		textField.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		textField.setBounds(117, 49, 121, 21);
		textField.setOpaque(false);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		textField_1.setColumns(10);
		textField_1.setOpaque(false);
		textField_1.setBounds(356, 49, 121, 21);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		textField_2.setForeground(new Color(0, 0, 0));
		textField_2.setColumns(10);
		textField_2.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		textField_2.setOpaque(false);
		textField_2.setBounds(117, 103, 121, 21);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		textField_3.setForeground(new Color(0, 0, 0));
		textField_3.setColumns(10);
		textField_3.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		textField_3.setOpaque(false);
		textField_3.setBounds(356, 103, 121, 21);
		getContentPane().add(textField_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u542C\u97F3\u4E50");
		rdbtnNewRadioButton.setForeground(new Color(0, 0, 0));
		rdbtnNewRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBounds(117, 189, 80, 23);
		rdbtnNewRadioButton.setOpaque(false);
		getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("\u770B\u4E66");
		radioButton.setForeground(new Color(0, 0, 0));
		radioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton.setBounds(199, 189, 65, 23);
		radioButton.setOpaque(false);
		getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u7761\u89C9");
		radioButton_1.setForeground(new Color(0, 0, 0));
		radioButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton_1.setBounds(281, 189, 65, 23);
		radioButton_1.setOpaque(false);
		getContentPane().add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("\u73A9\u6E38\u620F");
		radioButton_2.setForeground(new Color(0, 0, 0));
		radioButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton_2.setBounds(348, 189, 79, 23);
		radioButton_2.setOpaque(false);
		getContentPane().add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("\u8FD0\u52A8");
		radioButton_3.setForeground(new Color(0, 0, 0));
		radioButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton_3.setBounds(429, 189, 65, 23);
		radioButton_3.setOpaque(false);
		getContentPane().add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("\u65C5\u6E38");
		radioButton_4.setForeground(new Color(0, 0, 0));
		radioButton_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton_4.setBounds(117, 220, 65, 23);
		radioButton_4.setOpaque(false);
		getContentPane().add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("\u770B\u7535\u5F71");
		radioButton_5.setForeground(new Color(0, 0, 0));
		radioButton_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton_5.setBounds(199, 220, 80, 23);
		radioButton_5.setOpaque(false);
		getContentPane().add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("\u804A\u5929");
		radioButton_6.setForeground(new Color(0, 0, 0));
		radioButton_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton_6.setBounds(281, 220, 65, 23);
		radioButton_6.setOpaque(false);
		getContentPane().add(radioButton_6);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		textField_4.setForeground(new Color(0, 0, 0));
		textField_4.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		textField_4.setColumns(10);
		textField_4.setOpaque(false);
		textField_4.setBounds(117, 260, 165, 21);
		getContentPane().add(textField_4);
		
		JButton register = new JButton("\u6CE8\u518C");
		register.setBackground(new Color(255, 255, 255));
		register.setForeground(new Color(0, 0, 0));
		register.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		register.setBounds(145, 490, 93, 23);
		getContentPane().add(register);
		
		JLabel label_6 = new JLabel("\u4E2A\u4EBA\u7B80\u4ECB\uFF1A");
		label_6.setForeground(new Color(0, 0, 0));
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_6.setBounds(28, 315, 79, 15);
		getContentPane().add(label_6);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setOpaque(false);
		textArea.setBounds(117, 311, 330, 114);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		getContentPane().add(textArea);
		
		JLabel label_9 = new JLabel("\u6027    \u522B\uFF1A");
		label_9.setForeground(new Color(0, 0, 0));
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_9.setBounds(28, 152, 79, 15);
		getContentPane().add(label_9);
		
		JRadioButton radioButton_7 = new JRadioButton("\u7537");
		radioButton_7.setForeground(new Color(0, 0, 0));
		radioButton_7.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton_7.setBounds(117, 148, 53, 23);
		radioButton_7.setOpaque(false);
		getContentPane().add(radioButton_7);
		
		JRadioButton radioButton_8 = new JRadioButton("\u5973");
		radioButton_8.setForeground(new Color(0, 0, 0));
		radioButton_8.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		radioButton_8.setBounds(183, 148, 121, 23);
		radioButton_8.setOpaque(false);
		getContentPane().add(radioButton_8);
		
		JLabel label_7 = new JLabel("   \u7528\u6237\u6CE8\u518C");
		label_7.setForeground(new Color(0, 0, 0));
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_7.setBounds(208, 10, 123, 32);
		getContentPane().add(label_7);
		
		//窗口拖动调用方法
		  initWindowMoveEvent();
		
		close = new JButton("x");
		  close.setBackground(new Color(255, 255, 255));
		// close.setIcon(null);
		  close.setForeground(new Color(0, 0, 0));
		//  close.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		  close.setBounds(455, 10, 36, 21);
		 // close.setBorderPainted(false);实现按钮无边框
		//  close.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
		  close.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
		  getContentPane().add(close);
		  
		  ActionListener event = null;
		close.addActionListener(event);//设置监听
	   
		
		small = new JButton("-");
		  small.setBackground(new Color(255, 255, 255));
		//  small.setIcon(null);
		  small.setForeground(new Color(0, 0, 0));
		//  small.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		  small.setBounds(409, 10, 36, 21);
		//  small.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
		  small.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
		  getContentPane().add(small);
		  
		  JButton cancel = new JButton("\u53D6\u6D88");
		  cancel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		  cancel.setBackground(new Color(255, 255, 255));
		  cancel.setForeground(new Color(0, 0, 0));
		  cancel.setBounds(297, 490, 88, 23);
		  getContentPane().add(cancel);
		  
		  JLabel label_8 = new JLabel("");
		  label_8.setIcon(new ImageIcon(liang.class.getResource("/com/gh/res/\u6CE8\u518C.jpg")));
		  label_8.setBounds(0, 0, 497, 600);
		  getContentPane().add(label_8);
		  
		
		
		  
		  small.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                setExtendedState(JFrame.ICONIFIED);
	            }
	        });
		  
		  close.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	 setVisible(false);//隐藏窗体
					  System.exit(0); //退出程序
	            }
	        });
		  
		  register.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	JOptionPane.showMessageDialog(liang.this,"welcome register");
	            }
	        });
	}
		  
		//  small.addActionListener(even);//设置监听
		  
		  /**
		   * 窗口拖动事件
		   */
		  public void initWindowMoveEvent(){
			  addMouseListener(new MouseAdapter(){
				  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起
				  public void  mousePressed(MouseEvent e){
				  //当鼠标按下的时候获得窗口当前的位置
				  origin.x=e.getX();
				  origin.y=e.getY();
			  
			  }
			  });
			  
			  addMouseMotionListener(new MouseMotionAdapter(){
				  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动
				  
				  public void mouseDragged(MouseEvent e){
					  //当鼠标拖动时获取窗口当前位置
					  Point FirstFrame=getLocation();
					  //设置窗口的位置
					  //窗口当前的位置+鼠标当前在窗口的位置-鼠标按下的时候在窗口的位置
					  setLocation(FirstFrame.x+e.getX()-origin.x,FirstFrame.y+e.getY()-origin.y);
				  }
				  });
		  
		  
	  }
	
		  /**
		   * 事件处理类
	       **/
		 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 new liang();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					liang frame = new liang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
