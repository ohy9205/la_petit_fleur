package com.itwill.board.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.BoardDAO;
import com.itwillbs.board.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReWriteSetAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : ReWriteSet - excute() 호출 ");
		
		HttpSession session = request.getSession();
		String uploadPath = session.getServletContext().getRealPath("/attachFile");
		
		int maxSize = 10*1024*1024;
		
		
		MultipartRequest multi = new MultipartRequest(
			request, // 요청
			uploadPath, // 업로드된 파일을 저장할 위치
			maxSize, //업로드 가능한 파일의 최대크기
			"utf-8", // 파일의 인코딩 방식
			new DefaultFileRenamePolicy() // 파일이름 중복처리
		);
		
		BoardDTO bdto = new BoardDTO();
		bdto.setNum(Integer.parseInt(multi.getParameter("num")));
		bdto.setName(multi.getParameter("name"));
		bdto.setPwd(multi.getParameter("pwd"));
		bdto.setSubject(multi.getParameter("subject"));
		bdto.setContent(multi.getParameter("content"));
		bdto.setFile(multi.getFilesystemName("file")); // 중복처리된 파일 이름
		bdto.setIp(request.getRemoteAddr());
		bdto.setRe_ref(Integer.parseInt(multi.getParameter("re_ref")));
		bdto.setRe_lev(Integer.parseInt(multi.getParameter("re_lev")));
		bdto.setRe_seq(Integer.parseInt(multi.getParameter("re_seq")));
		String originFileName = multi.getOriginalFileName("file");
		

		BoardDAO bdao = new BoardDAO();
		bdao.reInsertBoard(bdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Notice.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
