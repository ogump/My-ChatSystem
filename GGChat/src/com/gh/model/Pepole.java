package com.gh.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * »À
 * @author student
 *
 */
public class Pepole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7950836664340113130L;
	private String id;
	private String password;
	private String name;
	private String sex;
	private String likes;
	private String email;
	private String info;
//	private boolean isOnline=false;
//	public boolean isOnline() {
//		return isOnline;
//	}
//	public void setOnline(boolean isOnline) {
//		this.isOnline = isOnline;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Pepole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pepole(String name, String id, String password, String sex,
			String likes, String email, String info) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.sex = sex;
		this.likes = likes;
		this.email = email;
		this.info = info;
	}
	@Override
	public String toString() {
		return "Pepole [id=" + id + ", password=" + password + ", name=" + name
				+ ", sex=" + sex + ", likes=" + likes + ", email=" + email
				+ ", info=" + info + "]";
	}
	
}
