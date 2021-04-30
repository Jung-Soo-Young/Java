package com.koreait.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mod")
public class BoardModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");		// no = 고유번호
		System.out.println("no : " + no);
		request.setAttribute("data", Database.list.get(Integer.parseInt(no)));	// 고유번호와 일치하는 Database.list 배열을 불러냄
		
		String jsp = "WEB-INF/jsp/mod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 		// 한글 깨짐 방지 인코딩
		String no = request.getParameter("no");		// mod.jsp의 넘버 값, 제목, 내용들 키 값 받아와야 함!
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		System.out.println("no : " + no);
		System.out.println("no : " + title);
		System.out.println("no : " + ctnt);
		
		BoardVO vo = Database.list.get(Integer.parseInt(no));	// 객체 생성 = Database의 배열
		vo.setTitle(title); // 변경된 값으로 제목과 내용을 저장!
		vo.setCtnt(ctnt);
		
		request.setAttribute("data", Database.list.get(Integer.parseInt(no)));
		
		// 지정한 페이지로 이동 (디테일 화면)
		response.sendRedirect("detail?no=" + no);
	}

}
