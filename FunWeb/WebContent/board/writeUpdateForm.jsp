<%@page import="com.itwillbs.board.BoardDTO"%>
<%@page import="com.itwillbs.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>UpdateForm</title>
		<link href="/FunWeb/css/default.css?v=10" rel="stylesheet" type="text/css">
		<link href="/FunWeb/css/subpage.css?v=10" rel="stylesheet" type="text/css">
	
	</head>
	<body>
	<%

	%>
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
					<form action="./UpdateSetAction.bo?num=${bdto.num }&pageNum=${param.pageNum}" method="post" name="write" enctype="multipart/form-data">	
						<h2 class="sub-tit">${bdto.subject }</h2>
						<input type="hidden" name="prevFile" value="${bdto.file }"/>
						<input type="hidden" name="fileDel" />
					
						<table class="write update">
							<tr>
								<td class="label"><label for="name">작성자</label></td>
								<td><input type="text" name="name" id="name" value="${bdto.name }"
									readonly></td>
								<td class="label pass"><label for="pwd">비밀번호</label></td>
								<td class="pass"><input type="password" name="pwd" id="pwd" /></td>

							</tr>
							<tr>
								<td class="label"><label for="subject">제목</label></td>
								<td colspan='3'><input type="text" name="subject" id="subject" value="${bdto.subject }"/></td>

							</tr>
							<tr>
								<td class="label"><label for="content">내용</label></td>
								<td colspan='3'><textarea id="content" name="content" 
										 maxlength="2000">${bdto.content }</textarea></td>

							</tr>
							<tr>
								<td class="label"><label for="file">첨부파일</label></td>
								<td colspan='3'>
									<input type="file" name="file" id="file"/>
										
								<c:if test="${ not empty bdto.file }">
									<div class="file-btn">
										<p id="prevFile"> <strong>현재파일</strong> &nbsp;&nbsp; 
											<a href="fileDownload.jsp?fileName=${bdto.file }">${bdto.file }</a> 
										</p>
										<button type="button" class="del" onclick="del() ">파일삭제</button>
									</div>
								</c:if>
							
								</td>

							</tr>
						</table>
		
						<div id="updateBtnList" >
							<button type="submit" class="btn btn-a updateBtn" onclick="return check()">수정</button>
						</div>

					</form>
					
					<script type="text/javascript">
						function check() {
							var pwd = document.write.pwd.value.trim();
							if(pwd===null || pwd.length<=0) {
								alert("비밀번호를 입력해 주세요.");
								return false;
							}
						}
						
						function del() {
							document.write.fileDel.value = "del";
							
							var prevFile = document.getElementById("prevFile");
							prevFile.innerText = "파일없음"
							
						}
					</script>
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