package com.itwill.board.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.BoardDAO;
import com.itwillbs.board.BoardDTO;

public class DeleteSetAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : DeleteSet - excute() 호출 ");
		
		// 요청값 저장
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		

		// 비번 일치 검사
		BoardDAO bdao = new BoardDAO();
		BoardDTO bdto = bdao.getBoard(num);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		if(!request.getParameter("pwd").equals(bdto.getPwd())) {
			
			out.print("alert('비밀번호가 일치하지 않습니다');");
			out.print("window.close();");
		
		} else {
			
			int result = bdao.deleteBoard(bdto);
			
			if(result == 1) { // 글삭제 성공
				if(bdto.getFile()!=null) {
//					bdto.setFile(file);
	
					// 삭제할 파일의 경로; //지울 파일명
					HttpSession session = request.getSession();
					String filePath = session.getServletContext().getRealPath("/attachFile/");
					
					File f = new File(filePath+"/"+bdto.getFile()); // 파일 객체생성
					if (f.exists())
						f.delete(); // 파일이 존재하면 파일을 삭제한다.
				}
				

			
				out.print("alert('삭제되었습니다');");
				out.print("window.close();");
				out.print("opener.location.href='./Notice.bo?pageNum=");
				out.print(pageNum+"';");
				
				
				
			}else if(result == 0) {

				out.print("alert('비밀번호가 일치하지 않습니다');");
				out.print("window.close();");
				
			}  else {

				out.print("alert('비밀번호가 일치하지 않습니다');");
				out.print("window.close();");
			}
		}

		out.print("</script>");
		out.close();
		
		return null;
	}

}
