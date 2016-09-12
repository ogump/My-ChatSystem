package com.gh.view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class yonghuxinxi extends JFrame {

	private JPanel contentPane;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
    private  JButton   small;
    private  JButton   close;
   
	    
	  //  private EventClass event =null;
	    
	  //全局的位置变量，用于表示鼠标在窗口上的位置
		  static Point origin=new Point();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yonghuxinxi frame = new yonghuxinxi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public yonghuxinxi() {
		setTitle("好友信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 323);
		setUndecorated(true);//去掉边框
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u59D3  \u540D");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(51, 49, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u767B\u5F55\u540D");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(51, 88, 54, 15);
		contentPane.add(label);
		
		label_1 = new JLabel("\u6027  \u522B");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(51, 127, 54, 15);
		contentPane.add(label_1);
		
		label_2 = new JLabel("\u7231  \u597D");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_2.setBounds(51, 166, 54, 15);
		contentPane.add(label_2);
		
		label_3 = new JLabel("\u90AE  \u7BB1");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_3.setBounds(51, 205, 54, 15);
		contentPane.add(label_3);
		
		label_4 = new JLabel("个性签名");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_4.setBounds(51, 244, 65, 15);
		contentPane.add(label_4);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(183, 49, 83, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_5 = new JLabel("New label");
		label_5.setBounds(183, 89, 83, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("New label");
		label_6.setBounds(183, 128, 83, 15);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("New label");
		label_7.setBounds(183, 167, 83, 15);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("New label");
		label_8.setBounds(183, 206, 83, 15);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("New label");
		label_9.setBounds(118, 234, 217, 44);
		contentPane.add(label_9);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\student\\Desktop\\3.png"));
		lblNewLabel_2.setBounds(0, 0, 450, 323);
		contentPane.add(lblNewLabel_2);
		
		
		
		//窗口拖动调用方法
		  initWindowMoveEvent();
		
		close = new JButton("x");
		close.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		  close.setBackground(new Color(204, 51, 51));
		// close.setIcon(null);
		  close.setForeground(new Color(0, 0, 0));
		//  close.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		  close.setBounds(409, 10, 36, 21);
		 // close.setBorderPainted(false);实现按钮无边框
		//  close.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
		  close.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
		  getContentPane().add(close);
		  
		  ActionListener event = null;
		close.addActionListener(event);//设置监听
	   
		
		  small = new JButton("-");
		  small.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		  small.setBackground(new Color(204, 51, 0));
		//  small.setIcon(null);
		  small.setForeground(new Color(0, 0, 0));
		//  small.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		  small.setBounds(363, 10, 36, 21);
		//  small.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
		  small.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
		  getContentPane().add(small);
		  
		  
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
		  
	}
	
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

}
