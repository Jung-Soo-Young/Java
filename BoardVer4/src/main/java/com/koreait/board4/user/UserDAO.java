package com.koreait.board4.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.board4.DBUtils;

public class UserDAO {
	public static int joinUser(UserVO param) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_user (uid, upw, unm, gender) "
					+ " VALUES (?, ?, ?, ?) ";
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, param.getUid());
			ps.setString(2, param.getUpw());
			ps.setString(3, param.getUnm());
			ps.setInt(4, param.getGender());
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBUtils.close(con, ps);
		}
	}
	
	// 로그인 성공:1, 아이디 없음:2, 비밀번호 틀림:3, 에러:0
	public static int loginUser(UserVO param) {		// 로그인 = select문
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_user "
					+ " WHERE uid = ? ";			// uid와 upw 두 개 다 넣을 시 오류 발생 = 무엇이 오류인지 찾지 못 한다. (uid ? / upw ?)
		
		try {
			con = DBUtils.getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, param.getUid());
			rs = ps.executeQuery();
			
			
			if(rs.next()) {		// 아이디가 있는 경우 & 비밀번호 확인
				String dbpw = rs.getString("upw");	
				// if(dbpw.equals(param.getUpw())
				
				if(BCrypt.checkpw(param.getUpw(), dbpw)) {	// BCrypt.checkpw() = 암호화된 비밀번호 체크 = (암호화되지 않은 비밀번호, 암호화 된 비밀번호)
					
					int iuser = rs.getInt("iuser");		// iuser, unm의 값을 담아놓는다.
					String unm = rs.getString("unm");
					
					param.setIuser(iuser);			 
					param.setUnm(unm);
					
					return 1;
				} else {
					return 3;
				}
			} else {			// 아이디가 없는 경우
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
}
