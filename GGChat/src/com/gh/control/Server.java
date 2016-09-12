package com.gh.control;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.gh.utils.Tools;
import com.gh.view.SeverFrame;

/**
 * 服务器处理类
 * 
 */
public class Server {
	 public  int port=8888;	//定义一个端口
	 private  SeverFrame severFrame;
	private NioSocketAcceptor server;
	private ServerIOHandler serverIOHandler;
	public Server(SeverFrame severFrame) {
		this.severFrame = severFrame;
	}
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 程序入口
	 * @param args
	 * @throws IOException 
	 */
	public  void startServer()  {
		 server = new NioSocketAcceptor();
		//设置内容过滤器
		DefaultIoFilterChainBuilder  filterChain = server.getFilterChain();
		filterChain.addLast("myChin", new ProtocolCodecFilter(  
                new ObjectSerializationCodecFactory()));
		
		//filterChain.addLast("textCode",new ProtocolCodecFilter(
		//			new TextLineCodecFactory(Charset.forName("UTF-8"))));
		
		serverIOHandler = new ServerIOHandler(severFrame);
		server.setHandler(serverIOHandler);
		//服务器监听端口 --- 服务器启动
		try {
			server.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			Tools.show(severFrame, "端口已经占用，请换个端口！");
		}
	}
	
	public IoSession getIdSession(String id) {
		return  serverIOHandler.getIdSession(id);
	}
}
