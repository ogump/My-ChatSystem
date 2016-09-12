package com.gh.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.gh.model.Info;
import com.gh.model.LoginInfo;

public class UserService {
	private LoginInfo logininfo = null;
	private ClientFrame clientframe = null;
	private boolean flag = true;
	private Info getInfo = null;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	private ObjectOutputStream bw;
	private ObjectInputStream br;
	public void login(final String username, final String password, final JFrame frame) {
		//接收消息线程
		Thread t=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Socket sk = new Socket("192.168.1.102", 8000);
					//这里被堵了好久，必须要先创建bw再创建br,因为scoket不许客户端和服务端同时创建输入流否者会堵塞Scoket通道
					bw =new ObjectOutputStream(sk.getOutputStream());
					br = new ObjectInputStream(sk.getInputStream());
					logininfo = new LoginInfo(username, password);
					bw.writeObject(logininfo);//向服务器发送登录信息
					bw.flush();
					//从服务接收登录信息
					LoginInfo objflag = (LoginInfo) br.readObject();
					if (objflag.isFlag()) {// 如果可以登录
						// 创建聊天界面
						clientframe = new ClientFrame(logininfo.getName(),UserService.this);
						// 聊天界面显示欢迎信息
						clientframe.updateText("服务器：欢迎登录！");
						frame.dispose();
						// 等待接收消息 
						while (isFlag()) {
							getInfo = new Info();
							getInfo = (Info) br.readObject();
							switch (getInfo.getInfoType()) {
							case SEND_INFO:
								clientframe.updateText("用户："+getInfo.getFromUser()+"说："+getInfo.getContent());
								break;
							case ADD_USER:
								clientframe.AddUserToList(getInfo.getContent());
								break;
							case EXIT:
								clientframe.DelUser(getInfo.getFromUser());
								clientframe.updateText("服务器：用户"+getInfo.getFromUser()+"下线了");
								break;
							default:
								break;
							}

						}
					} else {
						JOptionPane.showMessageDialog(frame, "用户名或密码错误");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "服务器连接异常");
					 //e.printStackTrace();
				}
			}
		});
		t.start();
	}
	//发送消息线程
	public void send(Info info){
		try {
			bw.writeObject(info);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
