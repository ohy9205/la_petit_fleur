package com.itwill.board.action;

public class ActionForward {
	// 컨트롤러에서 이동시킬 페이지 정보를 저장하는 객체
	// 저장저보 - 페이지주소, 이동방법

	private String path; // 페이지주소
	private boolean isRedirect; // 이동방법
		// true: 리다이렉트
		// false: 포워드
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", isRedirect=" + isRedirect + "]";
	}
	
	
	 
}
