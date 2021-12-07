package com.itwillbs.board;

import java.sql.Date;

/**
 * @author 해연
 *
 */
public class BoardDTO {

	private int num; // 글번호
	private String name; // 작성자
	private String pwd; // 패스워드
	private String subject; // 제목
	private String content; // 내용
	private int readcount; // 조회수
	private int re_ref; // 그룹번호
	private int re_lev; // 답글번호
	private int re_seq; // 답글순서
	private String ip; // 작성자ip
	private String file; // 파일첨부
	private Date date; // 작성날짜
	
	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public int getReadcount() {
		return readcount;
	}



	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}



	public int getRe_ref() {
		return re_ref;
	}



	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}



	public int getRe_lev() {
		return re_lev;
	}



	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}



	public int getRe_seq() {
		return re_seq;
	}



	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getFile() {
		return file;
	}



	public void setFile(String file) {
		this.file = file;
	}



	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", pwd="+ pwd + ", name=" + name + ", content=" + content + ", readcount=" + readcount
				+ ", re_ref=" + re_ref + ", re_lev=" + re_lev + ", re_seq=" + re_seq + ", date=" + date + ", ip=" + ip
				+ ", file=" + file + "]";
	}
	
}
