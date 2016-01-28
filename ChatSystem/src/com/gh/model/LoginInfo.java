package com.gh.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginInfo implements Serializable{
	private String name;
	private String pwd;
	private boolean flag=false;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public LoginInfo(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	public LoginInfo() {
		super();
	}
	@Override
	public String toString() {
		return "LoginInfo [name=" + name + ", pwd=" + pwd + ", flag=" + flag + "]";
	}
	
}
