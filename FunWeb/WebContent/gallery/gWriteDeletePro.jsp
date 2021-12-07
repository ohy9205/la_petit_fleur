<%@page import="java.io.File"%>
<%@page import="com.itwillbs.gallery.GalleryDAO"%>
<%@page import="com.itwillbs.gallery.GalleryDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>writeDeletePro</title>
	</head>
	<body>
	<%
	int num = Integer.parseInt(request.getParameter("num"));
			int thisPageNum = Integer.parseInt(request.getParameter("thisPageNum"));
			
			GalleryDAO bdao = new GalleryDAO();
			GalleryDTO bvo = bdao.getBoard(num);
			
			
			System.out.println("입력비번: "+request.getParameter("pwd"));
			System.out.println("얻어온비번: "+bvo.getPwd());
			
			if(!request.getParameter("pwd").equals(bvo.getPwd())) {
	%>
		<script>
			alert("비밀번호가 올바르지 않습니다");
			window.close();
		</script>
	<%
		} else {
	
		
			String file = bvo.getFile();
				
			
			int result = bdao.deleteBoard(bvo);
			
			if(result == 1) {
				bvo.setFile(file);

				// 삭제할 파일의 경로; //지울 파일명
				String filePath = request.getRealPath("/attachFile/");
				filePath += file;
				System.out.println("filePath > "+filePath);
				
				File f = new File(filePath); // 파일 객체생성
				if (f.exists())
					f.delete(); // 파일이 존재하면 파일을 삭제한다.
						
			} else if(result == 0) {

				System.out.println(" 비번오류 ");
			
			%>
			<script>
				alert("실패했습니다");
				window.close();	
			</script>
			<%
			}  else {

				System.out.println(" 해당 글 없음 ");
				%>
				<script>
					alert("실패했습니다");
					window.close();	
				</script>
				<%
			}
		
		}
	%>
		<script type="text/javascript">
			alert("삭제되었습니다");
			window.close();
			opener.location.href="gBoard.jsp?thisPageNum=<%=thisPageNum %>";
		</script>
	
	</body>
</html>