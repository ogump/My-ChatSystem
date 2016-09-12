package com.gh.Sever;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.gh.util.DateUtil;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class ServerFrame {

	private JFrame Severframe;
	private JTextField textField;
	private JTextArea textArea = null;
	private DefaultListModel<String> model=null;
	private JList<String> list=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame window = new ServerFrame();
					window.Severframe.setVisible(true);
					// 设置UI风格为系统默认的风格
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	//初始化用户列表
	public void InitUserList(){
		model=new DefaultListModel<String>();
		model.addElement("所有人");
		list.setModel(model);
	}
	//添加用户到在线列表
	public void AddUserToList(String username){
		model.addElement(username);
		list.setModel(model);
	}
	public void updateText(String text) {
		StringBuffer sb = new StringBuffer();
		sb.append(textArea.getText()).append("\n").append(DateUtil.getTime()+"--")
			.append(text);
		textArea.setText(sb.toString());
	}
	public void DelUser(String username){
		model.removeElement(username);
	}
	/**
	 * Create the application.
	 */
	public ServerFrame() {
		initialize();
		// 启动服务
		startSever();
		//初始化用户列表
		InitUserList();
	}

	private void startSever() {
		textArea.setText("服务器已经启动，正在监听8000端口...");
		new Thread(new Runnable() {
			@Override
			public void run() {
				new ServerService(ServerFrame.this).startSever();
			}
		}).start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Severframe = new JFrame();
		Severframe
				.setIconImage(Toolkit.getDefaultToolkit().getImage(ServerFrame.class.getResource("/com/gh/res/1.png")));
		Severframe.setTitle("\u804A\u804A-\u670D\u52A1\u7AEF");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int x = (int) (d.getWidth() - 534) / 2;
		int y = (int) (d.getHeight() - 383) / 2;
		Severframe.setBounds(x, y, 534, 383);
		Severframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Severframe.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("\u670D\u52A1\u5668\u7AEF");
		Severframe.getContentPane().add(label, BorderLayout.NORTH);

		JPanel jpanel = new JPanel();
		Severframe.getContentPane().add(jpanel, BorderLayout.EAST);
		jpanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("--\u5728\u7EBF\u7528\u6237\u5217\u8868--");
		jpanel.add(lblNewLabel, BorderLayout.NORTH);

		list = new JList<String>();
		jpanel.add(list, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		Severframe.getContentPane().add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JPanel panel = new JPanel();
		Severframe.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\uFF1A");
		panel.add(lblNewLabel_1, BorderLayout.WEST);

		textField = new JTextField();
		panel.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);

		JButton button = new JButton("\u53D1\u9001");
		panel.add(button, BorderLayout.EAST);
	}

}
