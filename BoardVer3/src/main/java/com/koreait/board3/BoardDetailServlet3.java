package com.koreait.board3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail3")
public class BoardDetailServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String iboard = request.getParameter("iboard");
		// int intIboard = Integer.parseInt(iboard);
		
		int iboard = MyUtils.getParamInt("iboard", request);	// 문자 iboard의 값을 정수로 변환하는 메소드
		
		BoardVO3 data = BoardDAO.selBoard(iboard);	// BoardVO3의 data 객체에 title, ctnt, regdt 값들을 담을 것이다.
		request.setAttribute("data", data);
		
		MyUtils.openJSP("detail3", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
