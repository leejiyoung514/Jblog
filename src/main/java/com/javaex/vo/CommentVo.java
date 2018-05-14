package com.javaex.vo;

public class CommentVo {
	
    private int cmtNo; //식별번호
	private int postNo; //글번호
	private int userNo; //회원번호
    private String cmtContent; //댓글내용
    private String regDate; //등록일
    
    private String id; //아이디
    private String userName; //회원이름
    
    public CommentVo() {

	}    

	public CommentVo(int postNo, int userNo, String cmtContent) {
		super();
		this.postNo = postNo;
		this.userNo = userNo;
		this.cmtContent = cmtContent;
	}

	public CommentVo(int cmtNo, int postNo, int userNo, String cmtContent, String regDate) {
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.userNo = userNo;
		this.cmtContent = cmtContent;
		this.regDate = regDate;
	}

	public CommentVo(int cmtNo, int postNo, int userNo, String cmtContent, String regDate, String userName) {
		super();
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.userNo = userNo;
		this.cmtContent = cmtContent;
		this.regDate = regDate;
		this.userName = userName;
	}

	
	
	public CommentVo(int cmtNo, int postNo, int userNo, String cmtContent, String regDate, String id, String userName) {
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.userNo = userNo;
		this.cmtContent = cmtContent;
		this.regDate = regDate;
		this.id = id;
		this.userName = userName;
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getCmtContent() {
		return cmtContent;
	}

	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CommentVo [cmtNo=" + cmtNo + ", postNo=" + postNo + ", userNo=" + userNo + ", cmtContent=" + cmtContent
				+ ", regDate=" + regDate + ", id=" + id + ", userName=" + userName + "]";
	}
    	
}
