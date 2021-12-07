<%@page import="com.itwillbs.member.MemberDTO"%>
<%@page import="com.itwillbs.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="/FunWeb/css/default.css?v=3" rel="stylesheet" type="text/css">
		<link href="/FunWeb/css/subpage.css?v=3" rel="stylesheet" type="text/css">
		<title>update</title>
	</head>
	<body>
	
		<div id="wrap">
			<!-- 헤더들어가는 곳 -->
			<jsp:include page="../inc/top.jsp"></jsp:include>
			<!-- 헤더들어가는 곳 -->
	
			<!-- 본문들어가는 곳 -->
			<!-- 본문메인이미지 -->
			<!-- 본문메인이미지 -->
			
	<article>
			<form action="./UpdateSetAction.me" method="post" name="join" id="join">
				<h1></h1>
				<div class="sub_img_center"></div>
				<h2 class="sub-tit">회원 정보 수정</h2>
				<h3 class="sut-sub-tit">${mdto.id } 님의 회원 정보입니다.</h3>
				<fieldset>
				<legend>Basic Info</legend>
					<div>
						<label for="Id">User ID</label>
						<input type="text" id="Id" name="id" value="${mdto.id}" readonly>
					</div>
					<div>
						<label for="Pwd">Password</label>
						<input type="password" id="Pwd" name="pwd" value="${mdto.pwd}" readonly>
					</div>
					<div>
						<label for="Name">Name</label>
						<input type="text" id="Name" name="name" value="${mdto.name}" >
					</div>
					<div>
						<label for="Email">E-Mail</label>
						<input type="text" id="Email" name="email" value="${mdto.email}" />
					</div>
				</fieldset>
					
				<fieldset>
				<legend>Optional</legend>
					<div>
						<label for="Address">Address</label> <br>
						<input type="text" id="Address" name="postnum" class="postNum" value="${mdto.postnum}" readonly>
							<button type="button" onclick="goPopup();">우편번호 검색</button> <br>
						<input type="text" id="Address" name="address" value="${mdto.address}" readonly>
					</div>
					<div>
						<label for="Phone">Phone Number</label>
						<input type="text" id="Phone" name="phone" value="${mdto.phone}" >
					</div>
					<div>
						<label for="Mobile">Mobile Number</label>
						<input type="text" id="Mobile" name="mobile"  value="${mdto.mobile}">
					</div>
				</fieldset>
				
				<button type="submit" class="submit dup">수정</button>
			</form>
			
	</article>
	
			<div class="clear"></div>
			<!-- 푸터들어가는 곳 -->
			<jsp:include page="../inc/bottom.jsp"></jsp:include>
			<!-- 푸터들어가는 곳 -->
		</div>
		
	
		<script>
			function goPopup(){
				// 주소검색을 수행할 팝업 페이지를 호출합니다.
				// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
				var pop = window.open("/FunWeb/member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
				
				// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
			    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
			}
	
	
			function jusoCallBack(roadFullAddr,zipNo){
					// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
					document.join.address.value = roadFullAddr;
					document.join.postnum.value = zipNo;
			}
		</script>
	</body>
</html>