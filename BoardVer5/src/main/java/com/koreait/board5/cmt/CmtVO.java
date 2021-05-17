package com.koreait.board5.cmt;

public class CmtVO {
	private int iboard;
	private int icmt;
	private int iuser;
	private String cmt;
	private String regdate;
	
	private String unm; // 글쓰기 이름

	public int getIboard() {
		return iboard;
	}

	public void setIboard(int iboard) {
		this.iboard = iboard;
	}

	public int getIcmt() {
		return icmt;
	}

	public void setIcmt(int icmt) {
		this.icmt = icmt;
	}

	public int getIuser() {
		return iuser;
	}

	public void setIuser(int iuser) {
		this.iuser = iuser;
	}

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getUnm() {
		return unm;
	}

	public void setUnm(String unm) {
		this.unm = unm;
	}
}
