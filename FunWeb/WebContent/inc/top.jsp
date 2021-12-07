<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<div class="header-in">
<%
	// 세션값 가져오기 "id"
	String id = (String)session.getAttribute("id");
	
	// 세션값이 없다 -> 로그아웃중
	if(id == null) {
%>	
		<div id="login" class="unLogin">
			<a href="./Login.me">LOGIN</a> | 
			<a href="./Join.me">SIGN UP</a>
		</div>
<%
		} else {
%>	
	<!-- 	// 세션값이 있다 -> 로그인중 -->
		<ul id="login" class="onLogin"> 
			<li> <strong><%=id %></strong>
			</li>
			<li> <strong>|</strong> </li>
			<li>
				<a href="./Logout.me">LOGOUT</a>
			</li>
			<li> <strong>|</strong> </li>
			<li>
				<a href="./Update.me">UPDATE</a>
			</li> 
		</ul>
<%
		}
%>
			<nav id="top_menu">
				<ul>
					<li><a href="./FunWeb.me">HOME</a></li>
					<li><a href="./Notice.bo">NOTICE</a></li>
					<li><a href="./Gallery.ga">GALLERY</a></li>
				</ul>
			</nav>
	</div>
</header>