<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>writeForm</title>
		<link href="/FunWeb/css/default.css?v=3" rel="stylesheet" type="text/css">
		<link href="/FunWeb/css/subpage.css?v=3" rel="stylesheet" type="text/css">
	
	</head>
	<body>
		<div id="wrap">
			<!-- 헤더들어가는 곳 -->
			<jsp:include page="../inc/top.jsp"></jsp:include>
			<!-- 헤더들어가는 곳 -->
	
			<!-- 본문들어가는 곳 -->
			<!-- 메인이미지 -->
			<!-- 메인이미지 -->
	
			<c:if test="${empty sessionScope.id }">
				<script type="text/javascript">
				alert("글쓰기는 로그인이 필요합니다");
				location.href = "./Login.me";
				</script>
			</c:if>
	
			<!-- 게시판 -->
			<article>
				<div class="write-wrap">
					<h1>NOTICE</h1>
					<div class="sub_img_center"></div>
					<form action="./WriteAction.bo" id="write-form" method="post" name="write" enctype="multipart/form-data">
						<table class="write">
							<tr>
								<td class="label"><label for="name">작성자</label></td>
								<td><input type="text" name="name" id="name" value="${sessionScope.id }"
									readonly></td>
								<td class="label pass"><label for="pwd">비밀번호</label></td>
								<td class="pass"><input type="password" name="pwd" id="pwd" maxlength="4" /></td>
								<!-- boardVO의 변수명들과 name값을 맞춰주는게 좋음 -->
							</tr>
							<tr>
								<td class="label"><label for="subject">제목</label></td>
								<td colspan='3'><input type="text" name="subject" id="subject" maxlength="45" ></td>

							</tr>
							<tr>
								<td class="label"><label for="content">내용</label></td>
								<td colspan='3'><textarea id="content" name="content" maxlength="2000"></textarea>
								</td>

							</tr>
							<tr>
								<td class="label"><label for="file">첨부파일</label></td>
								<td colspan='3'><input type="file" name="file" id="file" /></td>

							</tr>
						</table>
		
						<div id="btnList" >
							<button type="submit" class="btn btn-a" onclick="return check()">저장</button>
							
							<a href="./Notice.bo" class="btn btn-a">목록</a>
						</div>
	
					</form>
				</div> <!-- .write -->
				
				<script type="text/javascript">
					function check() {
						var pwd = document.write.pwd.value;
						if(pwd===null || pwd.trim().length<=0 ) {
							alert("비밀번호를 입력해 주세요.");
							return false;
						}
						
						var subject = document.write.subject.value;
						if(subject === null || subject.trim().length<=0) {
							alert("제목을 입력해 주세요");
							return false;
						}
					}
				</script>
			</article>
			<!-- 게시판 -->
			<!-- 본문들어가는 곳 -->
			
			<!-- 푸터들어가는 곳 -->
			<jsp:include page="../inc/bottom.jsp" />
			<!-- 푸터들어가는 곳 -->
		</div>
	</body>
</html>