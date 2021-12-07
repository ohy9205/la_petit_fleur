<%@page import="com.itwillbs.gallery.GalleryDTO"%>
<%@page import="com.itwillbs.gallery.GalleryDAO"%>
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
				<div class="write-wrap gal-write-wrap">
					<h1>GALLERY</h1>
					<div class="sub_img_center"></div>
					<table class="write content">
						<h2 class="sub-tit">${gdto.subject }</h2>
						<tr>
							<td class="label"><label for="num">글번호</label></td>
							<td> ${gdto.num } </td>
						</tr>
						<tr>
							<td class="label"><label for="readcount">조회수</label></td>
							<td> ${gdto.readcount } </td>
						</tr>
						<tr>
							<td class="label"><label for="name">작성자</label></td>
							<td> ${gdto.name } </td>
						</tr>
						<tr>
							<td class="label"><label for="subject">제목</label></td>
							<td> ${gdto.subject } </td>
						</tr>
						<tr>
							<td class="label"><label for="date">작성일</label></td>
							<td> ${gdto.date } </td>
						</tr>
						<tr>
							<td class="label"><label for="file">첨부파일</label></td>
							<td>
<%-- 							<a href="fileDownload.jsp?fileName=<%=bvo.getFile() %>"><%=bvo.getFile() %></a> --%>
							<a href="/FunWeb/attachFile/${gdto.file }" download> ${gdto.file }</a>

							</td>
						</tr>
						<tr>
							<td class="label"><label for="content">내용</label></td>
							<td width='500' height='150' class="t-area g-img">

								<img src="/FunWeb/attachFile/${gdto.file }" >
								<br><br>
								${gdto.content }
							</td>
						</tr>
					</table>
	
					<div id="btnList" >		

					<c:if test="${gdto.name eq sessionScope.id }">
						<a href="./Update.ga?num=${gdto.num }&pageNum=${param.pageNum }" class="btn btn-a">수정</a>
			
						<a href="./Delete.ga?num=${gdto.num }&pageNum=${param.pageNum }" class="btn btn-a" 
							onclick="window.open(this.href, '', 'width=400, height=430'); return false;">삭제</a>
					</c:if>
						<a href="./Gallery.ga?pageNum=${param.pageNum }" class="btn btn-a">목록</a>
						
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