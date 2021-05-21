package com.koreait.board5.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board5.MyUtils;
import com.koreait.board5.user.UserVO;

@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute("loginUser");	
		// session에서 loginUser로 받았기 때문에 jsp에서 사용
		
		if(loginUser == null) {		// loginUser에 값이 없다면 userLogin으로 이동
			response.sendRedirect("/user/userLogin");
			return;
		}
		
		List<BoardVO> list = BoardDAO.selBoardList();	// list 배열 선언 후 selBoardList() 메소드 정보 저장
		request.setAttribute("list", list);
		
		MyUtils.openJSP("board/boardList", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
