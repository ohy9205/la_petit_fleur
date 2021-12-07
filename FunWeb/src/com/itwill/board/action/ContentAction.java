package com.itwill.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.BoardDAO;
import com.itwillbs.board.BoardDTO;

public class ContentAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : ContentAction - excute() 호출 ");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		bdao.updateReadCount(num);
		BoardDTO bdto = bdao.getBoard(num);
		
		request.setAttribute("bdto", bdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
