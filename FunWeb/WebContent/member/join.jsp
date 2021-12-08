<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>join</title>
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
				<form action="./JoinAction.me" id="join" name="join" method="post">
				<h2 class="sub-tit">회원가입</h2>
					<fieldset>
						<legend>BASIC INFO</legend> 
						<label for="Id">User ID</label> <br> <input type="text" id="Id" name="id" class="id" placeholder="아이디" onkeyup="checkId()" >
						<p id="dupdiv"></p>
						<label for="Pwd">Password</label><br> <input type="password" id="Pwd" name="pwd" placeholder="비밀번호"><br>
						<label for="Pwd2">Retype Password</label> <br><input type="password" id="Pwd2" name="pwd2" placeholder="비밀번호 확인"><br>
						<label for="Name" >Name</label> <br> <input type="text" id="Name" name="name" placeholder="이름"><br>
						<label>E-Mail</label> <br> <input type="email" name="email" placeholder="이메일"><br>
						<label>Retype E-Mail</label> <br> <input type="email" name="email2" placeholder="이메일 확인"><br>
					</fieldset>
					
					
					<fieldset>
						<legend>OPTIONAL</legend>
						<label for="postnum">Address</label> <br>
							<input type="text" name="postnum" class="postNum" placeholder="우편번호" readonly> 
								<button type="button" class="dup postnumBtn" onclick="goPopup();">우편번호 검색</button> <br>
							<input type="text" name="address" placeholder="상세주소" readonly><br>
						<label>Tel</label> <br> <input type="text" name="phone" placeholder="연락처"><br>
						<label>Mobile</label> <br> <input type="text" name="mobile"><br>
					</fieldset>
					
					<div id="buttons">
						<button type="submit" class="submit dup" id="Submit" onclick="checkForm();">가입</button>
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
		
		<script>
			function checkId() { //id체크 
				var id = document.getElementById("Id");
				var dup = document.getElementsByClassName("dup")[0];
				
				 var xhr = new XMLHttpRequest();
				
		        //요청을 보낼 방식, 주소, 비동기여부 설정
		        xhr.open('GET', './member/joinIdCheck.jsp?id='+id.value, true);
		
		        //요청 전송
		        xhr.send();

		        //통신후 작업
		        xhr.onload = () => {
		            //통신 성공
		            if (xhr.status == 200) {
						document.getElementById("dupdiv").innerHTML=xhr.responseText;
						id.readonly;
		            } else {
		                //통신 실패
		                console.log("통신 실패");
		            }
		 	   }
			}
			
			
			var submit = document.getElementById("Submit");
			function checkForm() {
				var id = document.join.id.value;
				var pwd = document.join.pwd.value;
				var name = document.join.name.value;
				var email = document.join.email.value;
				
				var pwd2 = document.join.pwd2.value;
				var email2 = document.join.email2.value;
				
				if(id.length===0 || pwd.length===0 || name.length===0 || email.length===0) {
					alert("필수항목을 모두 입력하세요");
					event.preventDefault();
				} else {

					if(pwd!==pwd2) {
						alert("비밀번호가 일치하지 않습니다");
						event.preventDefault();
					} else if(email!==email2) {
						alert("이메일이 일치하지 않습니다");
						event.preventDefault();
					}
				}
				
			}
			
			
			
			function goPopup(){
				// 주소검색을 수행할 팝업 페이지를 호출합니다.
				// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
				var pop = window.open("../member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
				
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