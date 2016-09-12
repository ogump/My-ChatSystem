package com.gh.utils;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.gh.model.AddInfo;
import com.gh.model.DelInfo;
import com.gh.model.Pepole;

public class Tools {
	/**
	 * 数组转Pepole
	 * @param sp
	 * @return
	 */
	public static Pepole arrayToPepole(String[] sp) {
		String id = sp[0];
		String password = sp[1];
		String name = sp[2];
		String sex = sp[3];
		String likes = sp[4];
		String email = sp[5];
		String info = sp[6];
		return new Pepole(name, id, password, sex, likes, email, info);
	}

	/**
	 * 得到当前时间的字符串
	 * 
	 * @return
	 */
	public static String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	/**
	 * 弹窗
	 * @param c
	 * @param message
	 */
	public static void show(Component c, String message) {
		JOptionPane.showMessageDialog(c, message);
	}

	/**
	 * 读出该id的所有好友
	 * @param myId
	 */
	public static ArrayList<Pepole> readUseFriendFile(String myId) {
		ArrayList<Pepole> friends = new ArrayList<Pepole>();
		try {
			
			File myIdFile = new File("ServerData/"+myId + ".txt");
			if (myIdFile.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(myIdFile));
				String friendId;
				ArrayList<Pepole> pepoles = readFile();
				while ((friendId = br.readLine()) != null) {
					if (!"\n".equals(friendId) && !"".equals(friendId)) {
						for (Pepole p : pepoles) {
							if (p.getId().equals(friendId))
								friends.add(p);
						}
					}
				}
				br.close();
				return friends;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friends;
	}

	/**
	 * 从文件读入所有pepole
	 * 
	 * @return
	 */
	public static ArrayList<Pepole> readFile() {
		ArrayList<Pepole> pepoles = new ArrayList<Pepole>();
		try {
			File useFile = new File("ServerData/use.txt");
			if (useFile.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(useFile));
				String str;
				while ((str = br.readLine()) != null) {
					if (!"".equals(str) || !"\\n".equals(str)) {
						pepoles.add(Tools.arrayToPepole(str.split("~")));
					}
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pepoles;
	}

	/**
	 * 把pepole写入use文件
	 * 
	 * @param p
	 * @param path
	 */
	public static boolean writeFile(Pepole p) {
		
		try {
			File file=new File("ServerData/");
			if(!file.exists()){
				file.mkdirs();
			}
			File useFile = new File("ServerData/use.txt");
			if (!useFile.exists()) {
				useFile.createNewFile();
			}
			
			ArrayList<Pepole> ps = readFile();
			String str = p.getId() + "~" + p.getPassword() + "~" + p.getName()
					+ "~" + p.getSex() + "~" + p.getLikes() + "~"
					+ p.getEmail() + "~" + p.getInfo() + "\n";
			boolean flag = false;// 用户id存在标记
			for (Pepole people : ps) {
				if (p.getId().equals(people.getId())) {
					flag = true;
				}
			}
			if (flag) {
				return false;
			} else {
				BufferedWriter ubw = new BufferedWriter(new FileWriter(useFile,
						true));
				ubw.write(str);// 写入个人信息
				ubw.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 把addInf写入friend文件
	 * 
	 * @param p
	 * @param path
	 */
	public static boolean writeFile(AddInfo addInfo) {
		try {
			File useFile = new File("ServerData/"+addInfo.getMyid() + ".txt");
			ArrayList<String> use = new ArrayList<String>();
			if (!useFile.exists()) {
				useFile.createNewFile();
			}
			// 先读出来
			BufferedReader br = new BufferedReader(new FileReader(useFile));
			String str = "";
			while ((str = br.readLine()) != null) {
				use.add(str);
			}
			br.close();
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(useFile, true));
			for (String s : use) {
				if (addInfo.getFriendId().equals(s)) {// 如果里面已经有了
					bw.close();
					return false;
				}
			}
			bw.write(addInfo.getFriendId() + "\n");
			bw.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除好友，重写好友文件
	 * 
	 * @param p
	 * @param path
	 */
	public static boolean writeFile(DelInfo delInfo) {
		try {
			File useFile = new File("ServerData/"+delInfo.getMyid() + ".txt");
			ArrayList<String> use = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(useFile));
			
			if (!useFile.exists()) {
				System.out.println("文件不存在");
				br.close();
				return false;// 文件不存在
			}
			// 先从文件读出来
			String str = "";
			while ((str = br.readLine()) != null) {
				use.add(str);
			}
			br.close();
			// 读完后一次性覆盖写进去
			boolean flag = true;
			str = "";// 重新初始化
			for (String s : use) {
				if (!delInfo.getFriendId().equals(s)) {// 如果等于要删除的id
					str += s + "\n";
				}
				if (delInfo.getFriendId().equals(s)) {
					flag = false;// 标记说明要删的id在好友文件里
				}
			}
			if (flag) {
				System.out.println("没找到id");
				return false;
			} else {
				BufferedWriter bw = new BufferedWriter(new FileWriter(useFile));
				bw.write(str + "\n");
				bw.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;// 删除失败
		}
	}

	/**
	 * 根据id获得Pepole
	 * 
	 * @param id
	 * @return
	 */
	public static Pepole findPepole(ArrayList<Pepole> ps, String id) {
		for (Pepole p : ps) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}
	/**
	 * 以我的id，对方的id为文件名写入内容到文件
	 * 写聊天记录
	 * @param myId
	 * @param toId
	 * @param talk
	 * @return
	 */
	public static boolean writefile(String myId,String toId,String talk){
		File talkFile=new File("data/GGtalk_"+myId+"_"+toId+".txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(talkFile));
			if(!talkFile.exists()){
				talkFile.createNewFile();
			}
			bw.write(talk);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 从文件读入ip
	 * @return
	 */
	public static String readIpAndPort(){
		
		try {
			File file=new File("data/IP.txt");
			if(!file.exists()){
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write("192.168.48.2~9000");
				bw.close();
				return "192.168.48.2~9000";
			}else{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String str= br.readLine();
				if(str==null){
					br.close();
					return "192.168.48.2~9000";
				}else{
					br.close();
					return str;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "192.168.48.2~9000";
	}
	/**
	 * 读端口号
	 * @return
	 */
	public static int readPort(){
		try {
			File file=new File("ServerData/IP.txt");
			if(!file.exists()){
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write("9000");
				bw.close();
				return 9000;
			}else{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String str= br.readLine();
				if(str==null){
					br.close();
					return 9000;					
				}else{
					br.close();
					return Integer.parseInt(str);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 9000;
	}
}
