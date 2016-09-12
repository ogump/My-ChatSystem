 package com.gh.control;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.gh.model.Pepole;
import com.gh.model.SendInfo;
import com.gh.utils.Tools;
import com.gh.view.LoginFrame;
import com.gh.view.RegisterFrame;
import com.gh.view.TalkFrame;

/**
 * 客户端消息处理程序
 * 
 * @author NIIT
 *
 */
public class ClientIoHandler extends IoHandlerAdapter {
	private RegisterFrame registerFrame;
	private LoginFrame loginFrame;
	private NioSocketConnector client;
	private boolean isPwd = true;
	private TalkFrame talkFrame;
	private String[] sses;



	public ClientIoHandler(LoginFrame loginFrame, NioSocketConnector client) {
		this.loginFrame = loginFrame;
		this.client = client;
	}

	/**
	 * 连接到服务器端 触发
	 */
	@Override
	public void sessionCreated(IoSession session)  throws Exception{
		System.out.println("【ClientIoHandler会话创建】");
	}

	/**
	 * 服务器关闭时触发
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception{

		System.out.println("【ClientIoHandler会话结束");
		if (isPwd) {
			client.dispose();
			Tools.show(loginFrame, "服务器关闭，连接断开");
			talkFrame.dispose();
			System.exit(0);
		}
	}

	/**
	 * 收到服务器消息触发
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception	 {
		if(message instanceof Pepole){
			Pepole p=(Pepole) message;
			System.out.println("收到pepole信息"+message);
			talkFrame.newInfoFrame(p);
		}
		else if (message instanceof SendInfo) {
			SendInfo sendInfo = (SendInfo) message;
			System.out.println("服务器向我发送:" + message);
			String srcId=sendInfo.getSrcId();
			String descId=sendInfo.getDescId();
			String content=sendInfo.getContent();
			/**
			 * 接收到消息只有两种情况
			 * 1 是别人群发给我 也就是srcId是别人也可能是我， descId是所有人
			 * 2 是srcId是别人 descId是我
			 * 这里理了好久
			 */
			if(descId.equals("所有在线用户")){
				//descId是所有人
				if(srcId.equals(talkFrame.getMyId())){
					//如果是我发出的
					/**
					 * 我发出的我自己（talkFrame）显示算了，所以注释了
					 */
//					talkFrame.addText("所有在线用户", "【你】对【所有在线用户】说：\n"+content);
				}else{
					//不是我发的
					talkFrame.addText("所有在线用户", "【"+srcId+"】对【所有在线用户】说：\n"+content);
				}
			}else{
				//descId是我
				talkFrame.addText(srcId, "【"+srcId+"】对【你】说：\n"+content);
			}
			
		} else if (message instanceof String) {
			System.out.println("收到服务器String消息" + message);
			String s = (String) message;
			if (s.startsWith("允许登陆")) {// 判断标记
				loginFrame.dispose();
				sses = s.split("~");
				talkFrame = new TalkFrame(sses[1], sses[2], session);// 启动主界面
				talkFrame.setVisible(true);

			} else if ("你已经在线".equals(s)) {
				// 界面添加
				client.dispose();
				Tools.show(loginFrame, "你已经在线");
			}else if ("不许登陆".equals(s)) {
				client.dispose();
				if (isPwd) {// 密码错误断开连接和服务器断开连接区分开
					isPwd = false;
					Tools.show(loginFrame, "登陆名或密码错误！如果没有账号请注册！");
				}

			} else if ("添加好友成功".equals(s)) {
				// 界面添加
				Tools.show(talkFrame, "添加好友成功");
				talkFrame.addfiend(talkFrame.getOnlineId());

			} else if ("添加好友失败".equals(s)) {
				Tools.show(talkFrame, "添加好友失败，好友不存在或已经存在！");

			} else if ("注册成功".equals(s)) {
				//关闭注册界面
				Tools.show(registerFrame, "注册成功！请返回登陆");
				registerFrame.dispose();
				//显示登陆界面
				loginFrame.setVisible(true);

			} else if ("注册失败".equals(s)) {
				Tools.show(registerFrame, "注册失败！该id已存在，请换个id！");

			}else if (s.startsWith("好友列表")) {
				System.out.println("接收到服务器来的好友列表");
				s = s.substring(5);
				String[] split = s.split("~");
				talkFrame.UpdataList(split);// 更新好友列表

			} else if (s.startsWith("在线好友")) {
				System.out.println("接收到服务器来的在线好友");
				s = s.substring(5);
				String[] split = s.split("~");
				talkFrame.UpdataOnlineList(split);// 更新在线列表

			} else if (s.startsWith("用户下线")) {
				s = s.substring(5);
				talkFrame.delOnlineList(s);// 删除在线列表
				// 文本域通知
				talkFrame.addText("所有在线用户","服务器：用户" + s + "下线");
				
			} else if (s.equals("删除好友成功")) {
				// 界面删除
				Tools.show(talkFrame, "删除好友成功");
				talkFrame.DelUser(talkFrame.getFriendId());
				
			} else if (s.equals("删除好友失败")) {
				Tools.show(talkFrame, "要删除的好友不存在");
			} else if (s.startsWith("用户上线")) {
				s = s.substring(5);
				talkFrame.addOnlineList(s);
				// 文本域通知
				if (!s.equals(talkFrame.getMyId()))// 自己上线不显示
					
					talkFrame.addText("所有在线用户","服务器：用户" + s + "上线");
			}
//			else if (s.startsWith("用户消息")) {
//				s = s.substring(5);
//				String[] split = s.split("/");
//				// 更新文本域
//				if (!s.equals(talkFrame.getMyId()))
//					talkFrame.addText("用户：" + split[0] + ":\n" + split[1]);
//			}
			else if(s.equals("用户不在线")){
				
				talkFrame.addText(talkFrame.getFriendId(),"服务器：用户不在线");
			}
			else if(s.equals("你被禁言")){
				System.err.println("收到禁言");
				
				talkFrame.setTalk(false);//不许编辑
			}else if(s.equals("你被T下线")){
				System.err.println("收到被T");
				talkFrame.dispose();//界面关了
				Tools.show(talkFrame, "你被服务器T下线请联系服务器管理员！");
				System.exit(0);//线程关了
			}else if(s.equals("解除禁言")){
				System.err.println("收到解除禁言");
				talkFrame.setTalk(true);//允许编辑
			}
		}

	}// 方法末
	public void setRegisterFrame(RegisterFrame registerFrame) {
		this.registerFrame = registerFrame;
	}

}
