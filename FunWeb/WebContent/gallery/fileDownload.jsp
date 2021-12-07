<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>fileDownload</title>
	</head>
	<body>
	<%
		String fileName = request.getParameter("fileName");
		
		// 다운로드할 파일이 저장된 경로
		String filePath = request.getRealPath("/attachFile/"+fileName);
		
		// 다운로드할 파일의 절대경로
// 		String filePath = "../attachFile/"+fileName;
		
// 		String filePath = request.getRealPath("/file")+"\\"+fileName;
		System.out.println("filePath : - "+filePath);
		
		// 파일 입력 -> bufferedStream이용
		byte[] buffer = new byte[4096];
		
		// 다운로드할 파일 읽어오기 (bufferedStream이용)
		FileInputStream fis = new FileInputStream(filePath);
		
		// 다운로드할 파일의 MIME타입 -> 파일이 어떤 형태인지 알기 위함
		String mimeType = getServletContext().getMimeType(filePath);
		System.out.println("MIME-TYPE : "+mimeType);
		
		// MIME타입이 없을경우 application/octet-stream으로 지정(이진파일의 가장 기본값)
		// => 잘 알려지지 않은 이진파일임을 의미 => 자동으로 실행되지 않도록 처리
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		// response를 통해 파일 출력
		response.setContentType(mimeType);
		
		// 브라우저 구분(ie/그 외)
		String agent = request.getHeader("User-Agent");
		
		boolean ieBrowser = ( agent.indexOf("MISE") > -1 ) || ( agent.indexOf("Trident") > -1); // 둘 중 하나라도 참이면 ie
		
		// 한글 인코딩
		if(ieBrowser) {
			fileName = URLEncoder.encode(fileName, "utf-8").replace("\\+","%20");
		} else {
			fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
		}
				
		// 파일 출력할 통로 생성하기
		// -> 통로 속성을 설정해서 브라우저에서 파싱되는 파일들도 다운로드 형태로 동작하게 함
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		
		ServletOutputStream output = response.getOutputStream();
		
		int data = 0;
		while ( (data = fis.read(buffer,0,buffer.length)) != -1) {
			output.write(buffer,0,buffer.length);
		}
		output.flush();
		output.close();
		fis.close();
	%>
	</body>
</html>