package com.koreait.board5.fav;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board5.MyUtils;

@WebServlet("/board/fav")
public class BoardFavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int iboard = MyUtils.getParamInt("iboard", request);
		int iuser = MyUtils.getLoginUserPk(request);
		int fav = MyUtils.getParamInt("fav", request);
		
		switch(fav) {
		case 0:			// 좋아요 취소 (delete)
			FavDAO.delFav(iboard, iuser);
			break;
		case 1:			// 좋아요 처리 (insert)
			FavDAO.insFav(iboard, iuser);
			break;
		}
		response.sendRedirect("boardDetail?iboard=" + iboard);
	}

}
