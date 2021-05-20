package com.koreait.board5.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board5.MyUtils;
import com.koreait.board5.cmt.CmtDAO;
import com.koreait.board5.cmt.CmtVO;

@WebServlet("/board/boardDetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int iboard = MyUtils.getParamInt("iboard", request);
		int iuser = MyUtils.getLoginUserPk(request);
		BoardVO param = new BoardVO();
		param.setIboard(iboard);
		param.setIuser(iuser);
		request.setAttribute("data", BoardDAO.selBoard(param));
		
		CmtVO param1 = new CmtVO();
		param1.setIboard(iboard);
		param1.setIuser(iuser);		// 로그인 user PK
		request.setAttribute("cmtList", CmtDAO.selCmtList(param1));
		
		MyUtils.openJSP("board/boardDetail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
