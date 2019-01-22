package com.duzone.jdbc.bookshop.vo;

public class BookVo {
	private long no;
	private String title;
	private String status;
	
//	private AuthorVo author; jdbc 비즈니스로 맞추려고해서 현재는 아니다 객체지향적으롤라면 맞다
	private long AuthorNo;
	private String authorName;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getAuthorNo() {
		return AuthorNo;
	}
	public void setAuthorNo(long authorNo) {
		AuthorNo = authorNo;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", status=" + status + ", AuthorNo=" + AuthorNo
				+ ", authorName=" + authorName + "]";
	}
	
	
}
