package com.koreait.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/write")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsp = "/WEB-INF/jsp/write.jsp";
		request.getRequestDispatcher(jsp).forward(request, response); // 글쓰기 화면 입력란
	} // get방식으로 주소 이동

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //POST방식 한글 깨짐 현상 방지
		String title = request.getParameter("title"); // 요청
		String ctnt = request.getParameter("ctnt");
		
		BoardVO vo = new BoardVO(); // 값을 받기 위해서 새로운 객체 생성
		vo.setTitle(title); // 새 객체에 제목과 댓글을 받아옴
		vo.setCtnt(ctnt);
		
		Database.list.add(vo); // Database.list에 vo값 저장
		
		response.sendRedirect("/list"); // 응답 : Get방식으로 주소이동 (처리) , list 대신에 detail도 가능
	}

}
