<%@page import="com.itwillbs.board.BoardDTO"%>
<%@page import="com.itwillbs.board.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
<link href="/FunWeb/css/default.css?after" rel="stylesheet"
	type="text/css">
<link href="/FunWeb/css/subpage.css?after" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->
		<%
		/* 페이징 */
		// 한 페이지당 목록 10개씩 보여주기
		// 		int listCount = 10;

		// 		// 페이지 번호
		// 		String pageNum = request.getParameter("pageNum");
		// 		if(pageNum==null) pageNum="1";

		// 		// 페이지번호를 계산하기위해 정수형으로 변환
		// 		int thisPageNum =  Integer.parseInt(pageNum);

		// 		int startRow = (thisPageNum-1)*listCount+1;
		// 		int endRow = thisPageNum * listCount;

		// 		BoardDAO bdao = new BoardDAO();
		// 		String search = request.getParameter("search");
		// 		int count = bdao.getSearchCount(search);
		// 		List<BoardDTO> boardList = bdao.searchBoardList(startRow, listCount, search);
		// 		System.out.println(boardList);
		%>

		<!-- 게시판 -->
		<article>
			<div class="article-notice">
				<h1>NOTICE</h1>
				<div class="sub_img_center"></div>
				<p class="notice-text">
					<strong>${param.search } </strong>에대한 검색 결과입니다
				</p>
				<table id="notice">
					<tr>
						<th class="tno">No.</th>
						<th class="ttitle">Title</th>
						<th class="twrite">Writer</th>
						<th class="tdate">Date</th>
						<th class="tread">Read</th>
					</tr>
					<%
					// 					for(int i=0; i<boardList.size(); i++) {
					%>
					<c:forEach var="board" items="${boardList }">

						<tr class="list"
							onclick="location.href='./Content.bo?num=${board.num }&pageNum=${pageNum} '">
							<td>${board.num }</td>
							<td>${board.subject }</td>
							<td>${board.name }</td>
							<td>${board.date }</td>
							<td>${board.readcount }</td>
						</tr>
					</c:forEach>
					<%
					// 					}
					%>
				</table>

				<div id="listBottom">
					<div id="writeBtnWrap">
						<button type="button" onclick="location.href='writeForm.jsp'"
							id="writeBtn" class="btn">글쓰기</button>
					</div>
					<form action="#" name="search" id="table_search">
						<input type="text" name="search" class="input_box">
						<button type="submit" class="btn">검색</button>
					</form>
				</div>
				<%
				String id = (String) session.getAttribute("id");
				if (id != null) {
				%>

				<script>
					document.getElementById("writeBtn").classList
							.remove('hide');
				</script>
				<%
				}
				%>

				<!-- 페이지번호 목록 -->
				<%
				// 					// 페이지 버튼 다섯개씩 보이게 함
				// 					int pageNumBlock = 5;

				// 					int startPage = ((thisPageNum-1)/pageNumBlock)*pageNumBlock+1;
				// 					int endPage = startPage+pageNumBlock-1;

				// 					// 실제 페이지번호 개수 = (실제 글 개수/페이지당 보이는 글 수)+(만약 나머지 있으면 +1, 없으면 +0)
				// 					int pageNumCount = (count/listCount)+(count%listCount != 0 ? 1 : 0);

				// 					// 실제 페이지수보다 이론적인 계산 페이지보다 크면 
				// 					// -> 마지막 페이지번호를 실제 페이지 끝번호로 바꿈
				// 					if(endPage > pageNumCount) {
				// 						endPage = pageNumCount;
				// 					}
				%>
				<div id="page_control">
					<%
					// 이전버튼
// 					if (startPage > pageNumBlock) {
					%>
					<c:if test="${startPage > pageNumBlock}" >
						<a
							href="./Search.bo?pageNum=${pageNum-1 }&search=${search}">Prev</a>
					</c:if>
					<%
// 					}

					//페이지 번호 목록 출력
// 					for (int i = startPage; i <= endPage; i++) {
					%>
					<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
						<a href="./Search.bo?pageNum=${i }&search=${search}">
							${i }
						</a>
					</c:forEach>
					<%
// 					}

					// 다음버튼
// 					if (endPage < pageNumCount) {
					%>
					<c:if test="${endPage < pageNumCount }">
					<a
						href="./Search.bo?pageNum=${startPage + pageNumBlock }&search=${search}">Next</a>
					</c:if>
					<%
// 					}
					%>
				</div>
			</div>
		</article>
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>