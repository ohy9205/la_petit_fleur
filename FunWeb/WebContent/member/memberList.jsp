<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>memberList.jsp</title>
</head>
	<body>
		<button type="button" id="btn">회원목록</button>
		<table>
			<tr>
				<td>
					아이디
				</td>
				<td>
					비밀번호
				</td>
				<td>
					이름
				</td>
				<td>
					이메일
				</td>
			</tr>
		</table>
	
		<script>
				var btn = document.getElementById("btn");
				btn.addEventListener('click', function(event){
					alert("메시지");
					
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
			            } else {
			                //통신 실패
			                console.log("통신 실패");
			            }
   					 }
					
					
					
					
				});
		</script>
	</body>
</html>