package com.itwillbs.board;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	/* db 관련 객체 */
	private Connection con = null; // DB연결정보를 저장하는 객체
	private PreparedStatement pstmt = null; // SQL구문을 처리하는 객체
	private ResultSet rs = null; // 'select구문'의 결과 데이터(레코드셋)를 저장하는 객체
	private String sql = ""; // 실행할 SQL구문 작성

	/* db 연결(메서드) */
	private Connection getCon() throws Exception {

		// 디비연결 - 커넥션 풀(Context.xml) : db연결정보를 저장해서 사용

		// Context객체(프로젝트) 생성 - meta폴더에 접근할 수 있게 해줌
		Context initCTX = new InitialContext();
		// 이 변수에 내 프로젝트에 대한 정보를 초기화해서 넣겠다 는 의미

		// db연결정보 가져옴 - context.xml에서 가져옴
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/FunWeb");

		// db와 연결맺고 결과를 con에 저장
		con = ds.getConnection();

		System.out.println("  DB연결 성공! ");

		return con;
	} // getCon();

	/* db 연결 해제 - 자원해제 */
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // closeDB();

	/* insertBoard(bb) */
	public boolean insertBoard(BoardDTO mdto) {

		int num = 0;
		try {
			// 1.2. db연결
			con = getCon();

			// 3. sql구문작성 & pstmt 객체생성
			// 3-1. num계산하는 SQL 구문
//			sql = "select num from itwill_board"; // 데이터없음- false나옴
			sql = "select max(num) from jsp_board"; // 데이터있음- True나옴(함수는 무조건 하나 이상의 결과 나옴)

			pstmt = con.prepareStatement(sql);

			// 4. sql구문 실행
			rs = pstmt.executeQuery();

			// *데이터 처리(글번호num 계산 동작)
			if (rs.next()) { // 최대num값에서 1 더한 값을 num에 저장 후 db의 num에 저장함
//				num = rs.getInt("max(num)")+1;
				num = rs.getInt(1) + 1; // index번호로도 사용 가능 - 보통 인덱스 형태를 많이씀
// * rs.getInt(1)+1은 null+1 꼴인데 널 익셉션 안나는 이유 
//	: 메소드 내용을 보면 getInt는 sql에 관련되었을 경우 null이 아닌 0을 반환함을 알 수 있음
			}
//			else { 
//				num = 1; // 데이터 없으면 num에 1 담음
//				System.out.println("$$$$$$$$$$$$$$$");
//			}  => getInt()메서드 특성에 의해서 else는 쓸모없는 구문이 됨

			System.out.println("글 번호: " + num);

			// *글 정보 저장 (insert구문)
			// 3. sql구문 작성
			sql = "insert into jsp_board(num,name,pwd,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,ip,file,date) " + "values(?,?,?,?,?,?,?,?,?,?,?,now())"; // date자리에
																												// 현재시간정보
																												// 반환하는
																												// now()함수
																												// 사용

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 아까 계산한 num값
			pstmt.setString(2, mdto.getName());
			pstmt.setString(3, mdto.getPwd());
			pstmt.setString(4, mdto.getSubject());
			pstmt.setString(5, mdto.getContent());

			pstmt.setInt(6, 0); // 조회수 - 0
			pstmt.setInt(7, num); // 답글 - 그룹번호(일반글은 글번호와 동일하게 설정해봄)
			pstmt.setInt(8, 0); // 답글 - 들여쓰기(일반글 0)
			pstmt.setInt(9, 0); // 답글 - 순서(일반글 0)

			pstmt.setString(10, mdto.getIp());
			pstmt.setString(11, mdto.getFile());

			// 4. sql구문 실행
			pstmt.executeUpdate();

			System.out.println(" 게시판 글 작성 완료!!! ");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}

	} // insertBoard(mdto);

	/* getBoardCount() - 글의 총 개수 */
	public int getBoardCount() {

		int count = 0;
		try {
			con = getCon();
			sql = "select count(num) from jsp_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return count;
	} // getBoardCount();

	/* getBoardList(int startRow, int pageSize) */
	public List<BoardDTO> getBoardList(int startRow, int listCount) {

		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = getCon();

			// 답글이 없을 경우
			// : 글번호(num) 기준으로 정렬
//			sql = "select * from itwill_board order by num desc";

			// 답글이 있을 경우
			// : 그룹번호(re_ref) 내림차순 -> 순서(re_seq) 오름차순 순으로 정렬
//			sql = "select * from itwill_board order by re_ref desc, re_seq";

			// 페이징 기법 사용시
			sql = "select * from jsp_board order by re_ref desc, re_seq limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow - 1); // limit은 0부터 시작함. startRow는 1부터 시작하므로 -1 해서 0으로 만들어줌
			pstmt.setInt(2, listCount);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO bvo = new BoardDTO();

				bvo.setNum(rs.getInt(1));
				bvo.setName(rs.getString(2));
				bvo.setPwd(rs.getString(3));
				bvo.setSubject(rs.getString(4));
				bvo.setContent(rs.getString(5));
				bvo.setReadcount(rs.getInt(6));
				bvo.setRe_ref(rs.getInt(7));
				bvo.setRe_lev(rs.getInt(8));
				bvo.setRe_seq(rs.getInt(9));
				bvo.setIp(rs.getString(10));
				bvo.setFile(rs.getString(11));
				bvo.setDate(rs.getDate(12));

				list.add(bvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return list;
	} // getBoardList();

	/* getSearchCount() - 글의 총 개수 */
	public int getSearchCount(String search) {

		int count = 0;
		try {
			con = getCon();
			sql = "select count(num) from jsp_board " + "where subject like ? OR content like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return count;
	} // getSearchCount();

	/* searchBoardList(int startRow, int pageSize, String search) */
	public List<BoardDTO> searchBoardList(int startRow, int listCount, String search) {

		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = getCon();

			// 페이징 기법 사용시
			sql = "select * from jsp_board " + "where subject like ? OR content like ? " + "order by re_ref desc "
					+ "limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, startRow - 1); // limit은 0부터 시작함. startRow는 1부터 시작하므로 -1 해서 0으로 만들어줌
			pstmt.setInt(4, listCount);
			rs = pstmt.executeQuery();

			System.out.println("검색성공");
			while (rs.next()) {
				BoardDTO bvo = new BoardDTO();

				bvo.setNum(rs.getInt(1));
				bvo.setName(rs.getString(2));
				bvo.setPwd(rs.getString(3));
				bvo.setSubject(rs.getString(4));
				bvo.setContent(rs.getString(5));
				bvo.setReadcount(rs.getInt(6));
				bvo.setRe_ref(rs.getInt(7));
				bvo.setRe_lev(rs.getInt(8));
				bvo.setRe_seq(rs.getInt(9));
				bvo.setIp(rs.getString(10));
				bvo.setFile(rs.getString(11));
				bvo.setDate(rs.getDate(12));

				list.add(bvo);
				System.out.println("검색정보 반환");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return list;
	} // searchBoardList();

	/* updateReadCount(int num) */
	public void updateReadCount(int num) {

		try {

			// 1.2. DB연결
			con = getCon();

			// 3. sql구문작성 & pstmt객체 생성
			sql = "update jsp_board set readcount = readcount+1 " + "where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			// 4. 실행
			pstmt.executeUpdate();

			System.out.println(" 글 조회수 1증가 완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} // updateReadCount()

	/* getBoard(num) - 글내용 */
	public BoardDTO getBoard(int num) {

		BoardDTO vo = null;
		try {
			con = getCon();

			sql = "select * from jsp_board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// DB결과를 vO객체에 저장
				vo = new BoardDTO();

				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("date"));
				vo.setFile(rs.getString("file"));
				vo.setIp(rs.getString("ip"));
				vo.setName(rs.getString("name"));
				vo.setNum(rs.getInt("num"));
				vo.setPwd(rs.getString("pwd"));
				vo.setRe_lev(rs.getInt("re_lev"));
				vo.setRe_ref(rs.getInt("re_ref"));
				vo.setRe_seq(rs.getInt("re_seq"));
				vo.setReadcount(rs.getInt("readCount"));
				vo.setSubject(rs.getString("subject"));
			}

			System.out.println(" DAO : 글 번호에 해당하는 글 조회");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return vo;
	} // getBoard();

	
	public int updateBoard(BoardDTO vo) {
		int result = 0;
		try {
			con = getCon();

			// 이름, 패스워드 비교해서 본인이 쓴 글만 수정 가능 -> select구문부터 실행
			sql = "select pwd from jsp_board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			rs = pstmt.executeQuery();
			System.out.println(" update-해당 행 조회완료 ");

			// update 데이터 처리
			if (rs.next()) {
				if (vo.getPwd().equals(rs.getString("pwd"))) {
					sql = "update jsp_board set subject=?, content=?, file=? " + "where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, vo.getSubject());
					pstmt.setString(2, vo.getContent());
					pstmt.setString(3, vo.getFile());
					pstmt.setInt(4, vo.getNum());
					pstmt.executeUpdate();

					result = 1;
					System.out.println(" 회원정보 수정 완료! ");
				} else { // 비밀번호 틀렸을시
					result = 0;
				}
			} else { // num번호 자체가 잘못됐을경우(적성됐던 글이 없을경우)
				result = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	} // updateBoard()

	
	public int deleteBoard(BoardDTO vo) {
		int result = 0;
		try {

			con = getCon();
			sql = "select pwd from jsp_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			rs = pstmt.executeQuery();
			System.out.println("비번 조회 성공!");

			if (rs.next()) {
				if (vo.getPwd().equals(rs.getString("pwd"))) {
					sql = "delete from jsp_board where re_ref=?";
					pstmt = con.prepareStatement(sql); // 다시 로딩하지않으면 전에 만들어둔 select구문이 실행됨
					pstmt.setInt(1, vo.getRe_ref());
					pstmt.executeUpdate();

					sql = "update jsp_board set num=num-1, re_ref=re_ref-1 " 
									+ "where re_ref>?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, vo.getRe_ref());
					pstmt.executeUpdate();
//			
					System.out.println(" 글 삭제 완료! ");

					result = 1;

				} else {
					result = 0;
				} // Pwd일치 확인

			} else {
				result = -1;
			} // rs반환값 여부 확인

		} catch (Exception e) {
			System.out.println(" 글 삭제 실패! ");
		} finally {
			closeDB();
		}

		return result;
	} // deleteBoard();

	public void reInsertBoard(BoardDTO vo) {

		int num = 0;// 글번호
		try {
			con = getCon();

			// 1. 답글 번호(num) 계산
			sql = "select max(num) from jsp_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
				System.out.println(" 답글의 글번호 : " + num);
			}

			// 2. 답글 순서 재배치
			// => 같은그룹(re_ref)이면서, 순서가(re_seq) 기존값보다 큰게 있을경우
			sql = "update jsp_board set re_seq = re_seq+1 " + "where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getRe_ref());
			pstmt.setInt(2, vo.getRe_seq());
			pstmt.executeUpdate();

			System.out.println(" 답글순서 재배치 완료! ");

			// 3. 답글 저장(insert)
			sql = "insert into jsp_board(num,name,pwd,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,date,ip,file) " + "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, " [Re] " + vo.getSubject());
			pstmt.setString(5, vo.getContent());

			pstmt.setInt(6, 0); // 조회수 - 0
			pstmt.setInt(7, vo.getRe_ref()); // 답글 - 그룹번호(일반글은 글번호와 동일하게 설정해봄)
			pstmt.setInt(8, vo.getRe_lev() + 1); // 답글 - 들여쓰기(일반글 0)
			pstmt.setInt(9, vo.getRe_seq() + 1); // 답글 - 순서(일반글 0)

			pstmt.setString(10, vo.getIp());
			pstmt.setString(11, vo.getFile());

			pstmt.executeUpdate();
			System.out.println(" 답글 저장 완료! ");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" 답글 저장 실패! ");
		} finally {
			closeDB();
		}
	}
}
