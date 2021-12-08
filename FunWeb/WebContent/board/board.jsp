<%@page import="com.itwillbs.board.BoardDTO"%>
<%@page import="com.itwillbs.board.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl 사용시에는 코어태그 필수(당연히 코어 파일도 있어야 함) -->
<!-- jstl 시작하려면 좀 불편함이 있지만 리퀘스트로 받아서 형변환을 해야하는 수고가 사라짐 -->
	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>board</title>
		<link href="../css/default.css?V=3" rel="stylesheet" type="text/css">
		<link href="../css/subpage.css?V=3" rel="stylesheet" type="text/css">
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
				<div class="article-notice">
					<h1>NOTICE</h1>
					<div class="sub_img_center"></div>
					<p class="notice-text">글쓰기는 <strong>로그인</strong>시에만 가능합니다</p>
					<table id="notice">
						<tr>
							<th class="tno">No</th>
							<th class="ttitle">제목</th>
							<th class="twrite">작성자</th>
							<th class="tdate">작성일</th>
							<th class="tread">조회수</th>
						</tr>
						
						<c:forEach var="board"  items="${boardList}">
							<tr class="list"
								onclick="location.href='./Content.bo?num=${board.num }&pageNum=${pageNum}' ; " >
								<td>${board.num }</td>
								<td class="subject">
									<c:forEach var="i"	begin="1" end="${ board.re_lev}" step="1">
										&nbsp;&nbsp;
									
										<c:if test="${ i > board.re_lev-1}">
											<strong>ㄴ</strong>
										</c:if>
									</c:forEach>
											${board.subject} 
										
									
									<c:if test="${not empty board.file }">
										<span>
											<img src="../images/fileIcon.png">
										</span>
									</c:if>	
								</td>
								
								<td>${board.name }</td>
								<td>${board.date }</td>
								<td>${board.readcount }</td>
							</tr>
							
						</c:forEach>
					</table>
	
					<div id="listBottom">
						<div class="writeBtnBox">
						<c:if test="${not empty sessionScope.id}">
							<a href="./Write.bo" id="writeBtn" class="btn bnt-a">글쓰기</a>
						</c:if>
						</div>
						<form action="./Search.bo" name="search" id="table_search">
							<input type="text" name="search" class="input_box" placeholder="제목+내용"> 
							<button type="submit" class="btn" >검색</button>
						</form>
					</div>
					
		<!-- 페이지번호 목록 -->
					<div id="page_control">
					<c:if test="${startPage > pageNumBlock }">
						<a href="./Notice.bo?pageNum=${startPage-1 }">Prev</a>
					</c:if>
					
					<c:forEach var="i"  begin="${startPage }"  end="${endPage }"  step="1">
						<a href="./Notice.bo?pageNum=${i }"> ${i } </a>
					</c:forEach>
					
					<c:if test="${endPage < pageNumCount}"> <!-- 블럭의 마지막 페이지번호가 실제 페이지번호보다 작을때 -->
						<a href="./Notice.bo?pageNum=${startPage+pageNumBlock }">Next</a>
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