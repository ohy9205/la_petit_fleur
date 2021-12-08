<%@page import="java.util.List"%>
<%@page import="com.itwillbs.board.BoardDTO"%>
<%@page import="com.itwillbs.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="kr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no,shrink-to-fit=no">
	<title>main</title>
		<link href="../css/default.css?v=4" rel="stylesheet" type="text/css">
		<link href="../css/front.css?v=4" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div id="wrap">
			<!-- 헤더파일들어가는 곳 -->	
			<div class="header_wrap">
				<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- 				헤더파일들어가는 곳 -->
<!-- 				메인이미지 들어가는곳 -->
				<div id="main_img">
					<h2 class="banner-inner">
					</h2>
	 			</div>
			</div>
			<!-- 메인이미지 들어가는곳 -->
			<!-- 메인 콘텐츠 들어가는 곳 -->
			<article id="front">
				
				<div class="brand-intro-wrap">
					<div class="brand-intro">
						<h3 class="tit-text">ABOUT</h3>
						<div class="line"></div>
						<p class="gal-intro">La Petit Fleu</p>
						<p><strong class="bold">쁘띠플로</strong>
							는 당신의 행복과 함께합니다.</p>
						<p>평범한 일상에 꽃을 더해<br>
						 특별한 하루를 만들어 보세요.</p> 
					</div>
					<div class="brand-img">
						<img src="/FunWeb/images/brand-about.jpg" alt="꽃">
					</div>
				</div>
				
				
				<div id="gallery">
					<div class="gal-inner">
						<ul class="gal-left">
							<li>
								<img src="/FunWeb/images/main-gal-3.jpg" alt="">
							</li>
							<li>
								<img src="/FunWeb/images/main-gal-4.jpg" alt="">
							</li>
							
						</ul>
						<ul class="gal-right">
							<li class="gal-tit">
								<h3 class="tit-text">GALLERY</h3>
								<div class="line"></div>
								<p class="gal-intro">Memory with <br> 
									La Petit Fleu</p>
<!-- 								<p class="gal-text">쁘띠플로와 함께한 소중한 추억들</p> -->
								<p class="gal-link"><a href="./Gallery.ga">show more</a></p>
							</li>
							<li>
								<img src="/FunWeb/images/main-gal-1.jpg" alt="">
							</li>
							<li>
								<img src="/FunWeb/images/main-gal-2.jpg" alt="">
							</li>
						</ul>
					</div>
				</div>


				<div class="booking-wrap">
					<div class="booking-inner">
						<div id="booking">
							<h1 class="contact-title">BOOKING</h1>
								<p class="booking-text">
									방문예약을 원하시는분은 예약표를 작성해주세요 <br> 
									예약표는 <strong>로그인시에만</strong> 작성 가능합니다
								</p>
								<form action="orderFormPro.jsp" method="post" name="booking" class="boofing-form">
									<table class="booking-table">
			<%-- 							<input type="hidden" name="id" value="<%=id %>"> --%>
										<tr>
											<td class="label"><label for="name">이름</label></td>
											<td colspan="2"><input type="text" name="name" id="name" /></td>
										</tr>
										<tr>
											<td class="label"><label for="tel">연락처</label></td>
											<td colspan="2"><input type="text" name="tel" id="tel" /></td>
										</tr>
										<tr>
											<td class="label"><label for="bookingDate">예약일</label></td>
											<td>
	 											<input type="text" name="bookingDate" placeholder="날짜를 선택하세요">
											</td>
											<td>
	 											<input type="text" name="bookingTime" placeholder="시간을 선택하세요">
											</td>
										</tr>
										
										<tr>
											<td class="label"><label for="content">문의사항</label></td>
											<td colspan="2"><textarea id="content" name="content"
													maxlength="500" placeholder="500자 이내의 문의사항을 입력해주세요"></textarea></td>
										</tr>
									</table>
					
									<div id="btnList" >
										<button type="submit" class="btn" onclick="return check()">예약하기</button>
									</div>
								</form>
								
						</div>	<!-- booking -->	
						
						<div id="board">
							<h1 class="contact-title">LATIEST POST</h1>
							<p class="board-text"><a href="./Notice.bo" >게시판으로 이동</a></p>
							<ul class="board-list">									

							<c:forEach var="boardList" items="${boardList }">
								<li onclick="location.href='./Content.bo?num=${boardList.num }&pageNum=1'">
									<h3 class="board-tit">${boardList.subject }</h3>
									<p>${boardList.date }</p>
								</li>
							</c:forEach>
							
							</ul>
						</div>
					</div>
				</div> <!-- booking-wrap -->				


				<div id="contact">
						<div id="map" style="width:65%;height:320px;"></div>
						
							<ul class="contact-contact">
								<li class="contact-title">CONTACT</li>
								<li></li>
								<li>부산 사하구 다대로 617 <br> (다대 자유아파트) </li>
								<li>ohy9205@navr.com</li>
								<li>051-123-1234</li>
							</ul>
				</div>
				
				

			</article>
			<!-- 메인 콘텐츠 들어가는 곳 -->
			
			<!-- 푸터 들어가는 곳 -->
			<jsp:include page="../inc/bottom.jsp"></jsp:include>
			<!-- 푸터 들어가는 곳 -->
			
		</div> <!--  wrap end -->
		
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=10ce04d3ae3b8e2aa3bb6dbaa488da69"></script>
		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div

		  mapOption = { 
		        center: new kakao.maps.LatLng(35.051800, 128.972545), // 지도의 중심좌표
		        level: 4 // 지도의 확대 레벨
		    };

		var map = new kakao.maps.Map(mapContainer, mapOption);

		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
		    imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
		    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
		    markerPosition = new kakao.maps.LatLng(35.051800, 128.9725458); // 마커가 표시될 위치입니다
		
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		  position: markerPosition,
		  image: markerImage // 마커이미지 설정 
		});
		
		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);  
		
		// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		var content = '<div class="customoverlay">' +
		    '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
		    '    <span class="title">쁘띠플로</span>' +
		    '  </a>' +
		    '</div>';
		
		// 커스텀 오버레이가 표시될 위치입니다 
		var position = new kakao.maps.LatLng(35.051800, 128.972545);  
		
		// 커스텀 오버레이를 생성합니다
		var customOverlay = new kakao.maps.CustomOverlay({
		    map: map,
		    position: position,
		    content: content,
		    yAnchor: 1 
		});
		</script>			
		
		
	</body>
</html>