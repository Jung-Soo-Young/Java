package com.koreait.board5.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.board5.MyUtils;


@WebServlet("/user/userJoin")
public class UserJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyUtils.openJSP("user/userJoin", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		String unm = request.getParameter("unm");
		
		int gender = MyUtils.getParamInt("gender", request);	// 문자를 정수형으로 변환
		
		String hashedUpw = BCrypt.hashpw(upw, BCrypt.gensalt());	// 비밀번호 암호화 과정
		System.out.println("hashedUpw : " + hashedUpw);
		
		UserVO vo = new UserVO();		// UserVO 객체 선언
		vo.setUid(uid);
		vo.setUpw(hashedUpw);
		vo.setUnm(unm);
		vo.setGender(gender);
		
		UserDAO.joinUser(vo);			// vo 정보들 -> joinUser 메소드
		
		response.sendRedirect("userLogin");
	}

}
