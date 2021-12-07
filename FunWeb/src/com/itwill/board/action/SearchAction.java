package com.itwill.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.BoardDAO;
import com.itwillbs.board.BoardDTO;

public class SearchAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : SearchAction - excute() 호출 ");
		
		/* 페이징 */
		// 한 페이지당 목록 10개씩 보여주기
		int listCount = 10;
		
		// 페이지 번호
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		
		// 페이지번호를 계산하기위해 정수형으로 변환
		int thisPageNum =  Integer.parseInt(pageNum);
		
		
		int startRow = (thisPageNum-1)*listCount+1;
		int endRow = thisPageNum * listCount;

		BoardDAO bdao = new BoardDAO();
		String search = request.getParameter("search");
		int count = bdao.getSearchCount(search);
		List<BoardDTO> boardList = bdao.searchBoardList(startRow, listCount, search);
		System.out.println(boardList);
		
		
		// 페이지 버튼 다섯개씩 보이게 함
		int pageNumBlock = 5;
	
		int startPage = ((thisPageNum-1)/pageNumBlock)*pageNumBlock+1;
		int endPage = startPage+pageNumBlock-1;
		
		// 실제 페이지번호 개수 = (실제 글 개수/페이지당 보이는 글 수)+(만약 나머지 있으면 +1, 없으면 +0)
		int pageNumCount = (count/listCount)+(count%listCount != 0 ? 1 : 0);
	
		// 실제 페이지수보다 이론적인 계산 페이지보다 크면 
		// -> 마지막 페이지번호를 실제 페이지 끝번호로 바꿈
		if(endPage > pageNumCount) {
			endPage = pageNumCount;
		}
		
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageNumBlock", pageNumBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNumCount", pageNumCount);
		request.setAttribute("search", search);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardSearch.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
