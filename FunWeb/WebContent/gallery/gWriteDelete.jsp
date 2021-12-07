
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>writeDelete</title>
	</head>
	<body>
	<head>
		<meta charset="UTF-8">
		<title>joinIdCheck</title>
		
		<style>
			div {
				width: 600px;
				height: 200px;
				display: flex;
				justify-content: center;
				align-items: center;
			}
		</style>
	</head>
	<body>
		<div>
			<form action="./DeleteSet.ga?num=${param.num }&pageNum=${param.pageNum}" method="post" name="check">
				<label for="pwd">글 비밀번호 입력</label>
				<input type="password" name="pwd">
				<button type="submit">삭제</button>
			</form>
		</div>
		
	</body>
</html>