<%@page import="com.itwillbs.member.MemberDAO"%>
<%@page import="com.itwillbs.member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");

	MemberDAO mdao = new MemberDAO();
	MemberDTO mdto = mdao.getMember(id);
	
		if(mdto!=null) {
			%>이미 존재하는 아이디입니다 <%
		} else {
			%>사용 가능한 아이디입니다<%
	}
%> 