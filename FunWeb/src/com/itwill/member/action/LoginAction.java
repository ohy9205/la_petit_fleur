package com.itwill.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.MemberDAO;
import com.itwillbs.member.MemberDTO;

public class LoginAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// MemberVO에 정보 저장
		MemberDTO mdto = new MemberDTO();
		mdto.setId(id);
		mdto.setPwd(pwd);
		
		// db의 id,pwd정보와 입력한 정보가 일치하는 지비교하는 작업 수행
		MemberDAO mdao = new MemberDAO();
		mdto = mdao.userCheck(mdto); 
		
		// 일치하는 데이터가 있으면 MemberDAO에서 해당하는 정보의 모든 컬럼을 반환 
		// => session값 생성 "id" -> main.me로 이동
		// 일치하는 데이터가 없으면 
		// => "id나 비밀번호가 맞지 않습니다" 출력 -> 뒤로가기
		if(mdto == null) { // 반환값이 있으면(로그인에 성공하면)
		
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('id나 비밀번호가 올바르지 않습니다')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		// id정보를 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		// 이동정보 설정
		ActionForward forward = new ActionForward();
		forward.setPath("./FunWeb.me");
		forward.setRedirect(true);
		
		return forward;
		
		
	}

}
