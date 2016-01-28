package com.gh.model;
/**
 * 用户模型
 * @author ganhang
 * 暂时没用，因为没涉及数据库和一些用户管理功能
 */
public class User {
	private String name;
	private String age;
	private String pwd;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", pwd=" + pwd + ", email=" + email + "]";
	}
	public User(String name, String age, String pwd, String email) {
		super();
		this.name = name;
		this.age = age;
		this.pwd = pwd;
		this.email = email;
	}
	public User() {
		super();
	}
	public User(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	public User(String name) {
		super();
		this.name = name;
	}
	
}
