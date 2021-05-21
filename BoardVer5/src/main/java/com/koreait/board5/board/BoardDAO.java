package com.koreait.board5.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board5.board.BoardVO;
import com.koreait.board5.DBUtils;

public class BoardDAO {
	public static List<BoardVO> selBoardList(){
		List<BoardVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT A.iboard, A.title, A.iuser, A.regdt, B.unm "
					+ " FROM t_board A "			// 글 번호, 글 제목, 작성일시, 사용자를
					+ " INNER JOIN t_user B "		// t_board 와 t_user 의 iuser 값을 조인하여 불러온다.
					+ " ON A.iuser = B.iuser "
					+ " ORDER BY A.iboard DESC ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int iboard = rs.getInt("iboard");
				String title = rs.getString("title");
				String regdt = rs.getString("regdt");
				String unm = rs.getString("unm");
				
				BoardVO vo = new BoardVO();
				vo.setIboard(iboard);
				vo.setTitle(title);
				vo.setRegdt(regdt);
				vo.setUnm(unm);
				
				list.add(vo);			// 리스트에 vo객체의 값들을 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return list;
	}
	
	public static BoardVO selBoard(BoardVO param) {
		BoardVO result = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT A.iboard, A.title, A.ctnt, A.iuser, A.regdt, B.unm "
				+ " , if(C.iboard IS NULL, 0, 1) AS isFav "		// 좋아요 기능 구현
				+ " FROM t_board A "
				+ " INNER JOIN t_user B "
				+ " ON A.iuser = B.iuser "
				+ " LEFT JOIN t_board_fav C "
				+ " ON A.iboard = C.iboard "
				+ " AND C.iuser = ? "
				+ " WHERE A.iboard = ? ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getIuser());		// 로그인 user PK
			ps.setInt(2, param.getIboard());
			rs = ps.executeQuery();
			
			if(rs.next()) {
			result = new BoardVO();
				
			int iboard = rs.getInt("iboard");
			String title = rs.getString("title");
			String ctnt = rs.getString("ctnt");
			int iuser = rs.getInt("iuser");
			String regdt = rs.getString("regdt");
			String unm = rs.getString("unm");
			int isFav = rs.getInt("isFav");
			
			result.setIboard(iboard);
			result.setTitle(title);
			result.setCtnt(ctnt);
			result.setIuser(iuser);
			result.setRegdt(regdt);
			result.setUnm(unm);
			result.setIsFav(isFav);
			
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
	
	public static BoardVO selBoard(int iboard) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BoardVO data = null;
		
		String sql = " SELECT A.title, A.regdt, A.ctnt, B.unm, A.iuser "	// 제목, 작성일, 내용, 작성자 정보 조회
					+ " FROM t_board A "
					+ " LEFT JOIN t_user B "
					+ " ON A.iuser = B.iuser "
					+ " WHERE iboard = ? ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, iboard);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {			
				String title = rs.getString("title");
				String ctnt = rs.getString("ctnt");
				String regdt = rs.getString("regdt");
				int iuser = rs.getInt("iuser");
				String unm = rs.getString("unm");

				data = new BoardVO();
				data.setIboard(iboard);
				data.setTitle(title);
				data.setCtnt(ctnt);
				data.setRegdt(regdt);
				data.setIuser(iuser);
				data.setUnm(unm);
	
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
	
	public static int insBoard(BoardVO param) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_board "		// 제목, 댓글, 사용자 삽입
					+ " (title, ctnt, iuser) "
					+ " VALUES "
					+ " (?, ?, ?) ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getCtnt());
			ps.setInt(3, param.getIuser());
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBUtils.close(con, ps);
		}
	}
	
	public static int delBoard(BoardVO param) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM t_board WHERE iboard = ? "
					+ " AND iuser = ? ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, param.getIboard());
			ps.setInt(2, param.getIuser());
			
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBUtils.close(con, ps);
		}
	}
	
	public static int updBoard(BoardVO param) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_board "
					+ " SET title = ? "
					+ " , ctnt = ? "
					+ " WHERE iboard = ? ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, param.getIboard());
			return ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBUtils.close(con, ps);
		}
	}
}
