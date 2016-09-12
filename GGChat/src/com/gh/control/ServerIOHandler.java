package com.gh.control;

import java.net.SocketAddress;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.gh.model.AddInfo;
import com.gh.model.DelInfo;
import com.gh.model.LoginInfo;
import com.gh.model.Pepole;
import com.gh.model.SendInfo;
import com.gh.utils.Tools;
import com.gh.view.SeverFrame;

/**
 * 服务器消息处理程序
 * 
 * @author NIIT
 *
 */
public class ServerIOHandler extends IoHandlerAdapter {

	// 存储所有的客户端
	private List<IoSession> clients = new ArrayList<>();// 当前连接
	private HashMap<String, String> linksInfo = new HashMap<String, String>();// 连接与地址
	private HashMap<String, IoSession> idAndSession = new HashMap<String, IoSession>();// 连接与地址
	private ArrayList<Pepole> pepoles = new ArrayList<Pepole>();
	private ArrayList<String> onlinePepoles = new ArrayList<String>();// 在线用户
	private LoginInfo lgi;
	private SeverFrame severFrame;

	public ServerIOHandler(SeverFrame severFrame) {
		this.severFrame = severFrame;
	}

	/**
	 * 有客户端连接 触发
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// 添加
		clients.add(session);
		System.out.println(session.getRemoteAddress() + "连接服务器");
		// 发生会话创建的 客户端地址
	}

	/**
	 * 客户端关闭会话 触发
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// 发生会话结束的 客户端地址
		SocketAddress address = session.getRemoteAddress();
		System.out.println("【ServerIOHandler会话退出】" + address);// 调试输出
		// nowp.setOnline(false);//设置当前用户状态
		if (linksInfo.get(session.getRemoteAddress().toString()) != null) {// 排除尝试连接和注册连接下线时提示
			severFrame.addText(linksInfo.get(session.getRemoteAddress()
					.toString()) + "下线");// 更新文本域信息
			severFrame.delUser(linksInfo.get(session.getRemoteAddress()
					.toString()));// 在服务端 在线用户列表上删除
			severFrame.delnotalk(linksInfo.get(session.getRemoteAddress()
					.toString()));//在禁言列表删一下
			// 发送给别人更新在线好友
			onlinePepoles.remove(linksInfo.get(session.getRemoteAddress()
					.toString()));
			// linksInfo.remove(session.getRemoteAddress().toString());//联系移除
			idAndSession.remove(linksInfo.get(session.getRemoteAddress()
					.toString()));// id与session联系移除
			sendMessageToAllClient("用户下线~"
					+ linksInfo.get(session.getRemoteAddress().toString()));
		}
		// 删除连接
		clients.remove(session);

	}

	/**
	 * 收到客户端消息 触发
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception	{
		// 客户端地址
		SocketAddress address = session.getRemoteAddress();
		System.err.println("服务器收到信息:"+message);
		// 如果是登陆信息
		if (message instanceof LoginInfo) {
			lgi = (LoginInfo) message;
			// 查文件获得数据库信息
			pepoles = Tools.readFile();
			boolean flag = true;// 验证登陆标记，true表示密码错误
			for (Pepole p : pepoles) {
				if (lgi.getId().equals(p.getId())
						&& lgi.getPwd().equals(p.getPassword())) {
					// 判断是不是已经登陆
					boolean isOnline = false;
					for (String onlineId : onlinePepoles) {
						if (p.getId().equals(onlineId)) {
							isOnline = true;
						}
					}
					if (isOnline) {//如果在线
						session.write("你已经在线");
						flag = false;
						break;
					} else {
						session.write("允许登陆~" + p.getName() + "~" + p.getId());// 返回一个通过的标记加用户的姓名用于客户端显示用户姓名
						flag = false;// 标记验证通过
						onlinePepoles.add(p.getId());// 添加到在线人
						linksInfo.put(address.toString(), p.getId());// 添加地址与id映射
						idAndSession.put(p.getId(), session);// 添加id与session的映射
						severFrame.addText(p.getId() + "上线");// 服务器端显示用户登录
						severFrame.AddUserToList(p.getId());// 服务器在线列表显示id
						// 发送消息给所有在线的用户
						sendMessageToAllClient("用户上线~" + p.getId());
						break;
					}
				}
			}
			if (flag) {
				session.write("不许登陆");// 密码错误
			}
			// 如果是添加好友信息
		} else if (message instanceof AddInfo) {
			AddInfo addInfo = (AddInfo) message;
			// 从文本中查找是不是有这个id
			pepoles = Tools.readFile();
			// 是否已经添加为好友
			boolean flag = true;
			for (Pepole p : pepoles) {
				if (p.getId().equals(addInfo.getFriendId())) {
					flag = false;
					if (Tools.writeFile(addInfo)) {
						session.write("添加好友成功");
						System.out.println("写入成功");
					} else {
						session.write("添加好友失败");
						System.out.println("写入失败");
					}
				}
			}
			if (flag) {
				session.write("添加好友失败");
				System.out.println("写入失败");
			}
			// 如果是注册信息
		} else if (message instanceof Pepole) {
			Pepole p = (Pepole) message;
			if (Tools.writeFile(p)) {
				session.write("注册成功");
			} else {
				session.write("注册失败");
			}
			// 如果是字符串
		} else if (message instanceof String) {
			String s = (String) message;
			if ("查找好友".equals(s)) {
				System.out.println("服务器收到查询"
						+ linksInfo.get(session.getRemoteAddress().toString())
						+ "的好友");
				ArrayList<Pepole> friends = Tools.readUseFriendFile(linksInfo
						.get(session.getRemoteAddress().toString()));
				String friendsName = "好友列表~";
				for (Pepole p : friends) {
					friendsName += p.getId() + "~";// 发送好友的id
				}
				friendsName = friendsName
						.substring(0, friendsName.length() - 1);// 去掉最后一个/
				session.write(friendsName);
			} else if ("查找在线好友".equals(s)) {
				String onlineFriends = "在线好友~";
				for (String op : onlinePepoles) {
					onlineFriends += op + "~";
				}
				onlineFriends = onlineFriends.substring(0,
						onlineFriends.length() - 1);
				session.write(onlineFriends);
			}else if(s.startsWith("好友信息")){
				s=s.substring(5);
				ArrayList<Pepole> use = Tools.readFile();
				for(Pepole p:use){
					if(p.getId().equals(s)){
						session.write(p);
					}
				}
			}
			
			
		} else if (message instanceof DelInfo) {
			DelInfo delInfo = (DelInfo) message;
			if (Tools.writeFile(delInfo)) {
				// true 好友文件里有且删除成功
				// false 好友文件没有那id 或 该id好友为空，文件不存在
				session.write("删除好友成功");
				System.out.println("删除好友成功");
			} else {
				session.write("删除好友失败");
				System.out.println("删除好友失败");
			}
		} else if (message instanceof SendInfo) {
			SendInfo sendInfo = (SendInfo) message;
			if (sendInfo.getDescId().equals("所有在线用户")) {// 如果目标id是所有人
				System.err.println("服务器转发" + sendInfo + "给所有人");
				severFrame.addText("服务器转发消息" + sendInfo + "给"+ sendInfo.getDescId());
				sendMessageToAllClient(sendInfo);
			} else {// 发送单个好友
					// 跟据映射id与session映射拿到session
				System.err.println("服务器转发消息" + sendInfo + "给"+ sendInfo.getDescId());
				
				severFrame.addText("服务器转发消息" + sendInfo + "给"+ sendInfo.getDescId());
				if (idAndSession.get(sendInfo.getDescId()) != null)// 如果目标id在线
					idAndSession.get(sendInfo.getDescId()).write(sendInfo);
				else {
					// 目标不在线
					session.write("用户不在线");
				}
			}
		}
	}

	/**
	 * 发送消息给所有客户端
	 * 
	 * @param msg
	 */
	public void sendMessageToAllClient(Object msg) {
		for (IoSession session : clients) {
			session.write(msg);
		}
	}
	/**
	 * 根据id拿session
	 * @param id
	 * @return
	 */
	public IoSession getIdSession(String id) {
		return idAndSession.get(id);
	}
}
