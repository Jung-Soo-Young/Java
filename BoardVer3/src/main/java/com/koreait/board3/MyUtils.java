package com.koreait.board3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyUtils {
	public static int getParamInt(String key, HttpServletRequest req) {	// iboard 값 정수로 받아오기 위한 메소드
		String strVal = req.getParameter(key);
		int intVal = parseStringToInt(strVal);
		return intVal;
		
		// return parseStringToInt(req.getParameter(key));
	}
	
	public static int parseStringToInt(String srtNum) {		// 문자를 정수로 변환하기 위한 메소드
		try {
			return Integer.parseInt(srtNum);
		} catch(Exception e) {								// 문자를 정수로 변환하지 못 할 경우 예외처리 = 0
			return 0;
		} 
	}
	
	// Java = 클래스 단위로 객체화 가능, 메소드 혼자서 객체화 불가능
	public static void openJSP(String filem, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
									// 지역변수 request, response 필요!		throws Exception 일 경우 try/catch 필요!
		String jsp = "WEB-INF/view/" + filem + ".jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
		
		/*
		   RequestDispatcher rd = request.getRequestDispatcher(jsp);
		   rd.forward(request, response);
		 */

	}
}
