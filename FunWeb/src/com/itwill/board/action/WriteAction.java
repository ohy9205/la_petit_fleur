package com.itwill.board.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.BoardDAO;
import com.itwillbs.board.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : WriteAction - excute() 호출 ");
		
		// 글 저장 역할
		
		request.setCharacterEncoding("utf-8");
		
		// 첨부한 파일이 저장되는 경로(가상경로) -> 서버폴더에 저장됨
		// request.getRealPath("폴더"): 폴더의 서버 절대경로를 반환
		HttpSession session = request.getSession();
		String uploadPath = session.getServletContext().getRealPath("/attachFile");
		// 첨부파일의 최대 크기 제한(10MB)/
		int maxSize = 10*1024*1024;
		
		
		MultipartRequest multi = new MultipartRequest(
			request, // 요청
			uploadPath, // 업로드된 파일을 저장할 위치
			maxSize, //업로드 가능한 파일의 최대크기
			"utf-8", // 파일의 인코딩 방식
			new DefaultFileRenamePolicy() // 파일이름 중복처리
			);
			

		
		BoardDTO bdto = new BoardDTO();
		bdto.setName(multi.getParameter("name"));
		bdto.setPwd(multi.getParameter("pwd"));
		bdto.setSubject(multi.getParameter("subject"));
		bdto.setContent(multi.getParameter("content"));
		bdto.setFile(multi.getFilesystemName("file")); // 중복처리된 파일 이름
		bdto.setIp(request.getRemoteAddr());
		String originFileName = multi.getOriginalFileName("file");
		
		System.out.println("writePro: "+bdto);
		
		BoardDAO bdao = new BoardDAO();
		boolean result = bdao.insertBoard(bdto);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if(!result) {
			

			File f = new File(uploadPath+"/"+multi.getFilesystemName("file")); // 파일 객체생성
			if (f.exists())
				f.delete(); // 파일이 존재하면 파일을 삭제한다.
			
			out.print("<script>");
			out.print("alert('글쓰기에 실패했습니다');");
			out.print("history.back()");
			out.print("</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Notice.bo");
		forward.setRedirect(true);
		
		
		return forward;
	}

}
