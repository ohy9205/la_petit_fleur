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

public class UpdateSetAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : UpdateSetAction - excute() 호출 ");
		
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("페이지넘 = "+pageNum);
			
		BoardDTO bdto = new BoardDTO();
		
		
		/*파일 업로드 객체*/	
		HttpSession session = request.getSession();
		String uploadPath = session.getServletContext().getRealPath("/attachFile");
				
		int maxSize = 10 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request,
				uploadPath,
				maxSize,
				"utf-8",
				new DefaultFileRenamePolicy()
				);
		
		
		String prevFile = multi.getParameter("prevFile"); // 기존파일이름
		String fileDel = multi.getParameter("fileDel");


		/*기존파일 삭제 후 재업로드*/
//		String filePath = session.getServletContext().getRealPath("/attachFile/"+prevFile); 
		
		if(multi.getFilesystemName("file") != null) { // 새로저장한 파일이 있으면
			
			File file = new File(uploadPath+"/"+prevFile); //경로에 기존 파일이 존재한다면 삭제
			if(file.exists()) {
				file.delete();
			}
				
			bdto.setFile(multi.getFilesystemName("file")); // 그리고 새로운 파일로 업로드
			System.out.println("파일 업데이트 완료");
			
		} else if(fileDel.equals("del")) { // 새로운 파일 없이, 기존 파일을 삭제한다는 키만 전달되면
			
			File file = new File(uploadPath+"/"+prevFile); //경로에 파일이 존재한다면 삭제
			if(file.exists()) {
				file.delete();
			} // 
			
		} else if(prevFile != null){ // 새로운 파일 없이, 기존 파일이 존재하면
			bdto.setFile(prevFile);
		} 
		
		// else : 새로운 파일 없고, 기존파일도 없으면 
		// => bdto.setFile() 안함 -> null저장
		

		bdto.setNum(num);
		bdto.setPwd(multi.getParameter("pwd"));
		bdto.setSubject(multi.getParameter("subject"));
		bdto.setContent(multi.getParameter("content"));
		

		BoardDAO bdao = new BoardDAO();
		int result = bdao.updateBoard(bdto);
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		
		if(result == 1) {
			out.print("alert('글이 수정 되었습니다');");
			
		} else if(result == 0) {
			out.print("alert('비밀번호가 일치하지 않습니다');");
			
		} else {
			out.print("alert('존재하지 않는 글입니다');");
			
		}

		out.print("location.href='./Content.bo?num=");
		out.print(num);
		out.print("&pageNum=");
		out.print(pageNum+"'");
		out.print("</script>");

		out.close();
		
		
		
		return null;
	}

}
