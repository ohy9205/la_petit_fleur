package com.itwill.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.MemberDAO;
import com.itwillbs.member.MemberDTO;

public class UpdateSetAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println( " M : UpdateSetAction - excute() 호출 ");
		
		request.setCharacterEncoding("utf-8");
		
		// 전달받은 정보 저장
		MemberDTO mdto = new MemberDTO();
 		mdto.setPostnum(request.getParameter("postnum"));
 		mdto.setAddress(request.getParameter("address"));
 		mdto.setId(request.getParameter("id"));
 		mdto.setMobile(request.getParameter("mobiel"));
 		mdto.setName(request.getParameter("name"));
 		mdto.setPhone(request.getParameter("phone"));
 		mdto.setPwd(request.getParameter("pwd"));
 		mdto.setEmail(request.getParameter("email"));
		
 		
 		MemberDAO mdao = new MemberDAO();
 		boolean result = mdao.updateMember(mdto);
 		
 		System.out.println(" M : result = "+result);
 		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
 		if(!result) {
 			System.out.println("if문실행");
 			out.print("<script>");
 			out.print("alert('회원정보 수정 실패');");
 			out.print("history.back();");
 			out.print("</script>");
 			out.close();
 			
 
 		} else {
 			System.out.println("else문실행");
			out.print("<script>");
			out.print("alert('회원정보 수정 완료');");
			out.print("location.href='./FunWeb.me';");
			out.print("</script>");
			out.close();
			
 		}
 		
		return null;
	}

}
