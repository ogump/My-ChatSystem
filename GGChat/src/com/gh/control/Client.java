package com.gh.control;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.gh.model.AddInfo;
import com.gh.model.DelInfo;
import com.gh.model.LoginInfo;
import com.gh.model.SendInfo;
import com.gh.utils.Tools;
import com.gh.view.LoginFrame;
import com.gh.view.LoginFrame;
import com.gh.view.RegisterFrame;

/**
 * 客户端
 */
public class Client {
	private  String IP;
	private  int Port;
	private  IoSession session;
	private NioSocketConnector client;
	private LoginFrame loginFrame;
	private static LoginInfo loginInfo;
	private RegisterFrame registerFrame;
	public Client(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public void setPort(int port) {
		Port = port;
	}

	/**
	 * 启动客户端连接
	 */
	public boolean connect() {
		// 实例化 非阻塞的 Socket连接
		client = new NioSocketConnector();
		// 设置内容过滤器
		DefaultIoFilterChainBuilder filterChain = client.getFilterChain();
		//filterChain.addLast("textCode", new ProtocolCodecFilter(
		//		new TextLineCodecFactory(Charset.forName("UTF-8"))));
		filterChain.addLast("myChin", new ProtocolCodecFilter(
				new ObjectSerializationCodecFactory()));
		// 客户端处理程序
		ClientIoHandler clientIoHandler = new ClientIoHandler(loginFrame,client);
		client.setHandler(clientIoHandler);
		clientIoHandler.setRegisterFrame(registerFrame);
		// 连接服务器
		ConnectFuture future = client.connect(new InetSocketAddress(
				IP, Port));
		// 等待
		future.awaitUninterruptibly();
		// 得到会话对象
		try {
			session = future.getSession();
			return true;
		} catch (Exception e) {
			Tools.show(loginFrame, "无法连接服务器，服务器没有启动");
			client.dispose();
			if(registerFrame!=null)
			registerFrame.dispose();
			return false;
		}
		// session.getCloseFuture().awaitUninterruptibly();

	}

	public void sendMessage(SendInfo msg) {
		session.write(msg);
	}
	public void sendMessage(AddInfo msg) {
		session.write(msg);
	}
	public void sendMessage(DelInfo msg) {
		session.write(msg);
	}
	public void sendMessage(LoginInfo loginInfo) {
		session.write(loginInfo);
	}

	public  IoSession getSession() {
		return session;
	}
	
	public void  login(String username, String password) {
		loginInfo = new LoginInfo(username, password);
		if (connect()) {
			System.out.println("连接成功");
			sendMessage(loginInfo);
		}
	}
	public void setRegisterFrame(RegisterFrame regis){
		this.registerFrame=regis;
	}
}
