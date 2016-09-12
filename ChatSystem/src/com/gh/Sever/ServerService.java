package com.gh.Sever;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.gh.model.Info;
import com.gh.model.LoginInfo;
import com.gh.util.EnumInfoType;

/**
 * 服务器客户端监听
 * 
 * @author ganhang
 */
public class ServerService {
	private Info currInfo = null;
	private ServerFrame serFrame;
	private boolean flag;
	private UserServerThread ust;
	ExecutorService es = Executors.newScheduledThreadPool(1);
	// 保存所有在线用户服务线程
	private Vector<UserServerThread> userThreads;
	public ServerService(ServerFrame serFrame) {
		this.serFrame = serFrame;
	}
	public void startSever() {
		flag = true;
		try {
			ServerSocket server = new ServerSocket(8000);
			userThreads = new Vector<UserServerThread>();
			while (flag) {
				Socket client = server.accept();
				System.out.println("Server：有用户连接");
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				LoginInfo logininfo = (LoginInfo) ois.readObject();// 获取用户帐号密码
				System.out.println(logininfo);
				// 登录验证
				if (UserLogin.islogin(logininfo.getName(), logininfo.getPwd())) {
					logininfo.setFlag(true);
					oos.writeObject(logininfo);// 发送确认登录信息
					oos.flush();
					// 保存当前用户信息
					currInfo = new Info();
					currInfo.setFromUser(logininfo.getName());
					// 在服务器端显示用户登录信息
					serFrame.updateText("用户" + logininfo.getName() + "--已上线！");
					// 创建用户服务线程
					UserServerThread ust = new UserServerThread(currInfo, ois, oos);// 创建用户服务进程
					userThreads.add(ust);// 添加到进程池
					es.execute(ust);// 启动线程
				} else
					oos.writeObject(logininfo);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户服务线程
	 * 
	 * @author ganhang
	 */
	class UserServerThread implements Runnable {
		private Info info = null;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;

		public UserServerThread(Info info, ObjectInputStream ois, ObjectOutputStream oos) {
			this.info = info;
			this.oos = oos;
			this.ois = ois;
		}

		@Override
		public void run() {
			// 更新服务器的在线用户列表
			serFrame.AddUserToList(currInfo.getFromUser());
			// 先获得在线用户的列表
			StringBuffer sb = new StringBuffer();
			for (UserServerThread u : userThreads) {
				sb.append(u.info.getFromUser()).append(",");
			}
			System.out.println(sb.toString());
			// 首先向线程池里每个用户发送一个自己的在线用户列表
			for (UserServerThread usts : userThreads) {
				try {
					currInfo.setInfoType(EnumInfoType.ADD_USER);
					currInfo.setContent(sb.toString());
					usts.oos.writeObject(currInfo);// 注意要写那个线程的输出流
					usts.oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 等待接收用户信息
			System.out.println(flag);
			new Thread(new Wait(ois, oos)).start();

		}
		public void send(Info sendinfo) throws IOException {
			oos.writeObject(sendinfo);
			oos.flush();
		}
	}

	// 接收信息
	class Wait implements Runnable {
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		private boolean wait = true;

		public Wait(ObjectInputStream ois, ObjectOutputStream oos) {
			this.oos = oos;
			this.ois = ois;
		}

		@Override
		public void run() {
			while (wait) {
				try {
					// 接收信息必须重开线程因为会把当前线程阻塞
					Info info = (Info) ois.readObject();
					System.out.println(Thread.currentThread().getName() + "收到一个send信息");
					System.out.println(info);
					switch (info.getInfoType()) {
					case SEND_INFO:
						if (info.getToUser().equals("所有人")) {
							for (UserServerThread user : userThreads) {
								//这里容易发错人，注意每个用户都有一个服务线程，哪个用户要接收信息就由它的服务线程去发给它，其他用户的服务线程发不了。
								if (!info.getFromUser().equals(user.info.getFromUser()))
									user.send(info);
								System.out.print(user.info.getFromUser() + "/");
							}
							System.out.println("消息发送名单");
						} else {
							for (UserServerThread user : userThreads) {
								if (info.getToUser().equals(user.info.getFromUser())) {
									user.send(info);
								}
							}
						}
						break;
					case EXIT:
						serFrame.DelUser(info.getFromUser());
						serFrame.updateText("用户" + info.getFromUser() + "下线了");
						for (UserServerThread user : userThreads) {
							if (!info.getFromUser().equals(user.info.getFromUser())) {
								user.send(info);
							} 
							//这里ust保存的是最后登录的用户的线程因为一直在被覆盖，
							//需要重新写入当前要删的
							else ust = user;
							System.out.print(user.info.getFromUser() + ",");
						}
						System.out.println("退出发送名单");
						// 删除服务器上的该在线用户
						wait = false;
						System.out.println("移除的用户：" + ust.info.getFromUser());
						//这里容易删错用户线程
						userThreads.remove(ust);
						break;
					default:
						break;
					}
				} catch (ClassNotFoundException | IOException e) {
					 e.printStackTrace();
				}

			}
		}
	}

}
