package com.gh.model;

import java.io.Serializable;

/**
 * œ˚œ¢¿‡
 * @author student
 *
 */
public class SendInfo implements Serializable{
	private static final long serialVersionUID = -5385820091031719202L;
	private String srcId;
	private String descId;
	private String content;
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public String getDescId() {
		return descId;
	}
	public void setDescId(String descId) {
		this.descId = descId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public SendInfo(String srcId, String descId, String content) {
		super();
		this.srcId = srcId;
		this.descId = descId;
		this.content = content;
	}
	@Override
	public String toString() {
		return "SendInfo [" + (srcId != null ? "srcId=" + srcId + ", " : "")
				+ (descId != null ? "descId=" + descId + ", " : "")
				+ (content != null ? "content=" + content : "") + "]";
	}
	
}
