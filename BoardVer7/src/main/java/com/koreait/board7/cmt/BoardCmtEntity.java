package com.koreait.board7.cmt;

public class BoardCmtEntity {
	private int icmt;
	private int iboard;
	private int iuser;
	private String regdate;
	private String cmt;
	
	
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public int getIcmt() {
		return icmt;
	}
	public void setIcmt(int icmt) {
		this.icmt = icmt;
	}
	public int getIboard() {
		return iboard;
	}
	public void setIboard(int iboard) {
		this.iboard = iboard;
	}
	public int getIuser() {
		return iuser;
	}
	public void setIuser(int iuser) {
		this.iuser = iuser;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
