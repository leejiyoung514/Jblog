package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UsersService {

	@Autowired
	private UserDao userDao;
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private CategoryDao categoryDao;
	   
    // 회원등록 3가지(회원등록,블로그생성,카테고리생성)
    @Transactional
	public int join(UserVo userVo) {
    	//회원등록
		int result=userDao.insert(userVo);
		
		//블로그 생성 
	    String blogTitle = userVo.getUserName()+"의 블로그 입니다.";
		String logoFile = "15258749985887e244dfa-9c3d-4cb7-9e0a-c1e5a4b076a7.jpg";
		BlogVo blogVo = new BlogVo(userVo.getId(), blogTitle, logoFile);
	    blogDao.insert(blogVo);
	   	                                  
	    //카테고리
	    String cateName="미분류";
	    String description="";
	    CategoryVo categoryVo=new CategoryVo(userVo.getId(),cateName, description);
	    categoryDao.insert(categoryVo);
		return result;
	}
	
	// 아이디체크
	public Boolean idCheck(String id) {
		id = userDao.idCheck(id);
		Boolean isExist = true;
		if (id == null) {
			isExist = true;
		} else {
			isExist = false;
		}
		return isExist;
	}

	// 로그인-회원정보가져오기
	public UserVo login(String id, String password) {
		return userDao.getUser(id, password);
	}

}


