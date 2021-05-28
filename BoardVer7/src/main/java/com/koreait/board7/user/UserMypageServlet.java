package com.koreait.board7.user;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board7.MyFileUtils;
import com.koreait.board7.MyUtils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/user/mypage")
public class UserMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserEntity loginUser = MyUtils.getLoginUser(request);
		request.setAttribute("user", UserDAO.selUser(loginUser));
		
		MyUtils.openJSP("마이페이지","user/userMypage", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String uploadPath = request.getRealPath("/res/img/temp");
		String uploadPath = request.getServletContext().getRealPath("/res/img/");
		int maxFileSize = 10_485_760; // 10 * 1024 * 1024 (10mb)
		System.out.println("uploadPath : " + uploadPath);
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath + "/temp", maxFileSize, 
				"UTF-8", new DefaultFileRenamePolicy());
		
		String fileNm = multi.getFilesystemName("profileImg");	// userMypage.jsp의 type = file 의 name
		System.out.println("fileNm: " + fileNm);
		
		if(fileNm == null) {
			doGet(request, response);
		}
		
		UserEntity loginUser = MyUtils.getLoginUser(request);
		int loginUserPk = loginUser.getIuser();
		
		String targetFolder = uploadPath + "/user/" + loginUserPk;
		// folder.delete(); 	// delete() 폴더 삭제 - 폴더 안에 파일이 들어있을 경우 삭제 불가능
		MyFileUtils.delFolder(targetFolder);	// 폴더 삭제 메소드 - 안에 파일이 있어도 삭제 후 재생성
		
		File folder = new File(targetFolder);
		folder.mkdirs();	// exists()가 존재하기 때문에 두번 적어줄 필요가 없다.
		
		int lastDotIdx = fileNm.lastIndexOf(".");
		String ext = fileNm.substring(lastDotIdx); // 확장자 구함
		// String ext1 = fileNm.substring(fileNm.lastIndexOf(".") + 1);
		
		String newFileNm = UUID.randomUUID().toString();
		
		File imgFile = new File(uploadPath + "/temp/" + fileNm);
		imgFile.renameTo(new File(targetFolder + "/" + newFileNm));
		
		UserEntity param = new UserEntity();
		param.setIuser(loginUserPk);
		param.setProfileImg(newFileNm);
		
		UserDAO.updUser(param);
		loginUser.setProfileImg(newFileNm);
		doGet(request, response);
	}

}
