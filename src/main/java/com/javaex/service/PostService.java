package com.javaex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	@Autowired
	private CategoryDao categoryDao;
	
	//글 삽입 
	public int insert(PostVo postVo) {
	 return postDao.insert(postVo);
	}

	public PostVo getArticle(int postNo, int cateNo, String id) {
		System.out.println("post글가져오기 "+postNo+" "+cateNo+" "+id);
		List<CategoryVo> categoryList=new ArrayList<>();
		if(cateNo==-1 && postNo==-1) { //메인페이지 초기 cateNo=-1/postNo=-1
			categoryList=categoryDao.selectAll(id);
			cateNo= categoryList.get(0).getCateNo();
			List<PostVo> postList = postDao.getArticleList(cateNo);
			if(postList.size() != 0) { //메인페이지 초기에도 최근카테고리&최근글리스트중 가장 최근글을 가져오도록 처리 
				postNo=postList.get(0).getPostNo();
				return postDao.getArticle(postNo);
			} else { //회원가입시 글이 없기 때문에 당연히 postList 사이즈가 0/ 이기때문에 미분류/글없음/처리 
				return null;
			}
//진짜중요 나중에 한번 더 확인하자		
		}else if(cateNo!=-1 && postNo==-1){ //카테고리 클릭시 cateNo:값 있음 39/ postNo=-1 (카테고리 클릭시 postNo를 넘길 수 없기때문)
			//39를 일단 넘겨주자
			List<PostVo> postList=postDao.getArticleList(cateNo);
			if(postList.size() != 0) { 
			postNo=postList.get(0).getPostNo();
			return postDao.getArticle(postNo);
			} else { //카테고리 새로만들면 글이 없기 때문에 당연히 postList 사이즈가 0-->처리해줘야함
				return null;
			}
		}else {	//postNo, cateNo 값이 다 있는경우 && categorylist, postlist다 클릭한경우 	
			return postDao.getArticle(postNo);
		}
	}
	
	//post 리스트 가져오기
	public List<PostVo> getArticleList(int cateNo, String id){
		System.out.println("postlist글가져오기  "+cateNo+" "+id);
		List<CategoryVo> categoryList=new ArrayList<>();
	   if(cateNo==-1) {
		   categoryList=categoryDao.selectAll(id);
		   cateNo= categoryList.get(0).getCateNo();
		   return postDao.getArticleList(cateNo);
		  }else {
		   return postDao.getArticleList(cateNo);			  
		}			
	}
}
