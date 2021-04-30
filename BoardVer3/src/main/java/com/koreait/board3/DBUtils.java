package com.koreait.board3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	public static void main(String[] args) {
		try {
			getCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getCon() throws Exception {
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String URL = "jdbc:mysql://localhost:3308/boardver3";
		final String USER_NAME = "root";
		final String PASSWORD = "koreait";
		
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		System.out.println("연결 성공!!");
		
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		close(con, ps, null);
	}
	
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		// Connection은 (다리역할 = java랑 db랑 연결) 과 PreparedStatement 는 (명령어 실행) 필수적으로 사용 / ResultSet는 select문 일 때 추가 사용
		// con -> ps -> rs 순으로 만들어짐 / rs -> ps -> con 순으로 닫아야함!
		if(rs != null) {
			try { rs.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		if(ps != null) {
			try { ps.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		if(con != null) {
			try { con.close(); } catch (SQLException e) {e.printStackTrace();}
		}
	}
}
