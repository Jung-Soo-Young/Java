package com.koreait.board5.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.board5.MyUtils;

@WebServlet("/user/userLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MyUtils.openJSP("user/userLogin", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		
		UserVO vo = new UserVO();				// 객체 선언후 uid 저장
		vo.setUid(uid);
		
		UserVO result = UserDAO.selUser(vo);	// selUser메소드에서 정보를 가져온다.
		if(result == null) {
			// 아이디 없음
			request.setAttribute("errMsg", "아이디를 확인해 주세요.");
		} else if(BCrypt.checkpw(upw, result.getUpw())) {
			// 아이디 있음 & 비밀번호 체크
			result.setUpw(null);
			
			HttpSession hs = request.getSession();	// session에 담는 것은 대부분 로그인 때문 (http : 통신 후 끊음)
			hs.setAttribute("loginUser", result);	// 정보를 저장해 둠 -> 끊기면 정보가 사라짐 (loginUser에 저장된 정보가 있는가?)
			
			response.sendRedirect("/board/boardList");	// boardList로 이동
			return;
			
		} else {
			// 비밀번호 틀림
			request.setAttribute("errMsg", "비밀번호를 확인해 주세요.");
		}
		doGet(request, response);			// doGet으로 정보 전달
	}

}
