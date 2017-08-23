package com.bigdata2017.jdbc.bookshop.vo;

public class BookVO {
	private Long 	no;
	private String 	title;
	private String 	state;
	private Long 	authorNO;
	private String 	AuthorName;
	
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getAuthorNO() {
		return authorNO;
	}
	public void setAuthorNO(Long authorNO) {
		this.authorNO = authorNO;
	}
	public String getAuthorName() {
		return AuthorName;
	}
	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	
	@Override
	public String toString() {
		return "BookVO [no=" + no + ", title=" + title + ", state=" + state + ", authorNO=" + authorNO + ", AuthorName="
				+ AuthorName + "]";
	}
	
	
	
}
