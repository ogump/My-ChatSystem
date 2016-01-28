package com.gh.model;

import java.io.Serializable;

import com.gh.util.EnumInfoType;
/**
 * 消息对象
 * @author ganhang
 *
 */
public class Info implements Serializable{
	private String fromUser;//信息来自哪
	private String toUser;//发给谁
	private String content;//内容
	private EnumInfoType infoType;//信息类型
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public EnumInfoType getInfoType() {
		return infoType;
	}
	public void setInfoType(EnumInfoType infoType) {
		this.infoType = infoType;
	}
	public Info(String fromUser, String toUser, String sendTime, String content, EnumInfoType infoType) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;

		this.content = content;
		this.infoType = infoType;
	}
	@Override
	public String toString() {
		return "Info [fromUser=" + fromUser + ", toUser=" + toUser  + ", content=" + content
				+ ", infoType=" + infoType + "]";
	}
	public Info() {
		super();
	}
	
}
