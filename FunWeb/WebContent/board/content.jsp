<%@page import="com.itwillbs.board.BoardDTO"%>
<%@page import="com.itwillbs.board.BoardDAO"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Image"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>writeForm</title>
		<link href="/FunWeb/css/default.css?v=5" rel="stylesheet" type="text/css">
		<link href="/FunWeb/css/subpage.css?v=5" rel="stylesheet" type="text/css">
	
	</head>
	<body>
		<div id="wrap">
			<!-- 헤더들어가는 곳 -->
			<jsp:include page="../inc/top.jsp"></jsp:include>
			<!-- 헤더들어가는 곳 -->
	
			<!-- 본문들어가는 곳 -->
			<!-- 메인이미지 -->
			<!-- 메인이미지 -->
	
			<!-- 게시판 -->
			<article>
				<div class="write-wrap">
				<h1>NOTICE</h1>
				<div class="sub_img_center"></div>
				<h2 class="sub-tit">${bdto.subject }</h2>
					<table class="write content">
						<tr>
							<td class="label"><label for="num">글번호</label></td>
							<td> ${bdto.num } </td>
						</tr>
						<tr>
							<td class="label"><label for="readcount">조회수</label></td>
							<td> ${bdto.readcount } </td>
						</tr>
						<tr>
							<td class="label"><label for="name">작성자</label></td>
							<td> ${bdto.name } </td>
						</tr>
						<tr>
							<td class="label"><label for="subject">제목</label></td>
							<td> ${bdto.subject } </td>
						</tr>
						<tr>
							<td class="label"><label for="date">작성일</label></td>
							<td> ${bdto.date } </td>
						</tr>
						<tr>
							<td class="label"><label for="content">내용</label></td>
							<td width='500' height='150' class="t-area">
								<br>${bdto.content }
							</td>
						</tr>
						<tr>
							<td class="label"><label for="file">첨부파일</label></td>
							<td>
<%-- 									<a href="./board/fileDownload.jsp?fileName=${bdto.file }">${bdto.file }</a> --%>
								<a href="./attachFile/${bdto.file}" download> ${bdto.file } </a>
							</td>
						</tr>
					</table>
	
					<div id="btnList" >		
						<c:if test="${bdto.name eq sessionScope.id}">
							<a href="./UpdateForm.bo?num=${bdto.num }&pageNum=${param.pageNum}" class="btn btn-a">수정</a>
		
							<a href="./Delete.bo?num=${bdto.num }&pageNum=${param.pageNum}" class="btn btn-a" 
								onclick="window.open(this.href, '', 'width=400, height=430'); return false;">삭제</a>
						</c:if>
						<c:if test="${not empty sessionScope.id && bdto.re_lev eq 0 }">
							<a href="./ReWrite.bo?num=${bdto.num }&pageNum=${param.pageNum}&re_ref=${bdto.re_ref }&re_lev=${bdto.re_lev }&re_seq=${bdto.re_seq}" 
									class="btn btn-a">답글</a>
						</c:if>
							<a href="./Notice.bo?pageNum=${param.pageNum }" class="btn btn-a listBtn">목록</a>
					</div>
					</div> <!-- write-wrap -->
			</article>
			<!-- 게시판 -->
			<!-- 본문들어가는 곳 -->
			
			<!-- 푸터들어가는 곳 -->
			<jsp:include page="../inc/bottom.jsp" />
			<!-- 푸터들어가는 곳 -->
		</div>
	</body>
</html>