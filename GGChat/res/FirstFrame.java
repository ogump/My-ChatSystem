package com.gh.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import  java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.naming.event.EventContext;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;



/**
 * 
 * 通过继承JFrame实现一个窗体
 * ***/


public class FirstFrame extends JFrame{
	  private JPanel panel;
	  
	  private JLabel lab_title;
	  private JLabel lab_name;
	  private JLabel lab_psw;
	  private JLabel Image;
	 
	  private JTextField  user;
	  private JPasswordField  pas;
	  
	  private JButton  login;
	  private JButton close;
	  private JButton register;
	  private JButton small;
	  
	  private EventClass even =null;
	  
	  //全局的位置变量，用于表示鼠标在窗口上的位置
	  static Point origin=new Point();
	  /*
	   * 构造方法
	   */
	  public FirstFrame(){
		  setTitle("user login");//设置窗体
		  setSize(521,296);
		  setLocationRelativeTo(null);//居中
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  even =new EventClass();
		  
		  //实例化组件
		  panel=new JPanel();
		  panel.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));
		 // panel.setOpaque(false);
		//  panel.setBorder(BorderFactory.createLineBorder(Color.magenta, 5, true));
		  panel.setLayout(null);//null布局，组件需要使用x,y,width,height定义位置
		  lab_title=new JLabel("  Welcome login GGtalk");
		  lab_title.setBounds(110,30,300,50);
		  
		  //将标题加入面板
		  panel.add(lab_title);
		  setUndecorated(true);
		  //窗口拖动调用方法
		  initWindowMoveEvent();
		  
		  //设置字体
		  lab_title.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		  lab_title.setForeground(new Color(255, 255, 255));
		 // this.getContentPane().setBackground(Color.magenta);
		  
		  
		  lab_name=new JLabel("\u767B\u5F55\u540D\uFF1A ");
		  lab_name.setBackground(Color.WHITE);
		  lab_name.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		  lab_name.setForeground(new Color(182, 69, 25));
		  lab_name.setHorizontalAlignment(SwingConstants.LEFT);
		  lab_name.setBounds(110,136,100,30);
		  panel.add(lab_name);
		  
		  lab_psw=new JLabel("\u5BC6   \u7801 \uFF1A ");
		  lab_psw.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		  lab_psw.setForeground(new Color(182, 69, 25));
		  lab_psw.setBounds(110,176,120,30);
		  panel.add(lab_psw);
		  
		  user = new JTextField();
		  user.setOpaque(false);
		  user.setBackground(SystemColor.textHighlightText);
		  user.setForeground(new Color(255, 102, 0));
		  user.setBounds(190, 137, 200, 30);
		  panel.add(user);
		  
		  pas=new JPasswordField();
		  pas.setOpaque(false);
		  pas.setForeground(new Color(182, 69, 25));
		  pas.setBounds(190,177,200,30);
		  panel.add(pas);
		  
		  login=new JButton("登录");
		  login.setOpaque(false);
		  login.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 // login.setBorder(BorderFactory.createLineBorder(Color.white, 2, true));
		  login.setForeground(new Color(182, 69, 25));
		  login.setBackground(Color.WHITE);
		  login.setBounds(190, 228, 80, 30);
	      panel.add( login);
		  
		  //添加监听程序
	      login.addActionListener(even);
		  
		  register=new JButton("注册");
		  register.setOpaque(false);
		  register.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 // register.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		  register.setForeground(new Color(182, 69, 25));
		  register.setBackground(Color.WHITE);
		  register.setBounds(303, 228, 87, 30);
		   panel.add(register);
		   //添加监听程序
		   register.addActionListener(even);
	
		   close = new JButton("x");
			  close.setBackground(new Color(182, 69, 25));
			// close.setIcon(null);
			  close.setForeground(Color.WHITE);
			//  close.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			  close.setBounds(475, 10, 36, 21);
			 // close.setBorderPainted(false);实现按钮无边框
			//  close.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
			  close.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
			  panel.add(close);
			  
			  close.addActionListener(even);//设置监听
		   
			  small = new JButton("-");
			  small.setBackground(new Color(182, 69, 25));
			//  small.setIcon(null);
			  small.setForeground(Color.WHITE);
			//  small.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			  small.setBounds(435, 10, 36, 21);
			//  small.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
			  small.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
			  panel.add(small);
			  
		   
		 
		  //设置窗体的默认面板
		  setContentPane(panel);
		  
		  Image = new JLabel("");
		  Image.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		  Image.setIcon(new ImageIcon(FirstFrame.class.getResource("/com/gh/res/\u767B\u5F55.jpg")));
		  Image.setBackground(new Color(0, 102, 153));
		  Image.setBounds(1, 1, 520, 295);
		  panel.add(Image);
		  
		 
		 // setUndecorated(true);//去掉边框
		 // close.setOpaque(false);
		  

		  //设置窗口最小化
		  small.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                setExtendedState(JFrame.ICONIFIED);
	            }
	        });
		  
		  
		//  small.addActionListener(even);//设置监听
	  }
	  
	  
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
	  public class EventClass implements ActionListener{
		  @Override
		  public void actionPerformed(ActionEvent e){
			  //得到事件源
			  JButton btu=(JButton)e.getSource();
			  if(btu== login){
				  //取出文本框和密码框的值
				  String name=user.getText();
				  String pwd=new String(pas.getPassword());
				  
				  //判断登录是否成功 
				  if("chen".equals(name)){
					  dispose();
					  
					//  JOptionPane.showMessageDialog(FirstFrame.this,"Congratulations to login successfully ");
					//  JOptionPane.showMessageDialog(FirstFrame.this, " ", "welcome to GGtalk", JOptionPane.INFORMATION_MESSAGE);
				  }else{
					  pas.setText("");
					  JOptionPane.showMessageDialog(FirstFrame.this,"Password error, login failed！");
				  }
				 
			  }else if(btu==register){
				  JOptionPane.showMessageDialog(FirstFrame.this,"welcome register");
				  
			  }else if(btu==close){
				  setVisible(false);//隐藏窗体
				  System.exit(0); //退出程序
			  } 
			  
			 
		  }
	  }
	  
	  /**
	   * 程序入口
	   * @param args
	   * */
	  public static void main(String[] args){
		  EventQueue.invokeLater(new Runnable(){
			  public void run(){
				  try{
					  FirstFrame window=new FirstFrame();
					  //显示窗体
					  window.setVisible(true);
				  }catch( Exception e){
					  e.printStackTrace();
				  }
			  }
		  });
		  new FirstFrame();
	  }
}
