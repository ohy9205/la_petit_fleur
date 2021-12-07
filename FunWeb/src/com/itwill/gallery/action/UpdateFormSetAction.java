package com.itwill.gallery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.gallery.GalleryDAO;
import com.itwillbs.gallery.GalleryDTO;

public class UpdateFormSetAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : UpdateForm - excute() 호출 ");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
			
		GalleryDAO gdao = new GalleryDAO();
		GalleryDTO gdto = gdao.getBoard(num);
		
		request.setAttribute("gdto", gdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./gallery/gWriteUpdateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
