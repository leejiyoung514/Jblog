package com.javaex.vo;

public class CategoryVo {

	private int cateNo;
	private String id;
	private String cateName;
	private String description;
	private String regDate;
	
	private int postCount;
	private int rnum;

	public CategoryVo() {

	}

	public CategoryVo(String id, String cateName) {
		this.id = id;
		this.cateName = cateName;
	}

	public CategoryVo(String id, String cateName, String description) {
		this.id = id;
		this.cateName = cateName;
		this.description = description;
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate, int postCount) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
		this.postCount = postCount;
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}

	
	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate, int postCount,
			int rnum) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
		this.postCount = postCount;
		this.rnum = rnum;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", description=" + description
				+ ", regDate=" + regDate + ", postCount=" + postCount + ", rnum=" + rnum + "]";
	}

	



}
