package com.jsp.dto;

import java.util.Date;

public class ReplyVO {
	private int rno;
	private int bno;
	private String replyer;
	private String replytext;
	private Date regdate;
	private Date updatedate;
	
	
	public ReplyVO() {}
	public ReplyVO(int rno, int bno, String replyer, String replytext, Date regdate, Date updatedate) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replyer = replyer;
		this.replytext = replytext;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", replyer=" + replyer + ", replytext=" + replytext
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	
}

