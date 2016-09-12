package com.gh.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.mina.core.session.IoSession;

import com.gh.model.Pepole;
import com.gh.utils.Tools;

import javax.swing.border.LineBorder;

public class RegisterFrame extends JFrame {
	private JTextField tf_name;
	private JTextField tf_id;
	private JTextField tf_email;
	private JPasswordField pf_pwd;
	private JPasswordField pf_qpwd;
	private IoSession clientSession;
	// 全局的位置变量，用于表示鼠标在窗口上的位置
	static Point origin = new Point();
	
	public void setClientSession(IoSession clientSession) {
		this.clientSession = clientSession;
	}
	/**
	 * 窗口拖动事件
	 */
	public void initWindowMoveEvent() {
		addMouseListener(new MouseAdapter() {
			// 按下（mousePressed 不是点击，而是鼠标被按下没有抬起
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();

			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动

			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point FirstFrame = getLocation();
				// 设置窗口的位置
				// 窗口当前的位置+鼠标当前在窗口的位置-鼠标按下的时候在窗口的位置
				setLocation(FirstFrame.x + e.getX() - origin.x, FirstFrame.y
						+ e.getY() - origin.y);
			}
		});
	}
	public RegisterFrame(LoginFrame loginFrame) {
		setUndecorated(true);
		setResizable(false);
		initWindowMoveEvent();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterFrame.class.getResource("/com/gh/res/1.png")));
		setBounds(new Rectangle(100, 50, 495, 522));
		setFont(new Font("楷体", Font.PLAIN, 12));
		setTitle("\u7528\u6237\u6CE8\u518C");
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u59D3    \u540D\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(28, 147, 80, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u767B\u9646\u540D\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(28, 33, 80, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6    \u7801\uFF1A");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_2.setBounds(28, 70, 80, 15);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u5BC6\u7801\u786E\u8BA4\uFF1A");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_3.setBounds(28, 109, 80, 15);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u7231    \u597D\uFF1A");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_4.setBounds(28, 217, 79, 15);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u90AE    \u7BB1\uFF1A");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_5.setBounds(28, 286, 79, 15);
		getContentPane().add(label_5);
		
		tf_name = new JTextField();
		tf_name.setBorder(new LineBorder(Color.BLACK, 1, true));
		tf_name.setOpaque(false);
		tf_name.setBounds(118, 145, 124, 21);
		getContentPane().add(tf_name);
		tf_name.setColumns(10);
		
		tf_id = new JTextField();
		tf_id.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tf_id.setOpaque(false);
		tf_id.setColumns(10);
		tf_id.setBounds(118, 31, 124, 21);
		getContentPane().add(tf_id);
		
		tf_email = new JTextField();
		tf_email.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tf_email.setOpaque(false);
		tf_email.setColumns(10);
		tf_email.setBounds(117, 284, 199, 21);
		getContentPane().add(tf_email);
		
		
		JLabel label_6 = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_6.setBounds(28, 328, 79, 15);
		getContentPane().add(label_6);
		
		JTextArea ta_info = new JTextArea("该用户很懒，什么也没输入");
		ta_info.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		ta_info.setOpaque(false);
		ta_info.setBounds(117, 326, 337, 131);
		getContentPane().add(ta_info);
		
		pf_pwd = new JPasswordField();
		pf_pwd.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pf_pwd.setOpaque(false);
		pf_pwd.setBounds(118, 68, 124, 21);
		getContentPane().add(pf_pwd);
		
		pf_qpwd = new JPasswordField();
		pf_qpwd.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pf_qpwd.setOpaque(false);
		pf_qpwd.setBounds(118, 107, 124, 21);
		getContentPane().add(pf_qpwd);
		
		JLabel label_9 = new JLabel("\u6027    \u522B\uFF1A");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_9.setBounds(28, 180, 80, 15);
		getContentPane().add(label_9);
		
		JRadioButton rb_man = new JRadioButton("\u7537");
		rb_man.setSelected(true);
		rb_man.setBackground(new Color(176, 224, 230));
		rb_man.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		rb_man.setBounds(117, 176, 41, 23);
		rb_man.setOpaque(false);
		getContentPane().add(rb_man);
		
		JRadioButton rb_woman = new JRadioButton("\u5973");
		rb_woman.setBackground(new Color(204, 255, 255));
		rb_woman.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		rb_woman.setBounds(193, 176, 41, 23);
		rb_woman.setOpaque(false);
		getContentPane().add(rb_woman);
		 //性别组
		ButtonGroup bg=new ButtonGroup();
		bg.add(rb_man);
		bg.add(rb_woman);
		
		JCheckBox cb_music = new JCheckBox("\u542C\u97F3\u4E50");
		cb_music.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cb_music.setBackground(new Color(176, 224, 230));
		cb_music.setBounds(114, 213, 73, 23);
		cb_music.setOpaque(false);
		getContentPane().add(cb_music);
		
		JCheckBox cb_readbook = new JCheckBox("\u770B\u4E66");
		cb_readbook.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cb_readbook.setBackground(new Color(204, 255, 255));
		cb_readbook.setBounds(200, 213, 73, 23);
		cb_readbook.setOpaque(false);
		getContentPane().add(cb_readbook);
		
		JCheckBox cb_playgame = new JCheckBox("\u73A9\u6E38\u620F");
		cb_playgame.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cb_playgame.setBackground(new Color(204, 255, 255));
		cb_playgame.setBounds(282, 213, 73, 23);
		cb_playgame.setOpaque(false);
		getContentPane().add(cb_playgame);
		
		JCheckBox cb_sport = new JCheckBox("\u8FD0\u52A8");
		cb_sport.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cb_sport.setBackground(new Color(204, 255, 255));
		cb_sport.setBounds(282, 241, 73, 23);
		cb_sport.setOpaque(false);
		getContentPane().add(cb_sport);
		
		JCheckBox cb_trip = new JCheckBox("\u65C5\u6E38");
		cb_trip.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cb_trip.setBackground(new Color(176, 224, 230));
		cb_trip.setBounds(114, 241, 73, 23);
		cb_trip.setOpaque(false);
		getContentPane().add(cb_trip);
		
		JCheckBox cb_flim = new JCheckBox("\u770B\u7535\u5F71");
		cb_flim.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cb_flim.setBackground(new Color(204, 255, 255));
		cb_flim.setBounds(200, 238, 77, 23);
		cb_flim.setOpaque(false);
		getContentPane().add(cb_flim);
		/**
		 * 确认注册
		 */
		JButton btn_ok = new JButton("\u786E\u5B9A");
		btn_ok.setForeground(Color.BLACK);
		btn_ok.setBackground(Color.WHITE);
		btn_ok.setOpaque(false);
		btn_ok.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = tf_id.getText().trim();
				String password = new String(pf_pwd.getPassword()).trim();
				String qpassword = new String(pf_qpwd.getPassword()).trim();
				String name = tf_name.getText().trim();
				String sex = rb_man.isSelected() ? "男" : "女";
				String likes="" ;
				if (cb_music.isSelected())
					likes+="听歌、";
				if (cb_flim.isSelected())
					likes+="电影、";
				if (cb_playgame.isSelected())
					likes+="游戏、";
				if (cb_readbook.isSelected())
					likes+="读书、";
				if (cb_sport.isSelected())
					likes+="运动、";
				if (cb_trip.isSelected())
					likes+="旅游、";
				if(!"".equals(likes))likes=likes.substring(0, likes.length()-1);//不为空去掉最后一个"、"
				String email = tf_email.getText().trim();
				String info = ta_info.getText().trim();
				/**
				 *  首先对输入数据做判断
				 */
				// 首先不能为空
				if ("".equals(id) || "".equals(name)  || "".equals(password)
						|| "".equals(qpassword) || "".equals(sex) || "".equals(email) || "".equals(info)
						|| "".equals(likes)
						) {
					Tools.show(RegisterFrame.this, "有信息没有输入！");
				}else if(id.contains("Sb")||id.contains("sb")||id.contains("傻")||id.contains("笨")||
						id.contains("猪")||id.contains("狗")||id.contains("SB")||id.contains("sB")||
						id.contains("爸")||id.contains("use")||id.contains("所")||id.contains("IP")||id.contains("Port")){
					Tools.show(RegisterFrame.this, "不能用敏感词汇注册！");
				}
				// 然后密码大于6位且和确认密码相同
				else if (password.length() < 3) {
					Tools.show(RegisterFrame.this, "密码必须大于3位");
				} else if (password.length() < 3) {
					Tools.show(RegisterFrame.this, "密码必须大于3位");
				}else if (!password.equals(qpassword)) {
					Tools.show(RegisterFrame.this, "密码和确认密码不同！");
				} else if (!email.matches("\\w+@\\w+(\\.\\w+)+")) {
					Tools.show(RegisterFrame.this, "邮箱格式不对!");
				}else {
				Pepole p=new Pepole(name, id, password, sex, likes, email, info);
				clientSession.write(p);
				}
			}
		});
		btn_ok.setBounds(134, 483, 101, 23);
		getContentPane().add(btn_ok);
		/**
		 * 返回登陆
		 */
		JButton btn_cancel = new JButton("\u8FD4\u56DE\u767B\u9646");
		btn_cancel.setForeground(Color.BLACK);
		btn_cancel.setOpaque(false);
		btn_cancel.setBackground(Color.WHITE);
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFrame.setVisible(true);
				dispose();
			}
		});
		btn_cancel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btn_cancel.setBounds(293, 483, 101, 23);
		getContentPane().add(btn_cancel);
		
		JLabel label_7 = new JLabel("   \u7528\u6237\u6CE8\u518C");
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_7.setBounds(193, 0, 123, 32);
		getContentPane().add(label_7);
		
		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setForeground(Color.WHITE);
		button.setBorder(new LineBorder(Color.WHITE, 1, true));
		button.setBackground(new Color(178, 34, 34));
		button.setBounds(456, 0, 39, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("-");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setBorder(new LineBorder(Color.WHITE, 1, true));
		button_1.setBackground(new Color(178, 34, 34));
		button_1.setBounds(417, 0, 39, 23);
		getContentPane().add(button_1);
		
		JLabel lbbackGround = new JLabel("");
		lbbackGround.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lbbackGround.setBackground(new Color(204, 255, 255));
		lbbackGround.setIcon(new ImageIcon(RegisterFrame.class.getResource("/com/gh/res/\u6CE8\u518C.jpg")));
		lbbackGround.setBounds(0, 0, 495, 522);
		getContentPane().add(lbbackGround);
	}
}
