package com.itwillbs.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	
	private Connection con = null;
	private String sql = "";
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
/* getCon() - db 커넥션풀 연결*/	
	public Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/FunWeb");
		con = ds.getConnection();
		System.out.println(" DB연결 성공! ");
		return con;
		
	} //getCon();
	
	
/* closeDB() - db 연결 해제 - 자원해제 */
	public void closeDB() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // closeDB();
	
	
/*insertMember(MemberDTO mdto)*/	
	public boolean insertMember(MemberDTO mdto) {
		try {
			con = getCon();
			sql = "insert into jsp_member(id,pwd,name,email,postnum,address,phone,mobile,joindate)"
					+ " values (?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPwd());
			pstmt.setString(3, mdto.getName());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setString(5, mdto.getPostnum());
			pstmt.setString(6, mdto.getAddress());
			pstmt.setString(7, mdto.getPhone());
			pstmt.setString(8, mdto.getMobile());
			
			pstmt.executeUpdate();
			
			System.out.println(" insert 성공! ");
			return true;
		} catch (Exception e) {
			System.out.println(" insert 에러! ");;
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	} // insertMember(mdto);
	
	
/*userCheck(MemberDTO mdto)*/	
	public MemberDTO userCheck(MemberDTO mdto) { 
		// 쿼리문 반환정보 있으면 유저가 존재한다는 뜻
		MemberDTO MemberDTO = null;
		try {
			con = getCon();
			
			sql = "select * from jsp_member where id=? and pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPwd());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				MemberDTO = new MemberDTO();
				
				MemberDTO.setId(rs.getString("id"));
				MemberDTO.setPwd(rs.getString("pwd"));
				MemberDTO.setName(rs.getString("name"));
				MemberDTO.setEmail(rs.getString("email")); 
				MemberDTO.setPostnum(rs.getString("postnum"));
				MemberDTO.setAddress(rs.getString("address"));
				MemberDTO.setPhone(rs.getString("phone"));
				MemberDTO.setMobile(rs.getString("mobile"));
				MemberDTO.setJoindate(rs.getDate("joindate"));
			}
			
			System.out.println(" userCheck() 성공! ");
			
		} catch (Exception e) {
			System.out.println(" userCheck() 실패! ");
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return MemberDTO;
	} //userCheck(mdto);
	
	
	
	// getMember(id) - updateForm에 기존 정보 불러오는 역할, ID중복체크
	public MemberDTO getMember(String id) {
		
		MemberDTO mdto = null;
		
		try {
			con = getCon();
			sql = "select * from jsp_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mdto = new MemberDTO();
				
				mdto.setId(rs.getString("id"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setJoindate(rs.getDate("joindate"));
				mdto.setMobile(rs.getString("Mobile"));
				mdto.setName(rs.getString("name"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setPostnum(rs.getString("postnum"));
				mdto.setAddress(rs.getString("address"));
			}
			
			System.out.println(" 회원정보 불러오기 성공! ");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" 회원정보 불러오기 실패! ");
		} finally {
			closeDB();
		}
		
		return mdto;
	} // getMember() - 업데이트폼에 기존 정보 불러올때 이용
	
	
	
	public boolean updateMember(MemberDTO mdto) {
		try {
			
			con = getCon();
//			sql = "select pwd from itwill_member2 where id=?";
			sql = "update jsp_member "
					+ "set name=?, email=?, postnum=?, address=?, phone=?, mobile=? "
					+ "where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getEmail());
			pstmt.setString(3, mdto.getPostnum());
			pstmt.setString(4, mdto.getAddress());
			pstmt.setString(5, mdto.getPhone());
			pstmt.setString(6, mdto.getMobile());
			pstmt.setString(7, mdto.getId());
			pstmt.executeUpdate();

			System.out.println(" 수정 완료!! ");
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" 수정 실패!! ");
			
			return false;
			
		} finally {
			closeDB();
		}
	} // updateMember(mdto)
}
