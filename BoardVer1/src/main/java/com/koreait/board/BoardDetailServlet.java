package com.koreait.board; // Servlet로 생성하기!! , jsp 

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no"); // 쿼리 스트링 (키 값)을 적어야 함! getParameter
		System.out.println("no : " + no);
		
		request.setAttribute("data", Database.list.get(Integer.parseInt(no))); // BoardVO 객체 주소값
		//TODO - Database의 list에서 키 값을 불러온다. - 키 값이 String 이므로 int 값으로 변환
		String jsp = "WEB-INF/jsp/detail.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
// get방식으로 서버한테 값 전달할때는 쿼리스트링으로 전달해야함.
// post방식으로 서버한테 값 전달할때는 form 태그 사용하여 method를 post로 한 뒤 전달해야 함.
// getParameter = 브라우저에서 넘어옴, 엔터를 이용해서 받음
// Servlet -> jsp로 명령 , jsp -> Servlet 불가능
// setAttribute(키값, value), getAttribute(키값) = Servlet -> jsp로 명령을 보낼 때 사용하는 것
