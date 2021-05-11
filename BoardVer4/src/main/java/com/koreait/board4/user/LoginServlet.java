package com.koreait.board4.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board4.MyUtils;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute("loginUser");
		
		if(loginUser != null) {
			response.sendRedirect("/board/list");
			return;
		}
		
		MyUtils.openJSP("user/login", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		
		UserVO vo = new UserVO();
		vo.setUid(uid);
		vo.setUpw(upw);
		
		int result = UserDAO.loginUser(vo);			// UserDAO의 loginUser 메소드를 불러온다.
		System.out.println("result : " + result);
		
		if(result == 1) {							// loginUser 메소드의 값이 1이면 로그인 성공
			HttpSession hs = request.getSession();
			vo.setUpw(null);
			hs.setAttribute("loginUser", vo);		// vo가 가리키는 UserVO 객체는 (iuser, uid, unm 값만 담고 있다.)
			
			response.sendRedirect("/board/list");
			return;
		}
		
		String errMsg = null;
		switch(result) {
		case 0:										// loginUser 메소드의 값이 0이면 에러 메시지
			errMsg = "에러가 발생하였습니다.";
			break;
		case 2:										// loginUser 메소드의 값이 2이면 아이디 확인 메시지
			errMsg = "아이디를 확인해 주세요.";
			break;
		case 3:										// loginUser 메소드의 값이 3이면 비밀번호 확인 메시지
			errMsg = "비밀번호를 확인해 주세요.";
			break;
		}
		request.setAttribute("errMsg", errMsg);
		doGet(request, response);
	}

}
