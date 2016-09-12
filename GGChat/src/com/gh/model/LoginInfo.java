package com.gh.model;

import java.io.Serializable;


public class LoginInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1498454258109872905L;
	private String id;
	private String pwd;
	public LoginInfo(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "LoginInfo [id=" + id + ", pwd=" + pwd + "]";
	}
	
}
