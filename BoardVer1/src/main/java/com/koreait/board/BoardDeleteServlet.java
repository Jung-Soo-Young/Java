package com.koreait.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/del")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		System.out.println("no : " + no);
		int intNo = Integer.parseInt(no);
		
		Database.list.remove(intNo);
		
		response.sendRedirect("/list"); // response - 사용자의 요청에 대한 응답
		
	}

}
// del 매핑되는 서블릿 생성
// doPost에서 no 파라미터 받으시고 해당되는 글(BoardVO객체 주소값) 삭제 -> list로 주소 이동
// .add(넣는값(제너릭)), .get(인덱스(정수))