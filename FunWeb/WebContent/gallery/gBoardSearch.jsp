<%@page import="com.itwillbs.gallery.GalleryDTO"%>
<%@page import="com.itwillbs.gallery.GalleryDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>board</title>
		<link href="../css/default.css?V=2" rel="stylesheet" type="text/css">
		<link href="../css/subpage.css?V=2" rel="stylesheet" type="text/css">
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
				<div class="article-gallery">
						<h1>GALLERY</h1>			
						<div class="sub_img_center"></div>
					<ul class="gal-wrap">

					<c:forEach var="gallery" items="${galleryList }" >
						<li onclick="location.href='./Content.ga?num=${gallery.num }&pageNum=${pageNum }'">
							<div class="img-wrap">
								<img src="../attachFile/${gallery.file }" alt="사용자가 업로드한" >
							</div>
							<div class="text-wrap">
								<h3 class="gal-sub">${gallery.subject }</h3>
								<p class="gal-count">${gallery.readcount }</p>
								<p class="gal-name">${gallery.name }</p>
								<p class="gal-date">${gallery.date }</p>
							</div>
						</li>
					</c:forEach>

					</ul>
	
					<div id="listBottom">
						<div class="writeBtnBox">
<!-- 							<button type="button" onclick="location.href='./GalleryWrite.bo'" id="writeBtn" class="hide btn">글쓰기</button> -->
							
						<c:if test="${not empty sessionScope }">						
							<a href="./Write.ga" id="writeBtn" class="btn">글쓰기</a>
						</c:if>
						</div>
						<form action="./GallerySearch.ga" name="search" id="table_search">
							<input type="text" name="search" class="input_box" placeholder="제목+내용"> 
							<button type="submit" class="btn" >검색</button>
						</form>
					</div>

					
		<!-- 페이지번호 목록 -->

					<div id="page_control">
			
					<c:if test="${startPage > pageNumBlock }">
						<a href="./Gallery.ga?pageNum=${startPage-1 }">Prev</a>
					</c:if>
					
					<c:forEach begin="${startPage }" end="${endPage }" step="1" var="i">
						<a href="./Gallery.ga?pageNum=${i }"> ${i } </a>
					</c:forEach>
				
					<c:if test="${endPage < pageNumCount }">
						<a href="./Gallery.ga?pageNum=${startPage + pageNumBlock }">Next</a>
					</c:if>
				
					</div>
				</div> <!-- article-notice -->
			</article>
				<!-- 게시판 -->
				<!-- 본문들어가는 곳 -->
				<!-- 푸터들어가는 곳 -->
			<jsp:include page="../inc/bottom.jsp" />
				<!-- 푸터들어가는 곳 -->
			</div>
	</body>
</html>