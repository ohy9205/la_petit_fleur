package com.itwill.member.action;


import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.board.action.MainBoardListAction;

//@WebServlet(" /*.me ")
public class MemberFrontController extends HttpServlet {
       

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doProcess() 호출!! \n\n ");
		

/* ============== 1. 가상주소 가져오기 시작===============*/			
		System.out.println(" C  - < 1. 가상주소 가져오기 > - 시작");
		
		
	
		// uri 주소 구함 ( URI: '프로젝트/가상주소' 만 ) 
		String requestURI = request.getRequestURI(); // : /FunWeb/*.me
		System.out.println(" C - URI : "+requestURI);
	
		
		// 현재 프로젝트 경로 가져옴
		String ctxPath = request.getContextPath(); // : /FunWeb
		System.out.println(" C - ctxPath : "+ctxPath);
		
		
		// 매핑이름만 가져오기 -> 가상주소만 떼어냄 (uri주소 - 현재프로젝트정보)
		String command = requestURI.substring(ctxPath.length()); // : /*.me
				// ctxPath길이 번째의 인덱스부터 끝까지 불러옴 -> uri에서 프로젝트정보
		System.out.println(" C - command : "+command); // 가상주소 정보
		
		
		
		System.out.println(" C  - < 1. 가상주소 가져오기 > - 끝 \n");
/* ============== 1. 가상주소 가져오기 끝 ===============*/				
		
		
		
		
/* ============== 2. 가상주소 매핑 ===============*/			
		System.out.println(" C  - < 2. 가상주소 매핑 > - 시작");
	// 입력한 가상주소마다 다른 동작, 다른 연결을 하도록 만들자
		
		
		ActionForward forward = null; // 이동정보를 저장할 변수
		Action action = null; // 동작을 수행할 변수 -> 내부 함수가 ActionForward를 반환하므로 꼭 forward변수에 대입해야함
		// * 모든 모델은 Action을 구현해야함 *
		
		
	// 가상주소 매핑위한 if문
		if(command.equals("/FunWeb.me")) { // 메인페이지
			System.out.println( " C : /FunWeb.me 주소 호출 ");
			// DB사용 - 보드 정보 가져옴
			// 페이지이동 - main.jsp
			
			forward = new ActionForward();
			forward.setPath("MainBoard.bo");
			forward.setRedirect(false);

			System.out.println(" C : "+forward);
			
		} else if(command.equals("/Join.me")) {
			System.out.println( " C : /Join.me 주소 호출 ");
			// db사용 X, 페이지이동 - join.jsp
			
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);

			System.out.println(" C : "+forward);
			
		} else if(command.equals("/JoinAction.me")) { // 입력정보 저장하는 모델
			System.out.println( " C : /JoinAction.me 주소 호출 " );
			// db에 정보 저장, 저장 후 로그인페이지로 이동
			
			action = new JoinAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(" C : "+forward);
			
		} else if(command.equals("/Login.me")) {
			System.out.println(" C : /Login.me 주소 호출");
			// 페이지만 이동
			
			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);

			System.out.println(" C : "+forward);
			
		} else if(command.equals("/LoginAction.me")) {
			System.out.println(" C : /LoginAction.me 주소 호출 ");
			
			action = new LoginAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Logout.me")) {
			System.out.println(" C : /Logout.me 주소 호출 ");
			
			forward = new ActionForward();
			forward.setPath("./LogoutAction.me");
			forward.setRedirect(true);
		
			
		} else if(command.equals("/LogoutAction.me")) {
			System.out.println(" C : /LogoutAction.me 주소 호출 ");
			
			action = new LogoutAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if(command.equals("/Update.me")) {
			System.out.println(" C : /Update.me 주소 호출 ");
			
			action = new UpdateFormAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/UpdateSetAction.me")) {
			
			System.out.println( " C : /UpdateSetAction.me 주소 호출 ");
			
			action = new UpdateSetAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
		
		
		System.out.println(" C  - < 2. 가상주소 매핑 > - 끝 \n");
/* ============== 2. 가상주소 매핑 끝 ===============*/					
		
		
		

/* ============== 3. 가상주소로 이동하기 시작 ===============*/			
		if(forward != null) { // 페이지 이동 정보가 있으면
			System.out.println(" C - < 3. 가상주소 이동하기 > - 시작 ");
			
			if(forward.isRedirect()) { // true- 리다이렉트, false- 포워드
				System.out.println(" C : sendRedirect방식 | "+forward.getPath()+" 페이지로 이동");
				response.sendRedirect(forward.getPath()); 
			} else {
				System.out.println(" C : forward방식 | "+forward.getPath()+" 페이지로 이동");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
			
		}
		

		System.out.println(" C  - < 3. 가상주소 이동하기 > - 끝 \n");
/* ============== 3. 가상주소로 이동하기 끝 ===============*/	

	} // doProcess() 끝
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("------------------------");
		System.out.println(" C : doGet() 호출 !!! ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("------------------------");
		System.out.println(" C : doPost() 호출 !!! ");
		
		doProcess(request, response);
	}

}
