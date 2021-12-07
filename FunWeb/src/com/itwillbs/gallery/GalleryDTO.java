package com.itwillbs.gallery;

import java.sql.Date;

public class GalleryDTO {
	private int num; // 글번호
	private String name; // 작성자
	private String pwd; // 패스워드
	private String subject; // 제목
	private String content; // 내용
	private int readcount; // 조회수
	private String file; // 파일첨부
	private Date date; // 작성날짜
	private String ip; // 작성자ip
	
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
		return "ReviewVO [num=" + num + ", name=" + name + ", pwd=" + pwd + ", subject=" + subject + ", content="
				+ content + ", readcount=" + readcount + ", file=" + file + ", ip=" + ip + ", date=" + date + "]";
	}



}
