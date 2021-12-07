package com.itwill.gallery.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.gallery.GalleryDAO;
import com.itwillbs.gallery.GalleryDTO;

public class DeleteAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : DeleteAction - excute() 호출 ");

		// 요청값 저장
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		// 비번 일치 검사
		GalleryDAO gdao = new GalleryDAO();
		GalleryDTO gdto = gdao.getBoard(num);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.print("<script>");
		if (!request.getParameter("pwd").equals(gdto.getPwd())) {

			out.print("alert('비밀번호가 일치하지 않습니다');");
			out.print("window.close();");

		} else {

			int result = gdao.deleteBoard(gdto);

			if (result == 1) { // 글삭제 성공
				if (gdto.getFile() != null) {
//							gdto.setFile(file);

					// 삭제할 파일의 경로; //지울 파일명
					HttpSession session = request.getSession();
					String filePath = session.getServletContext().getRealPath("/attachFile/");

					File f = new File(filePath + "/" + gdto.getFile()); // 파일 객체생성
					if (f.exists())
						f.delete(); // 파일이 존재하면 파일을 삭제한다.
				}

				out.print("alert('삭제되었습니다');");
				out.print("window.close();");
				out.print("opener.location.href='./Gallery.ga?pageNum=");
				out.print(pageNum + "';");

			} else if (result == 0) {

				out.print("alert('비밀번호가 일치하지 않습니다');");
				out.print("window.close();");

			} else {

				out.print("alert('비밀번호가 일치하지 않습니다');");
				out.print("window.close();");
			}
		}

		out.print("</script>");
		out.close();

		return null;

	}

}
