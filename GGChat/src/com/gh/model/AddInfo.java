package com.gh.model;

import java.io.Serializable;

/**
 * 添加好友消息
 * @author student
 *
 */
public class AddInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -658656952417047998L;
	private String myid;
	private String friendId;

	public String getMyid() {
		return myid;
	}
	public void setMyid(String myid) {
		this.myid = myid;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public AddInfo(String myid, String friendId) {
		super();
		this.myid = myid;
		this.friendId = friendId;
	}
	@Override
	public String toString() {
		return "AddInfo [myid=" + myid + ", friendId=" + friendId + "]";
	}
}
