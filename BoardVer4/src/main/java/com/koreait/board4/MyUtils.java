package com.koreait.board4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyUtils {
	public static int getParamInt(String key, HttpServletRequest req) {	// getParamInt = request를 통해 문자를 정수로 변환하는 메소드
		String val = req.getParameter(key);
		int intVal = MyUtils.parseStringToInt(val);
		return intVal;
	}
	
	public static int parseStringToInt(String val) { // parseStringToInt = 문자를 정수로 변환하는 메소드
		try {
			int result = Integer.parseInt(val);
			return result;
		} catch(Exception e) {
			return 0;
		}
	}
	
	public static void openJSP(String fileNm, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String jsp = "/WEB-INF/view/" + fileNm + ".jsp";
		req.getRequestDispatcher(jsp).forward(req, res);
	}
	
}
