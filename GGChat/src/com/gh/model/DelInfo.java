package com.gh.model;

import java.io.Serializable;

/**
 * É¾³ýºÃÓÑÏûÏ¢
 * @author student
 *
 */
public class DelInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3901886279457407854L;
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
	public DelInfo(String myid, String friendId) {
		this.myid = myid;
		this.friendId = friendId;
	}
	@Override
	public String toString() {
		return "DelInfo [myid=" + myid + ", friendId=" + friendId + "]";
	}
	
}
