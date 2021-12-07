package com.itwill.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.MemberDAO;
import com.itwillbs.member.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberJoinAction_execute() 호출");
		
		request.setCharacterEncoding("utf-8");
		
		MemberDTO mdto = new MemberDTO();
		mdto.setId(request.getParameter("id"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setName(request.getParameter("name"));
		mdto.setEmail(request.getParameter("email"));
//		mdto.setJoindate(request.getParameter("joindate")); -> sql쿼리구문으로 추가
		mdto.setPostnum(request.getParameter("postnum"));
		mdto.setAddress(request.getParameter("address"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setMobile(request.getParameter("mobile"));

		System.out.println(" M : "+mdto);
		
		

		MemberDAO mdao = new MemberDAO();
		boolean result = mdao.insertMember(mdto);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		
		if(!result) {
			out.println("alert('가입에 실패했습니다');");
			out.println("history.back();");	
			
		} else {
			out.print("alert('가입되었습니다');");
			out.print("location.href='./Login.me'");
		}
		out.println("</script>");
		out.close();
		
		return null;
	}

}
