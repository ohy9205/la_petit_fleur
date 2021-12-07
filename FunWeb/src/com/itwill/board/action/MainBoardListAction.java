package com.itwill.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.BoardDAO;
import com.itwillbs.board.BoardDTO;

public class MainBoardListAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDAO bdao = new BoardDAO();
		List<BoardDTO> boardList = bdao.getBoardList(1, 5);
		
		request.setAttribute("boardList", boardList);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./main/main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
