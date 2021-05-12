package com.koreait.board4.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.board4.MyUtils;

@WebServlet("/user/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyUtils.openJSP("user/join", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		String unm = request.getParameter("unm");
		// String gender = request.getParameter("gender");		// gender을 문자로 받아왔지만 int로 선언해 두었기 때문에 타입이 일치하지 않음
		int gender = MyUtils.getParamInt("gender", request);	// IntGender = 정수로 변환해주는 메소드 선언
		
		String hashedUpw = BCrypt.hashpw(upw, BCrypt.gensalt());	// pw 암호화
		System.out.println("hashedUpw : " + hashedUpw);
		
		UserVO vo = new UserVO();
		vo.setUid(uid);
		vo.setUnm(unm);
		vo.setUpw(hashedUpw);
		vo.setGender(gender);
		
		UserDAO.joinUser(vo);				// joinUser 메소드 호출
		response.sendRedirect("login");		// login 페이지로 이동
	}

}
