package com.javaex.vo;

public class PostVo {

	private int postNo; //식별번호
	private int cateNo; //카테고리번호
    private String postTitle; //글 제목
    private String postContent; //글 내용
    private String regDate; //글 작성일
    private int rnum;
  
    //private String cateName; //카테고리제목
  
    
	public PostVo() {
		
	}
 
	public PostVo(int postNo, int cateNo, String postTitle, String postContent, String regDate) {
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
	}


	public PostVo(int postNo, int cateNo, String postTitle, String postContent, String regDate, int rnum) {
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
		this.rnum = rnum;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", cateNo=" + cateNo + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", regDate=" + regDate + ", rnum=" + rnum + "]";
	}
   	
}
