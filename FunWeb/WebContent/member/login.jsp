<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
		<link href="../css/default.css?v=3" rel="stylesheet" type="text/css">
		<link href="../css/subpage.css?v=3" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div id="wrap">
			<!-- 헤더들어가는 곳 -->
			<jsp:include page="../inc/top.jsp"></jsp:include>
			<!-- 헤더들어가는 곳 -->
	
			<!-- 본문들어가는 곳 -->
			<!-- 본문메인이미지 -->
			<!-- 본문메인이미지 -->
			<!-- 본문내용 -->
			<article>
				<h1 class="sign-tit"></h1>
				<div class="sub_img_center"></div>
				<form action="./LoginAction.me" id="join" class="login" method="post" >
					<h2 class="sub-tit">로그인</h2>
					<fieldset>
						<label for="Id">User ID</label> <input type="text" id="Id" name="id" placeholder="아이디 입력"><br>
						<label for="Pwd">Password</label> <input type="password" id="Pwd" name="pwd" placeholder="비밀번호 입력">
					</fieldset>
					<div id="buttons">
						<input type="submit" value="로그인" class="submit dup">
					</div>
				</form>
			</article>
			<!-- 본문내용 -->
			<!-- 본문들어가는 곳 -->
	
			<div class="clear"></div>
			<!-- 푸터들어가는 곳 -->
			<jsp:include page="../inc/bottom.jsp"></jsp:include>
			<!-- 푸터들어가는 곳 -->
		</div>
	</body>
</html>