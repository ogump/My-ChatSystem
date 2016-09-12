package com.gh.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.mina.core.session.IoSession;

import com.gh.model.AddInfo;
import com.gh.model.DelInfo;
import com.gh.model.Pepole;
import com.gh.model.SendInfo;
import com.gh.utils.Tools;

import java.awt.Toolkit;
import javax.swing.border.LineBorder;

/**
 * 
 * @author 梁鹏辉，盛冠钦
 *
 */
public class TalkFrame extends JFrame {
	private DefaultListModel<String> useModel;
	private DefaultListModel<String> onlineModel;
	private Hashtable<String, String> idAndContent = new Hashtable<String, String>();// 每个好友id对应一个聊天内容
	private Hashtable<String, Integer> idAndcount = new Hashtable<String, Integer>();// 每个好友id对应一个聊数
	private JList<String> list_b;
	private JList<String> list;
	private JLabel onlineNum;
	private int onlineCount = 0;
	private JTextArea ta_show;
	private String myId;// 这个界面的id
	private JTextArea ta_input;
	private String onlineId;// 点击的在线id
	private String friendId;// 点击的friendid
	private JLabel title;

	public String getMyId() {
		return myId;
	}

	// 得到选择的好友id
	public String getFriendId() {
		return friendId;
	}

	// 得到选择的在线id

	public String getOnlineId() {
		return onlineId;
	}

	/**
	 * Create the application.
	 */
	public TalkFrame(String myName, String myId, IoSession clientSession) {

		this.myId = myId;
		initialize(myName, myId, clientSession);// 初始化界面

		InitUserList(myId, clientSession);// 初始化用户列表
		idAndContent.put("所有在线用户", "");

		onlineId = "所有在线用户";
		new Thread(new Runnable() {// 开一个线程刷新聊天界面
					private String clikContent = idAndContent.get("所有在线用户");

					@Override
					public void run() {
						while (true) {
							try {
								Thread.sleep(100);
								clikContent = idAndContent.get(onlineId);
								ta_show.setText(clikContent);
								ta_show.setCaretPosition(ta_show.getText().length());// 滚动条自动滚动到最后
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();

	}

	// 更新文本内容
	/**
	 * 
	 * @param id 和谁说
	 * @param text 说的内容
	 */
	public void addText(String id, String text) {
		
		String time = Tools.getTime();//拿到时间
		String oldContent="";
		if(idAndContent.get(id)==null){
			oldContent="";
		}else{
			oldContent=idAndContent.get(id);//得到旧的内容
		}
		String newContent = oldContent+time + "\n" + text + "\n";//新内容
		idAndContent.put(id, newContent);//覆盖旧内容
		ta_show.setCaretPosition(ta_show.getText().length());// 滚动条自动滚动到最后
		//列表显示消息
		
		int talkNum=idAndcount.get(id)==null? 0:idAndcount.get(id);//id的数
		idAndcount.put(id, talkNum+1);//在原来基础上加一
		talkNum=idAndcount.get(id);//拿到最新id的数
		Object[] array =  onlineModel.toArray();//拿到列表
		for(int i=0;i<array.length;i++){
			String sid=(String) array[i];
			if(sid.startsWith(id)){
				//如果是以要加的内容的id开始，改变内容
				onlineModel.set(i, id+"-"+talkNum);
				break;
			}
		}
		
	}
	
	public void setTalk(boolean b) {
		if (b) {
			Tools.show(this, "恭喜！你被解除禁言，以后好好做人,oh，好好说话！");
		} else {
			Tools.show(this, "GG你被禁言,请好好说话！");
		}
		ta_input.setEditable(b);
	}

	// 初始化用户列表和在线状态列表
	public void InitUserList(String myId, IoSession clientSession) {
		useModel = new DefaultListModel<String>();
		onlineModel = new DefaultListModel<String>();
		onlineModel.addElement("所有在线用户");
		list.setModel(useModel);
		list_b.setModel(onlineModel);
		// 找服务器要好友列表
		clientSession.write("查找好友");
		clientSession.write("查找在线好友");

	}

	// 从服务器更新好友列表
	public void UpdataList(String[] friends) {
		// 拆了东墙补西墙。。。之前添加的要全删了，再添加一次
		System.out.println("开始更新好友列表");
		useModel.removeAllElements();
		for (String f : friends) {
			useModel.addElement(f);
		}
	}

	// 从服务器更新在线好友列表
	public void UpdataOnlineList(String[] onliefriends) {

		System.err.println("开始更新在线好友列表:");
		onlineModel.removeAllElements();
		onlineCount = 0;// 移除所有归0
		onlineModel.addElement("所有在线用户");
		for (String f : onliefriends) {
			onlineCount++;
			onlineModel.addElement(f);
		}
		onlineNum.setText(onlineCount + "");// 更新显示

	}
	//添加在线好友
	public void addOnlineList(String id){
		onlineCount++;
		onlineModel.addElement(id);
		onlineNum.setText(onlineCount + "");// 更新显示
		
	}
	// 移除在线用户
	public void delOnlineList(String id){
		onlineCount--;
		Object[] array =  onlineModel.toArray();
		for(Object s:array){
			String str=(String) s;
			if(str.startsWith(id)){
				id=str;
				break;
			}
		}
		onlineModel.removeElement(id);
		onlineNum.setText(onlineCount + "");// 更新显示
	}

	// 添加好友
	public void addfiend(String friendId) {
		useModel.addElement(friendId);
	}

	// 移除好友
	public void DelUser(String friendId) {
		useModel.removeElement(friendId);
		System.err.println("删除" + friendId);
	}
	//显示信息界面
	public void newInfoFrame(Pepole p){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new UseInfoFrame(p.getId(), p.getName(),p.getSex(),p.getLikes(), p.getEmail(), p.getInfo())
					.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//	// 移除在线用户
//	public void DelOnlineUser(String id) {
//		
//		list_b.clearSelection();
//		onlineCount--;// 当前在线数
//		onlineModel.removeElement(id);
//		// list_b.setModel(onlineModel);
//		onlineNum.setText(onlineCount + "");// 更新显示
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String myname, String myid, IoSession clientSession) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				LoginFrame.class.getResource("/com/gh/res/1.png")));
		setResizable(false);
		setTitle("GGtalk_当前ID:" + myid);
		setBounds(100, 100, 720, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(Color.WHITE, 1, true));
		scrollPane_1.setBounds(10, 50, 103, 296);
		getContentPane().add(scrollPane_1);
		// 好友列表
		list = new JList<String>();
		list.setBorder(new LineBorder(Color.WHITE, 1, true));
		list.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(0,0,0,0));//实现透明效果.
		list.setOpaque(false);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount()==2){
					System.err.println("你点了list两下");
					friendId=list.getSelectedValue();
					if(friendId==null){
						Tools.show(TalkFrame.this, "请选择id！");
					}else{
						clientSession.write("好友信息~"+friendId);
					}
				}
			}
		});
		scrollPane_1.setViewportView(list);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(Color.WHITE, 1, true));
		scrollPane_2.setBounds(123, 50, 101, 296);
		getContentPane().add(scrollPane_2);
		// 在线列表
		list_b = new JList<String>();
		list_b.setBorder(new LineBorder(Color.WHITE, 1, true));
		list_b.setForeground(Color.WHITE);
		list_b.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		list_b.setBackground(new Color(0,0,0,0));//实现透明效果.
		list_b.setOpaque(false);
		scrollPane_2.setOpaque(false);
		scrollPane_2.getViewport().setOpaque(false);
		list_b.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ta_show.setCaretPosition(ta_show.getText().length());// 滚动条自动滚动到最后
				 String[] split = list_b.getSelectedValue().split("-");
				onlineId = split[0];
				System.err.println("你单击了list_b并选择了" + onlineId);
				title.setText("【" + myname + "】欢迎使用GGtalk,你正在和【" + onlineId
						+ "】聊天");// 更新title
				ta_show.setText(idAndContent.get(onlineId));// 更新聊天记录
				idAndcount.put(onlineId, 0);
				Object[] array =  onlineModel.toArray();//拿到列表
				for(int i=0;i<array.length;i++){
					String sid=(String) array[i];
					if(sid.startsWith(onlineId)){
						//如果是以要加的内容的id开始，改变内容
						onlineModel.set(i, onlineId);
						break;
					}
				}
			}
		});
		scrollPane_2.setViewportView(list_b);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 33, 483, 362);
		
		getContentPane().add(scrollPane);

		ta_show = new JTextArea();
		
		ta_show.setEditable(false);
		ta_show.setFocusable(false);
		scrollPane.setViewportView(ta_show);
		// 添加好友
		JButton btn_add = new JButton("\u6DFB\u52A0\u597D\u53CB");
		btn_add.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btn_add.setBackground(Color.WHITE);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onlineId = list_b.getSelectedValue();
				if (onlineId == null) {// 不为空
					Tools.show(TalkFrame.this, "请选择id或输入id！");
					return;
				} else {
					if (onlineId.equals(myid)) {// 不能为自己
						Tools.show(TalkFrame.this, "不能添加自己");
						return;
					} else {
						clientSession.write(new AddInfo(myid, onlineId));
						System.out.println("发送添加好友请求");
					}
				}
			}
		});
		btn_add.setBounds(121, 377, 103, 31);
		getContentPane().add(btn_add);
		// 删除好友
		JButton btn_delete = new JButton("\u5220\u9664\u597D\u53CB");
		btn_delete.setBackground(Color.WHITE);
		btn_delete.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				friendId = list.getSelectedValue();
				if (friendId == null) {// 不为空
					Tools.show(TalkFrame.this, "请选择id或输入id！");
					return;
				} else {
					if (friendId.equals(myid)) {// 不能为自己
						Tools.show(TalkFrame.this, "不能删除自己");
						return;
					} else {
						clientSession.write(new DelInfo(myid, friendId));
						System.out.println("发送删除好友请求");
					}
				}

			}
		});

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(231, 398, 353, 72);
		getContentPane().add(scrollPane_3);
		// 聊天输入框
		ta_input = new JTextArea();
		ta_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				boolean eb = false;
				boolean ec = false;
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					eb = true;
				}
				if (e.isControlDown()) {
					ec = true;
				}
				if (eb && ec) {
					// 获取选择对象
					onlineId=list_b.getSelectedValue();
					System.out.println("你用按钮发送信息给：" + onlineId);
					if (onlineId == null) {
						onlineId = "所有在线用户";
					}
					if (onlineId.equals(myid)) {
						Tools.show(TalkFrame.this, "不能对自己发送");
						return;
					} // 判断内容
					String sendContent = ta_input.getText().trim();
					if (sendContent.equals("") || sendContent == null) {
						Tools.show(TalkFrame.this, "内容不能为空");
						return;
					} else {
						String[] split = onlineId.split("-");
						onlineId = split[0];
						SendInfo sendInfo = new SendInfo(myId, onlineId,
								sendContent);
						clientSession.write(sendInfo);
						
						addText(onlineId, "【你】对"+"【"+onlineId+"】说:\n"+sendContent);
						idAndcount.put(onlineId, 0);
						Object[] array =  onlineModel.toArray();//拿到列表
						for(int i=0;i<array.length;i++){
							String sid=(String) array[i];
							if(sid.startsWith(onlineId)){
								//如果是以要加的内容的id开始，改变内容
								onlineModel.set(i, onlineId);
								break;
							}
						}
						ta_input.setText("");
//						ta_show.setCaretPosition(ta_show.getText().length());// 滚动条自动滚动到最后
					}
				}
			}
		});
		scrollPane_3.setViewportView(ta_input);
		btn_delete.setBounds(10, 377, 103, 31);
		getContentPane().add(btn_delete);

		JLabel label = new JLabel("\u6211\u7684\u597D\u53CB");
		label.setForeground(Color.WHITE);
		label.setBackground(Color.WHITE);
		label.setFont(new Font("华文楷体", Font.BOLD, 13));
		label.setBounds(10, 33, 68, 15);
		getContentPane().add(label);

		JLabel onlineZxNum = new JLabel("当前在线用户人数:");
		onlineZxNum.setForeground(Color.WHITE);
		onlineZxNum.setFont(new Font("华文楷体", Font.BOLD, 14));
		onlineZxNum.setBounds(0, 0, 131, 31);
		getContentPane().add(onlineZxNum);

		title = new JLabel("【" + myname + "】欢迎使用GGtalk,你正在和【所有人】聊天");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("华文楷体", Font.BOLD, 14));
		title.setBounds(241, 0, 448, 31);
		getContentPane().add(title);
		// 发送按钮
		JButton Enter = new JButton("发送(Ctrl+Enter)");
		Enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取选择对象
				onlineId=list_b.getSelectedValue();
				System.out.println("你用按钮发送信息给：" + onlineId);
				if (onlineId == null) {
					onlineId = "所有在线用户";
				}
				if (onlineId.equals(myid)) {
					Tools.show(TalkFrame.this, "不能对自己发送");
					return;
				} // 判断内容
				String sendContent = ta_input.getText().trim();
				if (sendContent.equals("") || sendContent == null) {
					Tools.show(TalkFrame.this, "内容不能为空");
					return;
				} else {
					String[] split = onlineId.split("-");
					onlineId = split[0];
					SendInfo sendInfo = new SendInfo(myId, onlineId,
							sendContent);
					clientSession.write(sendInfo);
					addText(onlineId, "【你】对"+"【"+onlineId+"】说:\n"+sendContent);
					idAndcount.put(onlineId, 0);
					Object[] array =  onlineModel.toArray();//拿到列表
					for(int i=0;i<array.length;i++){
						String sid=(String) array[i];
						if(sid.startsWith(onlineId)){
							//如果是以要加的内容的id开始，改变内容
							onlineModel.set(i, onlineId);
							break;
						}
					}
					ta_input.setText("");
//					ta_show.setCaretPosition(ta_show.getText().length());// 滚动条自动滚动到最后
				}
			}
		});

		JButton button = new JButton(
				"\u6E05\u7A7A\u5E76\u4FDD\u5B58\u804A\u5929\u8BB0\u5F55");
		button.setMargin(new Insets(2, 10, 2, 10));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 清空聊天记录
				String talk = ta_show.getText();
				onlineId=list_b.getSelectedValue();
				if (onlineId == null) {
					onlineId = "所有在线用户";
				}
				String[] split = onlineId.split("-");
				onlineId = split[0];
				idAndContent.put(onlineId, "");
				Tools.writefile(myid, onlineId, talk);
				ta_show.setText("");
			}
		});
		button.setBackground(Color.WHITE);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.setBounds(583, 439, 135, 31);
		getContentPane().add(button);
		Enter.setBackground(Color.WHITE);
		Enter.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Enter.setBounds(583, 398, 131, 43);
		getContentPane().add(Enter);
		
		JLabel label_1 = new JLabel("\u53CC\u51FB\u597D\u53CB\u663E\u793A\u597D\u53CB\u4FE1\u606F");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("华文楷体", Font.BOLD, 13));
		label_1.setBounds(10, 356, 162, 15);
		getContentPane().add(label_1);

		onlineNum = new JLabel("0");
		onlineNum.setForeground(Color.WHITE);
		onlineNum.setIcon(null);
		onlineNum.setFont(new Font("华文楷体", Font.BOLD, 16));
		onlineNum.setBounds(136, 0, 36, 31);
		getContentPane().add(onlineNum);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(TalkFrame.class.getResource("/com/gh/res/\u804A\u5929.jpg")));
		lblNewLabel.setBounds(0, 0, 714, 472);
		getContentPane().add(lblNewLabel);

	}
}
