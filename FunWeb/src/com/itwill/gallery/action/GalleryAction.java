package com.itwill.gallery.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.gallery.GalleryDAO;
import com.itwillbs.gallery.GalleryDTO;

public class GalleryAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : GalleryAction - excute() 호출 ");
		
		GalleryDAO gdao = new GalleryDAO();
		int count = gdao.getBoardCount(); // 글의 총 개수 가져옴
		
/* 페이징 */
		int listCount = 12; // 페이지당 12개씩 노출
		
		// 페이지 번호
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		
		// 페이지번호를 계산하기위해 정수형으로 변환
		int thisPageNum =  Integer.parseInt(pageNum);
		
		
		int startRow = (thisPageNum-1)*listCount+1;
		int endRow = thisPageNum * listCount;
		
		List<GalleryDTO> galleryList = gdao.getBoardList(startRow, listCount);
		
		
/* 페이지 번호 */		
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
		request.setAttribute("galleryList", galleryList);
		request.setAttribute("listCount", listCount);
		request.setAttribute("pageNumBlock", pageNumBlock);
		request.setAttribute("pageNumCount", pageNumCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		
		ActionForward forward = new ActionForward();
		forward.setPath("./gallery/gBoard.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
