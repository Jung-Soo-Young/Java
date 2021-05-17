package com.koreait.board5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board5.user.UserVO;


public class MyUtils {
	public static UserVO getLoginUser(HttpServletRequest req) {
		if(req == null) { return null; }	// 에러 발생 가능성 0
		HttpSession hs = req.getSession();	// 로그인 된 회원의 정보
		return (UserVO) hs.getAttribute("loginUser");
	}
	
	public static int getLoginUserPk(HttpServletRequest req) {	// UserPk 값만 적용되는 메소드
//		UserVO loginUser = MyUtils.getLoginUser(req);	UserVO의 loginUser 선언
//		int pk = loginUser.getIuser();	pk값만 받아오는 것
		
		return getLoginUser(req).getIuser();
	}
	
	public static int getParamInt(String key, HttpServletRequest req) {	// getParamInt = request를 통해 문자를 정수로 변환하는 메소드
		String val = req.getParameter(key);
		int intVal = MyUtils.parseStringToInt(val);
		return intVal;
	}
	
	public static int parseStringToInt(String val) { // parseStringToInt = 문자를 정수로 변환하는 메소드
		try {
			int result = Integer.parseInt(val);
			return result;
		} catch(Exception e) {
			return 0;
		}
	}
	
	public static void openJSP(String fileNm, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// openJSP 메소드 (파일 이동)
		String jsp = "/WEB-INF/view/" + fileNm + ".jsp";
		req.getRequestDispatcher(jsp).forward(req, res);
	}
	
}
