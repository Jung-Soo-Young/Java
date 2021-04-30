package com.koreait.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list") // URL이 중요 = list.jsp
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get방식으로 요청 작업을 받을 거 = 화면 띄우는거 (view.url)
		// post방식은 처리 - 데이터 입력, 수정, 삭제 등

		request.setAttribute("data", Database.list); // request = 사용자에 의한 요청 (값을 전달)
		String jsp = "/WEB-INF/jsp/list.jsp"; // get방식으로 jsp를 연다.
		request.getRequestDispatcher(jsp).forward(request, response); // 고정적인 코딩 내용
	

		// 클라이언트 (브라우저) 값이 서버쪽으로 넘어왔음....		
		
		// <값 빼내는 방법>
		// request.getParameter(키 값);
		
		// Servlet -> jsp로 값 전달하는 방법
		// request.setAttribute(키 값, 밸류값); - 하나의 공간에는 한 개만!
		// 키 값인 속성의 값을 value 로 지정한다.
		
		// request.getAttribute(키 값);
		// 키 값인 속성의 값을 구한다.
		
	}
	
}
