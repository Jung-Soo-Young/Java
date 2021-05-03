package com.koreait.board3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Data Access Object (DB담당)
public class BoardDAO {
	
	// 글 등록 (Create)
	public static int insertBoard(BoardVO3 vo) { // 다리역할, 객체생성
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_board (title, ctnt) "
				+ " VALUES (?,?) "; 		// 쿼리문 작성

		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql); // ps 객체 생성 후 메소드 호출
			
			ps.setString(1, vo.getTitle()); 
			ps.setString(2, vo.getCtnt()); // (?, ?) = DB에서 value 값을 받아오는 것인데 매번 받아와야 하니 vo.getTitle 와 vo.getCtnt로 설정
			
			return ps.executeUpdate(); // 완성된 문장 실행 (insert, update, delete) 영향을 미친 레코드 수
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
		return 0;
	}
	
	// Select 부분
	public static List<BoardVO3> selBoardList() {
		List<BoardVO3> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null; // 클래스 명
		ResultSet rs = null; // 결과물을 받아야 하기 때문에 필요
		
		String sql = " SELECT iboard, title, regdt FROM t_board " // DB-SELECT문, 쿼리문 안에 세미콜론 넣지 않기!
					+ " ORDER BY iboard DESC ";					  // 글 목록 내림차순 정렬
		
		try {
			con = DBUtils.getCon();	// getCon() = throws - 예외발생문
			ps = con.prepareStatement(sql); // 메소드 명	
			rs = ps.executeQuery(); // Select 구문만 사용 ( executeQuery() )
			
			while(rs.next()) {	// rs.next()를 실행해서 레코드가 있을 경우 다음 실행
				BoardVO3 vo = new BoardVO3();
				list.add(vo);		// 레퍼런스 변수여서 어느 위치에 놓여도 상관이 없다.
				
				int iboard = rs.getInt("iboard");
				String title = rs.getString("title");
				String regdt = rs.getString("regdt");
				
				vo.setIboard(iboard);
				vo.setTitle(title);
				vo.setRegdt(regdt);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		
		return list;
	}
	
	public static BoardVO3 selBoard(int iboard) {
		Connection con = null;
		PreparedStatement ps = null; 
		ResultSet rs = null; 
		
		String sql = "SELECT title, regdt, ctnt FROM t_board WHERE iboard = ?";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql); 
			ps.setInt(1, iboard);
			
			rs = ps.executeQuery(); 
			
			if(rs.next()) {	
				BoardVO3 vo = new BoardVO3();
						
				String ctnt = rs.getString("ctnt");
				String title = rs.getString("title");
				String regdt = rs.getString("regdt");
				
				vo.setIboard(iboard);
				vo.setTitle(title);
				vo.setCtnt(ctnt);
				vo.setRegdt(regdt);
				
				return vo;
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;
	}
	
	// Update 수정 부분
		public static int updBoard(BoardVO3 vo) {	// 
			Connection con = null;
			PreparedStatement ps = null;
			
			String sql = " UPDATE t_board "		// Update 쿼리문 작성
					+ " SET title = ?"
					+ " , ctnt = ? "
					+ " WHERE iboard = ? ";

			try {
				con = DBUtils.getCon();
				ps = con.prepareStatement(sql);

				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getCtnt());
				ps.setInt(3, vo.getIboard());
				
				System.out.println(ps.toString());
				return ps.executeUpdate(); // 완성된 문장 실행 (insert, update, delete) 영향을 미친 레코드 수
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(con, ps);
			}
			return 0;
		}
	
	// Delete 삭제 부분
	public static int delBoard(BoardVO3 param) {	// BoardVO3 객체 안에 set 설정을 해놓았음
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM t_board WHERE iboard = ? "; 		// Delete 쿼리문 작성

		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);

			ps.setInt(1, param.getIboard());	// iboard를 get방식으로 불러옴
			
			System.out.println(ps.toString());
			return ps.executeUpdate(); // 완성된 문장 실행 (insert, update, delete) 영향을 미친 레코드 수
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
		return 0;
	}
	
}
