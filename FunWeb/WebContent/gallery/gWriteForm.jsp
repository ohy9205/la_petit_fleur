<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>writeForm</title>
		<link href="../css/default.css?v=3" rel="stylesheet" type="text/css">
		<link href="../css/subpage.css?v=3" rel="stylesheet" type="text/css">
	
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
			String id = (String) session.getAttribute("id");

			%>
	
			<!-- 게시판 -->
			<article>
				<div class="write-wrap">
						<h1>GALLERY</h1>	
						<div class="sub_img_center"></div>
						<h2 class="sub-tit">글작성</h2>
					<form action="./WriteSet.ga" id="write-form" method="post" name="write" enctype="multipart/form-data">
						<table class="write update">
							<tr>
								<td class="label"><label for="name">작성자</label></td>
								<td><input type="text" name="name" id="name" value="${sessionScope.id }"
									readonly></td>
								<td class="label pass"><label for="pwd">비밀번호</label></td>
								<td class="pass"><input type="password" name="pwd" id="pwd" maxlength="4" /></td>
								<!-- ReviewVO의 변수명들과 name값을 맞춰주는게 좋음 -->
							</tr>
							<tr>
								<td class="label"><label for="subject">제목</label></td>
								<td colspan='3'><input type="text" name="subject" id="subject" maxlength="45" ></td>

							</tr>
							<tr>
								<td class="label"><label for="file">사진첨부</label></td>
								<td colspan='3'><input type="file" name="file" id="file"  accept=".gif, .jpg, .png" /></td>

							</tr>
							<tr>
								<td class="label"><label for="content">내용</label></td>
								<td colspan='3'><textarea id="content" rows="15" name="content"></textarea>
								</td>

							</tr>
						</table>
		
						<div id="btnList" >
							<button type="submit" class="btn btn-a" onclick="return check()">저장</button>
							
							<a href="./Gallery.ga" class="btn btn-a">목록</a>
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