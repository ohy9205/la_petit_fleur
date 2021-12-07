package com.itwill.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.board.action.MainBoardListAction;

//@WebServlet(" /*.me ")
public class BoardFrontController extends HttpServlet {
       

	
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
		if(command.equals("/MainBoard.bo")) {
			System.out.println(" C : /MainBoard.bo 주소 호출 ");
			
			action = new MainBoardListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		} else if(command.equals("/Notice.bo")) {
			System.out.println(" C : /Notice.bo 주소 호출 ");
			
			action = new BoardListAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Write.bo")) {
			System.out.println(" C : /Write.bo 주소 호출 ");
			
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/WriteAction.bo")) {
			System.out.println(" C : /WriteAction.bo 주소 호출 ");
			
			action = new WriteAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Content.bo")) {
			System.out.println(" C : /Content.bo 주소 호출 ");
			
			action = new ContentAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/UpdateForm.bo")) {
			System.out.println(" C : /UpdateForm.bo 주소 호출 ");
			
			action = new UpdateFormAction();
			
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/UpdateSetAction.bo")) {
			System.out.println(" C : /UpdateSetAction.bo 주소 호출 ");
			
			action = new UpdateSetAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Delete.bo")) {
			System.out.println(" C : /Delete.bo 주소 호출 ");
			
			forward = new ActionForward();
			forward.setPath("./board/writeDelete.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/DeleteSet.bo")) {
			System.out.println(" C : /DeleteSet.bo 주소 호출 ");
			
			action = new DeleteSetAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/ReWrite.bo")) {
			System.out.println(" C : ./ReWrite.bo 주소 호출 ");
			
			forward = new ActionForward();
			forward.setPath("./board/ReWriteForm.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/ReWriteSet.bo")) {
			System.out.println(" C : ./ReWrite.bo 주소 호출 ");
			
			action = new ReWriteSetAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Search.bo")) {
			System.out.println(" C : ./Search.bo 주소 호출 ");
			
			action = new SearchAction();
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

			System.out.println(" C  - < 3. 가상주소 이동하기 > - 끝 \n");
		}
		

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
