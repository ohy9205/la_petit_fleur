package com.itwill.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.MemberDAO;
import com.itwillbs.member.MemberDTO;

public class UpdateFormAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : UpdateFormAction - excute() 호출 ");
		
		
		String id = (String)request.getSession().getAttribute("id");
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(id == null) {
			out.println("<script>");
			out.println("alert('잘못된 접근. 로그인시에만 가능합니다')");
			out.println("location.href='/Login.me'");
			out.println("</script>");
			out.close();
			return null;
		}
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.getMember(id);
		
		request.setAttribute("mdto", mdto);

		// 페이지 이동정보
		ActionForward forward = new ActionForward();
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
