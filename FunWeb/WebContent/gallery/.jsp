<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>writeForm</title>
		<link href="../css/default.css?after" rel="stylesheet" type="text/css">
		<link href="../css/subpage.css?afters" rel="stylesheet" type="text/css">
	
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
				int num = Integer.parseInt(request.getParameter("num"));
						int thisPageNum = Integer.parseInt(request.getParameter("thisPageNum"));
						
						BoardDAO bdao = new BoardDAO();
						bdao.updateReadCount(num);
						
						BoardDTO bvo = bdao.getBoard(num);
				%>
	
			<!-- 게시판 -->
			<article>
				<h1>Write</h1>
					<table id="write">
						<tr>
							<td class="label"><label for="name">작성자</label></td>
							<td> <%=bvo.getName() %> </td>
<!-- 							<td class="label pass"><label for="pwd">비밀번호</label></td> -->
<!-- 							<td class="pass"><input type="password" name="pwd" id="pwd" /></td> -->
							<!-- boardVO의 변수명들과 name값을 맞춰주는게 좋음 -->
						</tr>
						<tr>
							<td class="label"><label for="subject">제목</label></td>
							<td><%=bvo.getSubject() %></td>
<!-- 							<td></td> -->
<!-- 							<td></td> -->
						</tr>
						<tr>
							<td class="label"><label for="date">작성일</label></td>
							<td><%=bvo.getDate() %></td>
<!-- 							<td></td> -->
<!-- 							<td></td> -->
						</tr>
						<tr>
							<td class="label"><label for="content">내용</label></td>
							<td width='500' height='150'><%=bvo.getContent() %></td>
<!-- 							<td></td> -->
<!-- 							<td></td> -->
						</tr>
						<tr>
							<td class="label"><label for="file">첨부파일</label></td>
							<td width='500' height='150'><%=bvo.getFile() %></td>
<!-- 							<td></td> -->
<!-- 							<td></td> -->
						</tr>
					</table>
	
					<div id="btnList" >		
				<%
				
					if(session.getAttribute("id")!=null && session.getAttribute("id").equals(bvo.getName())){
				%>
						<button type="button" class="btn"
						onclick="location.href='writeUpdateForm.jsp?num=<%=num %>&thisPageNum=<%=thisPageNum %>';">수정</button>
			

						<button type="button" class="btn"
						onclick="location.href='writeDeletePro.jsp?num=<%=num %>&thisPageNum=<%=thisPageNum %>';">삭제</button>
				<%
					}
				%>	
						<button type="button" class="btn"
						onclick="location.href='board.jsp?thisPageNum=<%=thisPageNum %>';">목록</button>
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