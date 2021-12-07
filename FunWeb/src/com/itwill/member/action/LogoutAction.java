package com.itwill.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션초기화
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.println("alert('로그아웃 되었습니다')");
		out.println("location.href='./FunWeb.me'");
		out.print("</script>");
		
		return null;
	}

}
