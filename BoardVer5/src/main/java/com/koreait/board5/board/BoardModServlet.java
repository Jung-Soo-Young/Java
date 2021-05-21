package com.koreait.board5.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board5.board.BoardDAO;
import com.koreait.board5.board.BoardVO;
import com.koreait.board5.MyUtils;

@WebServlet("/board/boardMod")
public class BoardModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int iboard = MyUtils.getParamInt("iboard", request);
		
		BoardVO data = BoardDAO.selBoard(iboard);
		request.setAttribute("data", data);
		
		MyUtils.openJSP("board/boardMod", request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		int iboard = MyUtils.getParamInt("iboard", request);
		int iuser = MyUtils.getLoginUserPk(request);
		
		BoardVO param = new BoardVO();
		param.setIboard(iboard);
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setIuser(iuser);
		
		BoardDAO.updBoard(param);
		
		response.sendRedirect("/detail?iboard=" + iboard);
	}

}
