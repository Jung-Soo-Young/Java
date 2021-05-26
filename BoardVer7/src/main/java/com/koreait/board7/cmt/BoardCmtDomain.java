package com.koreait.board7.cmt;

public class BoardCmtDomain extends BoardCmtEntity {
	private String writerNm;		// 도메인 설정 (추가로 생성하는 것을 하나로 설정)

	public String getWriterNm() {
		return writerNm;
	}

	public void setWriterNm(String writerNm) {
		this.writerNm = writerNm;
	}
	
}
